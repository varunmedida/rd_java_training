package com.epam.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class CartItemTest {

	Category category = new Category("Electronics");
	SubCategory subCategory = new SubCategory("Mobiles", category);
	Product product = new Product(subCategory, "Apple", 5000, 10);
	CartItem cartItem;
	@Test
	void testGetCartItemId() {
		cartItem=new CartItem();
		cartItem.setCartItemId(1);
		assertEquals(1,cartItem.getCartItemId());
	}

	@Test
	void testGetProduct() {
		cartItem=new CartItem();
		cartItem.setCartItemId(1);
		cartItem.setProduct(product);
		assertNotNull(cartItem.getProduct());
	}

	@Test
	void testGetQuantityToCart() {
		cartItem=new CartItem();
		cartItem.setCartItemId(1);
		cartItem.setProduct(product);
		cartItem.setQuantityToCart(5);
		assertEquals(5, cartItem.getQuantityToCart());
	}

	@Test
	void testToString() {
		cartItem=new CartItem();
		cartItem.setCartItemId(1);
		cartItem.setProduct(product);
		cartItem.setQuantityToCart(5);
		assertNotNull(cartItem.toString());
	}

}
