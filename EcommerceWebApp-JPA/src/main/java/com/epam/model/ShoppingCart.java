package com.epam.model;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {

	private Set<CartItem> cartItems = new HashSet<>();
	private double totalAmount;

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "ShoppingCart [cartItems=" + cartItems + ", totalAmount=" + totalAmount + "]";
	}

	public void addToShoppingCart(CartItem cartItem) {
		this.cartItems.add(cartItem);
	}
}
