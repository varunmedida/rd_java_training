package com.epam.controller;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.beans.Cart;
import com.epam.beans.Category;
import com.epam.beans.Product;
import com.epam.beans.SubCategory;
import com.epam.service.CartService;
import com.epam.service.CartServiceImpl;
import com.epam.service.DisplayService;
import com.epam.service.DisplayServiceImpl;
import com.epam.service.ValidationService;
import com.epam.service.ValidationServiceImpl;

public class OnlineShoppingControllerImpl implements OnlineShoppingController {

	Logger logger = LogManager.getLogger(OnlineShoppingControllerImpl.class);
	DisplayService displayService = new DisplayServiceImpl();
	ValidationService validationService = new ValidationServiceImpl();
	CartService cartService = new CartServiceImpl();

	@Override
	public void displayAllCategories(Scanner scanner) {
		logger.info("--------------------");
		logger.info("Categories");
		logger.info("--------------------");
		List<Category> categoryList = displayService.displayAllCategories();
		categoryList.stream().forEach(category -> logger.info(category));
		logger.info("Choose your category:");
		int categoryOption = validationService.validateInput();
		List<Integer> categoryIds = categoryList.stream().filter(category -> category.getCategoryId() == categoryOption)
				.map(Category::getCategoryId).collect(Collectors.toList());
		if (categoryIds.isEmpty()) {
			logger.error("Please enter valid category");
			displayAllCategories(scanner);
		} else {
			displaySubCategoriesBasedOnCategory(categoryOption, scanner);
		}
	}

	private void displaySubCategoriesBasedOnCategory(int categoryOption, Scanner scanner) {
		logger.info("--------------------");
		logger.info("Sub-Categories");
		logger.info("--------------------");
		List<SubCategory> subCategoryList = displayService.displaySubCategoriesBasedOnCategory(categoryOption);
		if (!subCategoryList.isEmpty()) {
			subCategoryList.stream().forEach(subCategory -> logger.info(subCategory));
			logger.info("Choose your sub-category:");
			int subCategoryOption = validationService.validateInput();
			List<Integer> subCategoryIds = subCategoryList.stream()
					.filter(subCategory -> subCategory.getSubCategoryId() == subCategoryOption)
					.map(SubCategory::getSubCategoryId).collect(Collectors.toList());
			if (subCategoryIds.isEmpty()) {
				logger.error("Please enter valid sub-category");
				displaySubCategoriesBasedOnCategory(categoryOption, scanner);
			} else {
				displayProductsBasedOnSubCategory(subCategoryOption, scanner);
			}
		} else {
			logger.error("No Subcategories in this category!");
		}
	}

	private void displayProductsBasedOnSubCategory(int subCategoryOption, Scanner scanner) {
		logger.info("--------------------");
		logger.info("Products");
		logger.info("--------------------");
		List<Product> productList = displayService.displayProductsBasedOnSubCategory(subCategoryOption);
		if (!productList.isEmpty()) {
			productList.stream().forEach(product -> logger.info(product));
			logger.info("--------------------");
			logger.info("Choose your product:");
			int productOption = validationService.validateInput();
			logger.info("Choose Quantity:");
			int quanityToBeAdded = validationService.validateInput();
			List<Integer> productIds = productList.stream().filter(product -> product.getProductId() == productOption)
					.map(Product::getProductId).collect(Collectors.toList());
			if (productIds.isEmpty()) {
				logger.error("Please enter valid product");
				displayProductsBasedOnSubCategory(subCategoryOption, scanner);
			} else {
				cartService.addProductToCart(productOption, subCategoryOption, quanityToBeAdded);
			}
		} else {
			logger.error("No products in this sub-category!");
		}
	}

	@Override
	public void viewCart() {
		List<Cart> cartList = cartService.viewCart();
		if (cartList.isEmpty()) {
			logger.error("No Products in cart");
		} else {
			logger.info("Cart:");
			logger.info("Products");
			logger.info("--------------------");
			cartList.stream().forEach(product -> logger.info(product));
			logger.info("--------------------");

		}
	}

	@Override
	public void removeProductFromCart() {
		List<Cart> cartList = cartService.viewCart();
		if (cartList.isEmpty()) {
			logger.error("No Products to remove from cart as cart is empty");
		} else {
			logger.info("Cart Products:");
			logger.info("--------------------");
			cartList.stream().forEach(product -> logger.info(product));
			logger.info("--------------------");
			logger.info("Enter product id to be removed:");
			int productId = validationService.validateInput();
			List<Integer> productIds = cartList.stream().filter(cart -> cart.getProductId() == productId)
					.map(Cart::getProductId).collect(Collectors.toList());
			if (productIds.isEmpty()) {
				logger.error("Please enter valid product");
				removeProductFromCart();
			} else {
				cartService.removeProductFromCart(productId);
			}
			
		}

	}

}
