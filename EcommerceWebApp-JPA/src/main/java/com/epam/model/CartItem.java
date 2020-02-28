package com.epam.model;

public class CartItem {
	private int cartItemId;
	private Product product;
	private int quantityToCart;
	public int getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantityToCart() {
		return quantityToCart;
	}
	public void setQuantityToCart(int quantityToCart) {
		this.quantityToCart = quantityToCart;
	}
	@Override
	public String toString() {
		return "CartItem [cartItemId=" + cartItemId + ", product=" + product + ", quantityToCart=" + quantityToCart
				+ "]";
	}


}
