package com.epam.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.SubCategory;

public class OnlineShoppingDaoImpl implements OnlineShoppingDao {
	Logger logger = LogManager.getLogger(OnlineShoppingDaoImpl.class);

	@Override
	public List<Category> getAllCategories() throws SQLException, IOException, ClassNotFoundException {

		List<Category> categoryList = new ArrayList<>();
		try (Connection connection = DatabaseConnection.initializeDatabase();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery(DatabaseConnection.SELECT_CATEGORY);) {

			while (resultSet.next()) {
				categoryList.add(new Category(resultSet.getInt("categoryId"), resultSet.getString("categoryName")));
			}
		}

		return categoryList;
	}

	@Override
	public List<SubCategory> getSubCategoriesBasedOnCategory(String categoryOption)
			throws ClassNotFoundException, SQLException {
		List<SubCategory> subCategoryList = new ArrayList<>();

		try (Connection connection = DatabaseConnection.initializeDatabase();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery(DatabaseConnection.SELECT_SUBCATEGORY + categoryOption);) {

			while (resultSet.next()) {
				subCategoryList.add(new SubCategory(resultSet.getInt("subcategoryId"), resultSet.getInt("categoryId"),
						resultSet.getString("subCategoryName")));
			}
		}

		return subCategoryList;
	}

	@Override
	public List<Product> getProductsBasedOnSubCategory(String subCategoryOption)
			throws ClassNotFoundException, SQLException {

		List<Product> products = new ArrayList<>();

		try (Connection connection = DatabaseConnection.initializeDatabase();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery(DatabaseConnection.SELECT_PRODUCT + subCategoryOption);) {

			while (resultSet.next()) {
				products.add(new Product(resultSet.getInt("productId"), resultSet.getInt("subCategoryId"),
						resultSet.getString("productName"), resultSet.getDouble("productPrice"),
						resultSet.getInt("quantityOfStock")));
			}
		}

		return products;
	}

}
