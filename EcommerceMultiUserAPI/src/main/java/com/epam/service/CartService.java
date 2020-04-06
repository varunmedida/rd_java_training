package com.epam.service;

import com.epam.exception.EmptyCartException;
import com.epam.model.CartItem;
import com.epam.model.ShoppingCart;

public interface CartService {

	ShoppingCart viewCart(String userName) throws EmptyCartException;

	CartItem addToCart(Long productId, Long quantity, String userName);

	CartItem updateCart(Long productId, Long quantity, String userName);

	CartItem deleteCartItem(Long productId, String userName);

	ShoppingCart checkout(String userName);

}
