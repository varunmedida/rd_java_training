package com.epam.service;

import java.util.Set;

import com.epam.model.CartItem;

public interface CartService {

	boolean addToCart(int productOption, int quantityAdded);

	Set<CartItem> viewCart();

	double calculateAmount(Set<CartItem> cartItems);

}
