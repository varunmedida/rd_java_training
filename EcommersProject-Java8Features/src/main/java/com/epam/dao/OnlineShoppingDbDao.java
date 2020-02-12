package com.epam.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OnlineShoppingDbDao {

	 private Statement statement = null;
	 private ResultSet resultSet = null;
	public void printCategories() throws SQLException
	{
		statement= DatabaseConnection.getConnection().createStatement();
		resultSet = statement.executeQuery("select * from category");
		while(resultSet.next()) {
			System.out.println(resultSet.getString("categoryId")+" "+resultSet.getString("categoryName"));
		}
		DatabaseConnection.closeConnection();
	}
}
