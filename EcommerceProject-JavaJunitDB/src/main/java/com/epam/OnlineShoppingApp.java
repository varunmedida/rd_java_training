package com.epam;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.controller.OnlineShoppingController;
import com.epam.controller.OnlineShoppingControllerImpl;
import com.epam.service.ValidationService;
import com.epam.service.ValidationServiceImpl;

public class OnlineShoppingApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ValidationService validationService = new ValidationServiceImpl();
		OnlineShoppingController controller = new OnlineShoppingControllerImpl();
		Logger logger = LogManager.getLogger(OnlineShoppingApp.class);
		logger.info("--------------------");
		logger.info("   Epamer's Store");
		logger.info("--------------------");
		boolean continueShopping = true;
		do {
			logger.info("What would you like to do?");
			logger.info("1. Shopping");
			logger.info("2. View Cart");
			logger.info("3. Remove products from cart");
			logger.info("4. Checkout");
			logger.info("5. Exit");
			logger.info("Choose an option:\t");
			int option = validationService.validateInput();
			switch (option) {
			case 1:
				controller.displayAllCategories(scanner);
				break;
			case 2:
				controller.viewCart();
				break;
			case 3:	
				controller.removeProductFromCart();
				break;
			case 5:
				continueShopping = false;
				logger.info("Thank you for shopping with us.");
				break;
			default:
				logger.error("Invalid option. Please enter again!");
				break;
			}

		} while (continueShopping);
		scanner.close();
	}
	
}
