package com.epam;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.model.Cart;
import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.SubCategory;
import com.epam.service.OnlineShoppingService;
import com.epam.service.OnlineShoppingServiceImpl;

public class OnlineShoppingApp {
	static Scanner sc = new Scanner(System.in);
	static OnlineShoppingService service = new OnlineShoppingServiceImpl();
	private static final String LINE = "--------------------------------------";
	static Logger logger = LogManager.getLogger(OnlineShoppingApp.class);
	public static void main(String[] args) {
		logger.info(LINE);
		logger.info("|	Welcome to EPAMER's Store	|");
		logger.info(LINE);
		boolean continueShopping = true;
		do {
			logger.info("What would you like to do?");
			logger.info("1. Shopping");
			logger.info("2. View Cart");
			logger.info("3. Remove products from cart");
			logger.info("4. Checkout");
			logger.info("5. Exit");
			logger.info("Choose an option:\t");
			try {
				int option = sc.nextInt();
				switch (option) {
				case 1:
					displayAllCategories();
					break;
				case 2:
					viewCart();
					break;
				case 3:
					removeProductFromCart();
					break;
				case 4:
					boolean checkedOut = checkout();
					if (checkedOut) {
						continueShopping = false;
						logger.info("Thank you for shopping with us.");
					}
					break;
				case 5:
					continueShopping = false;
					logger.info("Thank you for shopping with us.");
					break;
				default:
					logger.error("Invalid option. Please enter again!");
					break;
				}
			} catch (InputMismatchException inputMismatch) {
				logger.error("Invalid option. Please enter again!");
				sc.next();
			}
		} while (continueShopping);
		sc.close();

	}

	private static boolean checkout() {
		boolean checkedOut = false;
		ArrayList<Cart> cartList = service.viewCart();
		if (cartList.isEmpty()) {
			logger.error("No Products in item to buy");
		} else {
			logger.info("Cart:");
			logger.info("Products");
			logger.info(LINE);
			cartList.stream().forEach(product -> logger.info(product));
			logger.info(LINE);
			double totalAmount = service.calculateTotalPrice(cartList);
			logger.info("TOTAL AMOUNT:\t {}", totalAmount);
			logger.info("WOULD YOU LIKE TO CHECKOUT?(y/n)");
			char checkout = sc.next().charAt(0);
			if (checkout == 'y') {
				service.updateInventoryStock(cartList);
				checkedOut = true;
			}

		}
		return checkedOut;

	}

	private static void removeProductFromCart() {
		ArrayList<Cart> cartList = service.viewCart();
		if (cartList.isEmpty()) {
			logger.error("No Products to remove from cart as cart is empty");
		} else {
			logger.info("Cart Products:");
			logger.info(LINE);
			cartList.stream().forEach(product -> logger.info(product));
			logger.info(LINE);
			logger.info("Enter product id to be removed:");
			int productId = sc.nextInt();
			service.removeProductFromCart(productId);
		}
	}

	private static void viewCart() {
		ArrayList<Cart> cartList = service.viewCart();
		if (cartList.isEmpty()) {
			logger.error("No Products in cart");
		} else {
			logger.info("Cart:");
			logger.info("Products");
			logger.info(LINE);
			cartList.stream().forEach(product -> logger.info(product));
			logger.info(LINE);
		}
	}

	private static void displayAllCategories() {
		ArrayList<Category> categoryList = service.getAllCategories();
		logger.info("Categories");
		logger.info("-----------");
		categoryList.stream().forEach(product -> logger.info(product));
		logger.info("Choose Category:\t");
		int categoryOption = sc.nextInt();
		displaySubCategoriesBasedOnCategory(categoryOption);
	}

	private static void displaySubCategoriesBasedOnCategory(int categoryOption) {
		ArrayList<SubCategory> subCategoryList = service.displaySubCategoriesBasedOnCategory(categoryOption);
		if (subCategoryList.isEmpty()) {
			logger.error("No Sub Categories and products in this option yet!");
			displayAllCategories();
		} else {
			logger.info("Sub-Categories");
			logger.info("-----------------");
			subCategoryList.stream().forEach(subCategory -> logger.info(subCategory));
			logger.info("Select SubCategory:\t");
			int subCategoryOption = sc.nextInt();
			displayProductsBasedOnSubCategory(subCategoryOption);
		}
	}

	private static void displayProductsBasedOnSubCategory(int subCategoryOption) {

		ArrayList<Product> productList = service.diplayProductsBasedOnSubCategory(subCategoryOption);
		if (productList.isEmpty()) {
			logger.error("No products in this Sub-Category.");
			displayAllCategories();
		} else {
			logger.info("Products:");
			logger.info("-----------------");
			productList.stream().forEach(product -> logger.info(product));
			logger.info("Select Product:\t");
			int productOption = sc.nextInt();
			logger.info("Enter Quantity to be added to cart:");
			int quantityToAdd = sc.nextInt();
			addProductToCart(subCategoryOption, productOption, quantityToAdd);
		}
	}

	private static void addProductToCart(int subCategoryOption, int productOption, int quantityToAdd) {
		service.addProductToCart(subCategoryOption, productOption, quantityToAdd);
	}

}
