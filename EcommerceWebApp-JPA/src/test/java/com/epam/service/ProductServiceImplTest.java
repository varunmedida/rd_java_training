package com.epam.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*; 
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.dao.OnlineShoppingDao;
import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.SubCategory;

class ProductServiceImplTest {

	@Mock
	OnlineShoppingDao dao;

	@InjectMocks
	ProductServiceImpl productService;

	@BeforeEach
	void initialize() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testGetProductsBasedOnSubCategory() {
		Category category = new Category("Electronics");
		SubCategory subCategory = new SubCategory("Mobiles", category);
		Product product = new Product(subCategory, "Apple", 5000, 10);
		product.setProductId(1);
		List<Product> products = new ArrayList<>();
		products.add(product);
		when(dao.getProductsBasedOnSubCategory(1)).thenReturn(products);
		assertEquals(products,productService.getProductsBasedOnSubCategory(1));
		
	}

}
