package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.dao.OnlineShoppingDaoImpl;
import com.epam.model.Category;
import com.epam.model.SubCategory;

class SubCategoryServiceImplTest {

	@Mock
	OnlineShoppingDaoImpl dao;

	@InjectMocks
	SubCategoryServiceImpl subCategoryService;

	@BeforeEach
	void initialize() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetSubCategoriesBasedOnCategory() {
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("abc");
		SubCategory subCategory = new SubCategory();
		subCategory.setCategory(category);
		subCategory.setSubCategoryId(1);
		subCategory.setSubCategoryName("Mobiles");
		List<SubCategory> subCategories = new ArrayList<>();
		subCategories.add(subCategory);
		category.setSubCategories(subCategories);

		when(dao.getSubCategoriesBasedOnCategory(1)).thenReturn(subCategories);
		assertEquals(subCategories, subCategoryService.getSubCategoriesBasedOnCategory(1));

	}

}
