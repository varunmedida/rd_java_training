package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	public ShoppingCart findByShoppingCartId(Long shoppingCartId);
}
