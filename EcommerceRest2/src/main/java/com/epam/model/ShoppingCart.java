package com.epam.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

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

	@OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
	private List<CartItem> cartItems = new ArrayList<>();

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
			BigDecimal bd = new BigDecimal(totalAmount).setScale(2, RoundingMode.HALF_UP);
			totalAmount = bd.doubleValue();
		}
		this.totalAmount = totalAmount;
	}

}
