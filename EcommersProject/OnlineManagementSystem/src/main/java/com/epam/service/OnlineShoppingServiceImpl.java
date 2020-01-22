package com.epam.service;

import java.util.ArrayList;

import com.epam.dao.OnlineShoppingDao;
import com.epam.dao.OnlineShoppingDaoImpl;
import com.epam.model.Cart;
import com.epam.model.Category;
import com.epam.model.Product;

public class OnlineShoppingServiceImpl implements OnlineShoppingService {

	OnlineShoppingDao dao = new OnlineShoppingDaoImpl();

	public ArrayList<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return dao.getAllCategories();
	}

	public void addProductToCart(Product product,int quantity) {
		// TODO Auto-generated method stub
		dao.addProductToCart(product,quantity);
	}

	public ArrayList<Cart> viewProductsInCart() {
		// TODO Auto-generated method stub
		return dao.viewProductsInCart();
	}

	public void removeProductsFromCart(int productId) {
		// TODO Auto-generated method stub
		dao.removeProductsFromCart(productId);
	}

	public double totalAmount(ArrayList<Cart> products) {
		// TODO Auto-generated method stub
		double totalAmount=0;
		for (Cart product : products)
			totalAmount=totalAmount+(product.getProductPrice()*product.getQuantityPurchased());
		return totalAmount;
	}

	public void updateStockInInventory(ArrayList<Cart> products) {
		// TODO Auto-generated method stub
		dao.updateStockInInventory(products);
	}

	

}
