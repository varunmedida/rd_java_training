package com.epam.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class SubCategoryTest {

	Category category = new Category();
	SubCategory subCategory;

	@Test
	void testSubCategory() {
		subCategory = new SubCategory();
		assertNotNull(subCategory);
	}

	@Test
	void testGetSubCategoryId() {
		subCategory = new SubCategory();
		subCategory.setSubCategoryId(1);
		assertEquals(1, subCategory.getSubCategoryId());
	}

	@Test
	void testGetSubCategoryName() {
		subCategory = new SubCategory();
		subCategory.setSubCategoryName("Mobiles");
		assertEquals("Mobiles", subCategory.getSubCategoryName());
	}

	@Test
	void testGetCategory() {
		category = new Category("abc");
		subCategory= new SubCategory("abc", category);
		subCategory.setCategory(category);
		assertNotNull(subCategory.getCategory());
	}


	@Test
	void testGetProducts() {
		category = new Category("abc");
		subCategory = new SubCategory("abc", category);
		Product product = new Product(subCategory,"apple",5000,10);
		List<Product> products = new ArrayList<>();
		products.add(product);
		subCategory.setProducts(products);
		assertEquals(products.size(),subCategory.getProducts().size());
	}

	@Test
	void testToString() {
		subCategory = new SubCategory("abc", category);
		assertNotNull(subCategory.toString());
		
	}


}
