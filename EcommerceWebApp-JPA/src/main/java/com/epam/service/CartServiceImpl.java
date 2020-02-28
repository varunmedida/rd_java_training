package com.epam.service;

import java.util.Set;

import com.epam.dao.OnlineShoppingDao;
import com.epam.dao.OnlineShoppingDaoImpl;
import com.epam.model.CartItem;
import com.epam.model.Product;

public class CartServiceImpl implements CartService {

	OnlineShoppingDao dao = new OnlineShoppingDaoImpl();
	
	public boolean addToCart(int productOption, int quantityAdded) {
		boolean addedToCart=false;
		Product product = dao.getProductById(productOption);
		if(quantityAdded>0 && quantityAdded<=product.getQuantity()) {
			dao.addToCart(product,quantityAdded);
			addedToCart=true;
		}
		return addedToCart;
	}
	@Override
	public Set<CartItem> viewCart() {
		return dao.viewCart();
	}
	@Override
	public double calculateAmount(Set<CartItem> cartItems) {
		double total=0;
		for(CartItem cartItem:cartItems) {
			total = (cartItem.getQuantityToCart()*cartItem.getProduct().getProductPrice())+total;
		}
		return total;
	}

}
