package com.epam.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.beans.Cart;
import com.epam.beans.Category;
import com.epam.beans.Product;
import com.epam.beans.SubCategory;

public class OnlineShoppingDaoImpl implements OnlineShoppingDao {

	Logger logger = LogManager.getLogger(OnlineShoppingDaoImpl.class);
	List<Category> categoryList = new ArrayList<>();
	List<SubCategory> subCategoryList = new ArrayList<>();
	List<Product> productList = new ArrayList<>();
	List<Cart> cartList = new ArrayList<>();
	Statement statement;
	ResultSet resultSet;

	public OnlineShoppingDaoImpl() {
		Connection connection = DatabaseConnection.getConnection();
		try {
			statement = connection.createStatement();
			fetchCategoriesFromDatabase(statement);
			fetchSubCategoriesFromDatabase(statement);
			fetchProductsFromDatabase(statement);
		} catch (SQLException message) {
			logger.info("Could not perform operation");
		}
	}

	private void fetchProductsFromDatabase(Statement statement) throws SQLException {
		resultSet = statement.executeQuery("select * from product");
		while (resultSet.next()) {
			productList.add(new Product(resultSet.getInt("productId"), resultSet.getInt("subCategoryId"),
					resultSet.getString("productName"), resultSet.getDouble("productPrice"),
					resultSet.getInt("quantityOfStock")));
		}
	}

	private void fetchSubCategoriesFromDatabase(Statement statement) throws SQLException {
		resultSet = statement.executeQuery("select * from subCategory");
		while (resultSet.next()) {
			subCategoryList.add(new SubCategory(resultSet.getInt("subCategoryId"), resultSet.getInt("categoryId"),
					resultSet.getString("subCategoryName")));
		}
	}

	private void fetchCategoriesFromDatabase(Statement statement) throws SQLException {
		resultSet = statement.executeQuery("select * from category");
		while (resultSet.next()) {
			categoryList.add(new Category(resultSet.getInt("categoryId"), resultSet.getString("categoryName")));
		}
	}

	@Override
	public List<Category> displayAllCategories() {
		return categoryList;
	}

	@Override
	public List<SubCategory> displaySubCategoriesBasedOnCategory(int categoryOption) {
		return subCategoryList.stream().filter(subCategory -> subCategory.getCategoryId() == categoryOption)
				.collect(Collectors.toList());
	}

	@Override
	public List<Product> displayProductsBasedOnSubCategory(int subCategoryOption) {
		return productList.stream().filter(product -> product.getSubCategoryId() == subCategoryOption)
				.collect(Collectors.toList());
	}

	@Override
	public void addProductToCart(int subCategoryOption, int productOption, int quantityToAdd) {

		Product productToCart = productList.stream().filter(
				product -> product.getProductId() == productOption && product.getSubCategoryId() == subCategoryOption)
				.findAny().orElse(null);
		Cart productPresentInCart = cartList.stream().filter(cart -> cart.getProductId() == productOption).findAny()
				.orElse(null);
		Optional<Product> productOptional = Optional.ofNullable(productToCart);
		Optional<Cart> cartOptional = Optional.ofNullable(productPresentInCart);
		if (productOptional.isPresent()) {
			if (cartOptional.isPresent()) {
				alreadyPresentInCart(productPresentInCart, productToCart, quantityToAdd);
			} else {
				notPresentInCart(productToCart, quantityToAdd);
			}
		} else {
			logger.error("Product Not Present");
		}
	}

	private void notPresentInCart(Product productToCart, int quantityToAdd) {

		if (quantityToAdd <= productToCart.getQuantityOfStock()) {
			cartList.add(new Cart(productToCart.getProductId(), productToCart.getProductName(),
					productToCart.getProductPrice(), quantityToAdd));
			logger.info("Product Added to Cart.");
		} else {
			logger.error("Insufficient Quantity!");
		}
	}

	private void alreadyPresentInCart(Cart productPresentInCart, Product productToCart, int quantityToAdd) {

		if (productPresentInCart.getQuantityAdded() + quantityToAdd <= productToCart.getQuantityOfStock()) {
			productPresentInCart.setQuantityAdded(quantityToAdd + productPresentInCart.getQuantityAdded());
			logger.info("Product already present. Increased quantity.");
		} else {
			logger.error("Insufficient Quantity!");
		}
	}

	@Override
	public List<Cart> viewCart() {
		return cartList;
	}

	@Override
	public void removeProductFromCart(int productId) {
		Cart productToBeRemoved = cartList.stream().filter(cart -> cart.getProductId() == productId).findAny()
				.orElse(null);
		Optional<Cart> cartOptional = Optional.ofNullable(productToBeRemoved);
		if (cartOptional.isPresent()) {
			cartList.remove(productToBeRemoved);
			logger.info("Product Removed Successfully");
		} else {
			logger.error("Product not found to be removed.");
		}
	}
}
