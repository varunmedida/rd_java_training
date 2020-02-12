package com.epam.service;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidationServiceImpl implements ValidationService {

	Logger logger = LogManager.getLogger(ValidationServiceImpl.class);
	Scanner scanner = new Scanner(System.in);
	public int validateInput() {
		while(!scanner.hasNextInt()) {
			logger.error("Please enter a valid input!");
			scanner.next();
		}
		return scanner.nextInt();
	}
	

	
}
