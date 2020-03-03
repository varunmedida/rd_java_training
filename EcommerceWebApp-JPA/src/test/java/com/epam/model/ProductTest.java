package com.epam.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ProductTest {

	Category category = new Category("Electronics");
	SubCategory subCategory = new SubCategory("Mobiles", category);
	Product product = new Product(subCategory, "Apple", 5000, 10);

	@Test
	void testProduct() {
		product = new Product();
		assertNotNull(product);
	}

	@Test
	void testGetProductId() {
		product.setProductId(1);
		assertEquals(1, product.getProductId());
	}

	@Test
	void testGetSubCategory() {
		subCategory.setSubCategoryId(1);
		product.setSubCategory(subCategory);
		assertEquals(1, product.getSubCategory().getSubCategoryId());
	}

	@Test
	void testGetProductName() {
		product.setProductName("abc");
		assertEquals("abc", product.getProductName());
	}

	@Test
	void testGetProductPrice() {
		product.setProductPrice(1);
		assertEquals(1, product.getProductPrice());
	}

	@Test
	void testGetQuantity() {
		product.setQuantity(1);
		assertEquals(1, product.getQuantity());
	}

}
