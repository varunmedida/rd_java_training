package com.epam.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*; 
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.dao.OnlineShoppingDao;
import com.epam.model.Category;

class CategoryServiceImplTest {
	
	@Mock
	OnlineShoppingDao dao;

	@InjectMocks
	CategoryServiceImpl categoryService;

	@BeforeEach
	void initialize() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetAllCategories() {
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("abc");
		List<Category> categories = new ArrayList<>();
		categories.add(category);
		when(dao.getAllCategories()).thenReturn(categories);
		assertEquals(categories,categoryService.getAllCategories());
	}

}
