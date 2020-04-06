package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.ShoppingCart;
import com.epam.model.User;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	public ShoppingCart findByUserAndCheckedOut(User user,boolean checkedOut);
	
}
