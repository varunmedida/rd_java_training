package com.epam.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class ShoppingCart {

	@Id
	private Long shoppingCartId;

	@OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
	private List<CartItem> cartItems = new ArrayList<>();

	@NonNull
	private Double totalAmount;
	
	@ManyToOne
	private User user;
	@Column(columnDefinition = "boolean default false")
	private boolean checkedOut;

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

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount() {
		Double totalAmount = 0.0;
		for (CartItem cartItem : cartItems) {
			totalAmount = (cartItem.getQuantity() * cartItem.getProduct().getProductPrice()) + totalAmount;
		}
		this.totalAmount = totalAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	
	
}
