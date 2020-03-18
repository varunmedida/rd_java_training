package com.epam.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.CartItem;
import com.epam.model.Product;
import com.epam.model.ShoppingCart;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	public CartItem findByProductAndShoppingCart(Product product,ShoppingCart shoppingCart);
	
	public Set<CartItem> findByShoppingCart(ShoppingCart shoppingCart); 
}
