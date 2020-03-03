package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
	@Mock
	Category category;
	@Mock
	List<Category> categories;

	@InjectMocks
	CategoryServiceImpl categoryService;

	@BeforeEach
	void initialize() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetAllCategories() {
		category.setCategoryId(1);
		category.setCategoryName("abc");
		categories.add(category);
		when(dao.getAllCategories()).thenReturn(categories);
		assertEquals(categories,categoryService.getAllCategories());
	}

}
