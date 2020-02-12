package com.epam.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {

	Category category;
	
	@Test
	void testGetCategoryId() {
		category.setCategoryId(1);
		assertEquals(1, category.getCategoryId());
	}

	@Test
	void testGetCategoryName() {
		category.setCategoryName("Electronics");
		assertEquals("Electronics",category.getCategoryName());
	}

	@BeforeEach
	void testCategory() {
		category = new Category(1, "Electronics");
		assertNotNull(category);
	}

	@Test
	void testToString() {
		assertEquals(category.getCategoryId() + ". " + category.getCategoryName(), category.toString());
	}

}
