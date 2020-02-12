package com.epam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	static Connection con;
	public static Connection getConnection() {
		try {
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ecommercedb","root","Epam1234$");
		}catch(Exception e) {
			System.out.println("Could not connect to database");
		}
		return con;
	}
	public static void closeConnection() throws SQLException {
		con.close();
	}
}
