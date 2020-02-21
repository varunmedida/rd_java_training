package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.epam.model.SubCategory;

class SubCategoryServiceImplTest {

	SubCategoryServiceImpl subCategoryService;
	@Test
	void testGetSubCategoriesBasedOnCategory() throws ClassNotFoundException, SQLException {
		subCategoryService = new SubCategoryServiceImpl();
		List<SubCategory> list = new ArrayList<>();
		List<SubCategory> expectedSubCategories = Mockito.spy(list);
		expectedSubCategories.add(new SubCategory(1, 1, "Mobiles"));
		expectedSubCategories.add(new SubCategory(2, 1, "Laptops"));
		expectedSubCategories.add(new SubCategory(3, 1, "Home Appliance"));
		Mockito.verify(expectedSubCategories).add(new SubCategory(1, 1, "Mobiles"));
		Mockito.verify(expectedSubCategories).add(new SubCategory(2, 1, "Laptops"));
		Mockito.verify(expectedSubCategories).add(new SubCategory(3, 1, "Home Appliance"));
		for (int i = 0; i < expectedSubCategories.size(); i++) {
			assertTrue(expectedSubCategories.get(i).equals(subCategoryService.getSubCategoriesBasedOnCategory("1").get(i)));
		}
	}

}
