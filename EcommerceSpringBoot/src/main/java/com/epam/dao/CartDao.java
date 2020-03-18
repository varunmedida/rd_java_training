package com.epam.dao;

import com.epam.exception.EmptyCartException;
import com.epam.exception.InsufficientQuantityException;
import com.epam.model.Product;
import com.epam.model.ShoppingCart;

public interface CartDao {

	ShoppingCart viewCart() throws EmptyCartException;

	boolean addToCart(Product product, Long quantity) throws InsufficientQuantityException;

	boolean deleteProduct(Long cartId);

	boolean updateCart(Product productDetails, Long quantity);

	ShoppingCart checkout() throws InsufficientQuantityException;

}
