package com.epam.service;

import com.epam.exception.EmptyCartException;
import com.epam.exception.InsufficientQuantityException;
import com.epam.model.ShoppingCart;

public interface CartService {

	ShoppingCart viewCart() throws EmptyCartException;

	boolean addToCart(Long productId, Long quantity) throws InsufficientQuantityException;

	boolean deleteProduct(Long cartId);

	boolean updateCart(Long productId, Long quantity) throws InsufficientQuantityException;

	ShoppingCart checkout() throws InsufficientQuantityException;
	
}
