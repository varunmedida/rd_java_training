package com.epam.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseConnection {
	public static String SELECT_CATEGORY;
	public static String SELECT_SUBCATEGORY;
	public static String SELECT_PRODUCT;

	static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
		Logger logger = LogManager.getLogger(DatabaseConnection.class);
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(
					"D:\\rd_java_training\\EcommerceWebApp\\src\\main\\resources\\config.properties");
			properties.load(inputStream);
		} catch (IOException exception) {
			logger.info(exception);
		}
		SELECT_CATEGORY=properties.getProperty("selectcategory");
		SELECT_SUBCATEGORY=properties.getProperty("selectsubcategory");
		SELECT_PRODUCT=properties.getProperty("selectproduct");
		Class.forName(properties.getProperty("dbDriver"));
		return DriverManager.getConnection(properties.getProperty("dbURL") + properties.getProperty("dbName"),
				properties.getProperty("dbUsername"), properties.getProperty("dbPassword"));

	}

}
