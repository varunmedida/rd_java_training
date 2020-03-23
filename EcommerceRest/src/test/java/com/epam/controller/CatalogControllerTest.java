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

import com.epam.model.Category;
import com.epam.model.SubCategory;
import com.epam.service.CatalogServiceImpl;

class CatalogControllerTest {

	@Mock
	CatalogServiceImpl catalogService;

	@InjectMocks
	CatalogController catalogController;

	static Category electronics;
	static Category fashion;
	static SubCategory mobiles;
	static List<Category> categories;
	static List<SubCategory> subcategories;

	@BeforeAll
	public static void setInitVars() {
		categories = new ArrayList<>();
		subcategories = new ArrayList<>();
		electronics = new Category();
		fashion = new Category();
		electronics.setCategoryId((long) 1);
		electronics.setCategoryName("Electronics");
		fashion.setCategoryId((long) 2);
		fashion.setCategoryName("Fashion");
		mobiles = new SubCategory();
		mobiles.setSubCategoryId((long) 1);
		mobiles.setSubCategoryName("Mobiles");
		subcategories.add(mobiles);
		electronics.setSubCategories(subcategories);
		categories.add(electronics);
		categories.add(fashion);
	}

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetCategories() {
		when(catalogService.getCategories()).thenReturn(categories);
		Assertions.assertNotNull(catalogController.getCategories());
	}

	@Test
	void testGetCategoryById() {
		when(catalogService.getCategoryById(electronics.getCategoryId())).thenReturn(electronics);
		Assertions.assertNotNull(catalogController.getCategoryById(electronics.getCategoryId()));
	}

	@Test
	void testGetSubCategoriesByCategoryId() {
		when(catalogService.getSubCategoriesByCategoryId(electronics.getCategoryId())).thenReturn(subcategories);
		Assertions.assertNotNull(catalogController.getSubCategoriesByCategoryId(electronics.getCategoryId()));
	}

	@Test
	void testGetSubCategoryBasedOnId() {
		when(catalogService.getSubCategoryBasedOnId(mobiles.getSubCategoryId())).thenReturn(mobiles);
		Assertions.assertNotNull(catalogController.getSubCategoryBasedOnId(mobiles.getSubCategoryId()));
	}

}
