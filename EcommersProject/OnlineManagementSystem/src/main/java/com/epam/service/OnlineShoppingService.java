package com.epam.service;

import java.util.ArrayList;

import com.epam.model.Cart;
import com.epam.model.Category;
import com.epam.model.Product;

public interface OnlineShoppingService {

	ArrayList<Category> getAllCategories();

	void addProductToCart(Product product, int quantity);

	ArrayList<Cart> viewProductsInCart();

	void removeProductsFromCart(int productId);

	double totalAmount(ArrayList<Cart> products);

	void updateStockInInventory(ArrayList<Cart> products);


}
