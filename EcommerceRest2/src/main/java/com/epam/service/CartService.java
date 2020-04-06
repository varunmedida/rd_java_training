package com.epam.service;

import com.epam.exception.EmptyCartException;
import com.epam.model.CartItem;
import com.epam.model.ShoppingCart;

public interface CartService {

	ShoppingCart viewCart() throws EmptyCartException;

	CartItem addToCart(Long productId, Long quantity);

	CartItem updateCart(Long productId, Long quantity);

	CartItem deleteCartItem(Long productId);

	ShoppingCart checkout();

	CartItem deleteCartItemByCartId(Long cartId);

}
