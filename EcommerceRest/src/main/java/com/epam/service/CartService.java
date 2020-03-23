package com.epam.service;

import com.epam.model.CartItem;
import com.epam.model.ShoppingCart;

public interface CartService {

	ShoppingCart viewCart();

	CartItem addToCart(Long productId, Long quantity);

	CartItem updateCart(Long productId, Long quantity);

	CartItem deleteCartItem(Long productId);

	ShoppingCart checkout();

}
