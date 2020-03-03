package com.epam.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CategoryTest {

	Category category = new Category();

	@Test
	void testCategory() {
		assertNotNull(category);
	}

	@Test
	void testGetCategoryId() {
		int expected = 1;
		category.setCategoryId(1);
		assertEquals(expected, category.getCategoryId());
	}

	@Test
	void testGetCategoryName() {
		String expected = "Electronics";
		category.setCategoryName("Electronics");
		assertEquals(expected, category.getCategoryName());
	}

	@Test
	void testGetSubCategories() {
		List<SubCategory> subCategories = new ArrayList<>();
		subCategories.add(new SubCategory("Mobiles", category));
		category.setSubCategories(subCategories);
		assertEquals(subCategories.size(), category.getSubCategories().size());
	}

	@Test
	void testToString() {
		assertNotNull(category.toString());
	}

	@Test
	void testCategoryString() {
		Category category2 = new Category("Electronics");
		assertNotNull(category2);
	}

}
