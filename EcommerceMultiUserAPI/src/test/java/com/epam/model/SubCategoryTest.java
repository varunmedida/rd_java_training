package com.epam.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class SubCategoryTest {
	
	@InjectMocks
	SubCategory subCategory;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		subCategory = new SubCategory();
	}

	@Test
	void testGetSubCategoryName() {
		subCategory.setSubCategoryName("Mobiles");
		subCategory.getSubCategoryName();
	}

	@Test
	void testGetCategory() {
		Category category = new Category();
		subCategory.setCategory(category);
		subCategory.getCategory();
	}

	@Test
	void testToString() {
		Assertions.assertNotNull(subCategory.toString());
	}

}
