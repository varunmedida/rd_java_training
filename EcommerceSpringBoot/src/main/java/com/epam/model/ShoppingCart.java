package com.epam.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Entity
public class ShoppingCart {

	@Id
	private Long shoppingCartId;

	@OneToMany(mappedBy = "shoppingCart",cascade = CascadeType.ALL)
	private Set<CartItem> cartItems = new HashSet<>();
	
	@NonNull
	private Double totalAmount;

	public ShoppingCart() {
		setShoppingCartId();
	}
	public Long getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId() {
		Long shoppingCartId = (long) (Math.random() * 1000);
		this.shoppingCartId = shoppingCartId;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount() {
		Double totalAmount = 0.0;
		for(CartItem cartItem:cartItems) {
			totalAmount = (cartItem.getQuantity()*cartItem.getProduct().getProductPrice())+totalAmount;
		}
		this.totalAmount = totalAmount;
	}
	@Override
	public String toString() {
		return "ShoppingCart [shoppingCartId=" + shoppingCartId + ", cartItems=" + cartItems + ", totalAmount="
				+ totalAmount + "]";
	}
	
	
}
