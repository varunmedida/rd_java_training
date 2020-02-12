package com.epam.service;

import java.util.List;

import com.epam.beans.Cart;

public interface CartService {

	void addProductToCart(int productOption, int subCategoryOption, int quanityToBeAdded);

	List<Cart> viewCart();

	void removeProductFromCart(int productId);

}
