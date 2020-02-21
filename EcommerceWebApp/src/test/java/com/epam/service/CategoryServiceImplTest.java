package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.epam.model.Category;

class CategoryServiceImplTest {

	CategoryServiceImpl categoryService;

	@Test
	void test() throws ClassNotFoundException, SQLException, IOException {

		categoryService = new CategoryServiceImpl();
		List<Category> list = new ArrayList<>();
		List<Category> expectedCategories = Mockito.spy(list);
		expectedCategories.add(new Category(1, "Electronics"));
		expectedCategories.add(new Category(2, "Fashion"));
		Mockito.verify(expectedCategories).add(new Category(1, "Electronics"));
		Mockito.verify(expectedCategories).add(new Category(2, "Fashion"));
		for (int i = 0; i < expectedCategories.size(); i++) {
			assertTrue(expectedCategories.get(i).equals(categoryService.getAllCategories().get(i)));
		}
	}

}
