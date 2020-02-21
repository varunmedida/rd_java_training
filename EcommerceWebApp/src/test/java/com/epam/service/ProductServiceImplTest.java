package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.epam.model.Product;

class ProductServiceImplTest {

	ProductServiceImpl productService;
	@Test
	void testGetProductsBasedOnSubCategory() throws ClassNotFoundException, SQLException {
	productService = new ProductServiceImpl();
	List<Product> list = new ArrayList<>();
	List<Product> expectedProducts = Mockito.spy(list);
	expectedProducts.add(new Product(1, 1, "Apple", 8000, 10));
	expectedProducts.add(new Product(2, 1, "Samsung", 6000, 7));
	expectedProducts.add(new Product(3, 1, "Moto", 4000, 5));
	Mockito.verify(expectedProducts).add(new Product(1, 1, "Apple", 8000, 10));
	Mockito.verify(expectedProducts).add(new Product(2, 1, "Samsung", 6000, 7));
	Mockito.verify(expectedProducts).add(new Product(3, 1, "Moto", 4000, 5));
	for (int i = 0; i < expectedProducts.size(); i++) {
		assertTrue(expectedProducts.get(i).equals(productService.getProductsBasedOnSubCategory("1").get(i)));
	}
	
	}

}
