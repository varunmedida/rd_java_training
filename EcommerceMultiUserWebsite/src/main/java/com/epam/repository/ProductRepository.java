package com.epam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.Product;
import com.epam.model.SubCategory;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public Product findByProductId(Long productId);
	
	public List<Product> findBySubCategory(SubCategory subCategory);
}
