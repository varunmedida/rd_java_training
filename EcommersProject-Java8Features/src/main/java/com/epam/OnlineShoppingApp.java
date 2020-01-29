package com.epam;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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

	public static void main(String[] args) {
		System.out.println(LINE);
		System.out.println("|	Welcome to EPAMER's Store	|");
		System.out.println(LINE);
		boolean continueShopping = true;
		do {
			System.out.println("\nWhat would you like to do?");
			System.out.println("1. Shopping\n2. View Cart\n3. Remove products from cart\n4. Checkout\n5. Exit");
			System.out.print("Choose an option:\t");
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
						System.out.println("Thank you for shopping with us.");
					}
					break;
				case 5:
					continueShopping = false;
					System.out.println("Thank you for shopping with us.");
					break;
				default:
					System.err.println("Invalid option. Please enter again!");
					break;
				}
			} catch (InputMismatchException inputMismatch) {
				System.err.println("\nInvalid option. Please enter again!");
				sc.next();
			}
		} while (continueShopping);
		sc.close();

	}

	private static boolean checkout() {
		boolean checkedOut = false;
		ArrayList<Cart> cartList = service.viewCart();
		if (cartList.isEmpty()) {
			System.err.println("\nNo Products in item to buy");
		} else {
			System.out.println("\nCart:");
			System.out.println("\nProducts");
			System.out.println(LINE);
			cartList.stream().forEach(System.out::println);
			System.out.println(LINE);
			double totalAmount = service.calculateTotalPrice(cartList);
			System.out.println("TOTAL AMOUNT:\t" + totalAmount);
			System.out.println("WOULD YOU LIKE TO CHECKOUT?(y/n)");
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
			System.err.println("\nNo Products to remove from cart as cart is empty");
		} else {
			System.out.println("\nCart Products:");
			System.out.println(LINE);
			cartList.stream().forEach(System.out::println);
			System.out.println(LINE);
			System.out.print("Enter product id to be removed:");
			int productId = sc.nextInt();
			service.removeProductFromCart(productId);
		}
	}

	private static void viewCart() {
		ArrayList<Cart> cartList = service.viewCart();
		if (cartList.isEmpty()) {
			System.err.println("\nNo Products in cart");
		} else {
			System.out.println("\nCart:");
			System.out.println("\nProducts");
			System.out.println(LINE);
			cartList.stream().forEach(System.out::println);
			System.out.println(LINE);
		}
	}

	private static void displayAllCategories() {
		ArrayList<Category> categoryList = service.getAllCategories();
		System.out.println("\nCategories");
		System.out.println("-----------");
		categoryList.stream().forEach(System.out::println);
		System.out.print("Choose Category:\t");
		int categoryOption = sc.nextInt();
		displaySubCategoriesBasedOnCategory(categoryOption);
	}

	private static void displaySubCategoriesBasedOnCategory(int categoryOption) {
		ArrayList<SubCategory> subCategoryList = service.displaySubCategoriesBasedOnCategory(categoryOption);
		if (subCategoryList.isEmpty()) {
			System.err.println("\nNo Sub Categories and products in this option yet!");
			displayAllCategories();
		} else {
			System.out.println("\nSub-Categories");
			System.out.println("-----------------");
			subCategoryList.stream().forEach(System.out::println);
			System.out.print("Select SubCategory:\t");
			int subCategoryOption = sc.nextInt();
			displayProductsBasedOnSubCategory(subCategoryOption);
		}
	}

	private static void displayProductsBasedOnSubCategory(int subCategoryOption) {

		ArrayList<Product> productList = service.diplayProductsBasedOnSubCategory(subCategoryOption);
		if (productList.isEmpty()) {
			System.err.println("\nNo products in this Sub-Category.");
			displayAllCategories();
		} else {
			System.out.println("\nProducts:");
			System.out.println("-----------------");
			productList.stream().forEach(System.out::println);
			System.out.print("Select Product:\t");
			int productOption = sc.nextInt();
			System.out.print("Enter Quantity to be added to cart:");
			int quantityToAdd = sc.nextInt();
			addProductToCart(subCategoryOption, productOption, quantityToAdd);
		}
	}

	private static void addProductToCart(int subCategoryOption, int productOption, int quantityToAdd) {
		service.addProductToCart(subCategoryOption, productOption, quantityToAdd);
	}

}