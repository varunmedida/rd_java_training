package com.epam.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.epam.dao.CategoryDao;
import com.epam.model.Category;

class CategoryServiceImplTest {

	@InjectMocks
	CategoryServiceImpl categoryService;
	@Mock
	CategoryDao categoryDao;

	static Category firstCategory;
	static Category secondCategory;
	static List<Category> categories;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	public static void setInitVars() {
		categories = new ArrayList<>();
		firstCategory = new Category();
		firstCategory.setCategoryId((long) 1);
		firstCategory.setCategoryName("Electronics");
		secondCategory = new Category();
		secondCategory.setCategoryId((long) 2);
		secondCategory.setCategoryName("Fashion");
		categories.add(firstCategory);
		categories.add(secondCategory);
	}

	@Test
	void testGetAllCategories() {
		Mockito.when(categoryDao.getAllCategories()).thenReturn(categories);
		Assertions.assertEquals(categories.size(), categoryService.getAllCategories().size());
	}

}
