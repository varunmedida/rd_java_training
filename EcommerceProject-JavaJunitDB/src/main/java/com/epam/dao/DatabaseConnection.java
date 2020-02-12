package com.epam.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseConnection {

	 static Logger logger = LogManager.getLogger(DatabaseConnection.class);

	public static Connection getConnection() {
		Connection connect = null;
		try {
		Properties properties = new Properties();
		properties.load(new FileInputStream(new File("src/main/resources/config.properties")));
		connect = DriverManager.getConnection(properties.getProperty("db-url") + properties.getProperty("db"),
				properties.getProperty("dbuser"), properties.getProperty("dbpassword"));
		}catch(SQLException | IOException message) {
			logger.info("Could not connect to database");
		}
		return connect;
	}

}
