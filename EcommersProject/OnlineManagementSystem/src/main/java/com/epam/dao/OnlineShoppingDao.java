package com.epam.dao;

import java.util.ArrayList;

import com.epam.model.Cart;
import com.epam.model.Category;
import com.epam.model.Product;

public interface OnlineShoppingDao {

	ArrayList<Category> getAllCategories();

	void addProductToCart(Product product, int quantity);

	public ArrayList<Cart> viewProductsInCart();

	void removeProductsFromCart(int productId);

	void updateStockInInventory(ArrayList<Cart> products);

}
