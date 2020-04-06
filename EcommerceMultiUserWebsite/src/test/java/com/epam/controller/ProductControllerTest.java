package com.epam.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.model.Product;
import com.epam.model.SubCategory;
import com.epam.service.ProductServiceImpl;

class ProductControllerTest {

	@Mock
	ProductServiceImpl productService;
	
	@InjectMocks
	ProductController productController;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	static SubCategory mobiles;
	static Product apple;
	static List<Product> products;
	@BeforeAll
	public static void setInitVars() {
		apple = new Product();
		apple.setProductId((long) 1);
		apple.setProductName("Apple");
		apple.setQuantity((long) 10);
		apple.setSubCategory(mobiles);
		apple.setProductPrice((double) 1000);
		products= new ArrayList<>();
		products.add(apple);
		mobiles = new SubCategory();
		mobiles.setSubCategoryId((long) 1);
		mobiles.setSubCategoryName("Mobiles");
	}
	
	@Test
	void testGetAllProducts() {
		when(productService.getAllProducts()).thenReturn(products);
		Assertions.assertNotNull(productController.getAllProducts());
	}

	@Test
	void testGetProductById() {
		when(productService.getProductById(apple.getProductId())).thenReturn(apple);
		Assertions.assertNotNull(productController.getProductById(apple.getProductId()));
	}

	@Test
	void testGetProductsBasedOnSubCategory() {
		when(productService.getProductsBasedOnSubCategory(mobiles.getSubCategoryId())).thenReturn(products);
		Assertions.assertNotNull(productController.getProductsBasedOnSubCategory(mobiles.getSubCategoryId()));
	}

}
