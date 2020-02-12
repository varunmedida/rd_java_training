package com.epam.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {

	Product product;
	@Test
	void testGetProductId() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSubCategoryId() {
		fail("Not yet implemented");
	}

	@Test
	void testGetProductName() {
		fail("Not yet implemented");
	}

	@Test
	void testGetProductPrice() {
		fail("Not yet implemented");
	}

	@Test
	void testGetQuantityOfStock() {
		fail("Not yet implemented");
	}

	@BeforeEach
	void testProduct() {
		product = new Product(1, 1, "Apple", 8000, 10);
		assertNotNull(product);
	}

	@Test
	void testToString() {
		assertEquals(product.getProductId() + " " + product.getProductName()+ " " + product.getProductPrice() + " " + product.getQuantityOfStock(),product.toString());
	}

}
