package com.epam.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.exception.DataNotFoundException;
import com.epam.model.Category;
import com.epam.model.SubCategory;
import com.epam.repository.CategoryRepository;
import com.epam.repository.SubCategoryRepository;

class CatalogServiceImplTest {

	@InjectMocks
	CatalogServiceImpl catalogService;
	@Mock
	CategoryRepository categoryRepository;
	@Mock
	SubCategoryRepository subCategoryRepository;
	@Mock
	Category category;

	static Category electronics;
	static Category fashion;
	static SubCategory mobiles;
	static List<SubCategory> subCategories;
	static List<Category> categories;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	public static void setInitVars() {
		subCategories = new ArrayList<>();
		categories = new ArrayList<>();
		categories.add(electronics);
		categories.add(fashion);
		electronics = new Category();
		fashion = new Category();
		electronics.setCategoryId((long) 1);
		electronics.setCategoryName("Electronics");
		fashion.setCategoryId((long) 2);
		fashion.setCategoryName("Fashion");
		mobiles = new SubCategory();
		mobiles.setSubCategoryId((long) 1);
		mobiles.setSubCategoryName("Mobiles");
		subCategories.add(mobiles);
		electronics.setSubCategories(subCategories);
	}

	@Test
	void testGetCategories() {
		when(categoryRepository.findAll()).thenReturn(categories);
		Assertions.assertEquals(categories.size(), catalogService.getCategories().size());
	}

	@Test
	void testGetCategoriesIfEmpty() {
		categories = new ArrayList<>();
		when(categoryRepository.findAll()).thenReturn(categories);
		Assertions.assertThrows(DataNotFoundException.class, () -> catalogService.getCategories());
	}

	@Test
	void testGetCategoryById() {
		Optional<Category> opCategory = Optional.of(electronics);
		when(categoryRepository.findById(electronics.getCategoryId())).thenReturn(opCategory);
		Assertions.assertEquals(electronics.getCategoryId(),
				catalogService.getCategoryById(electronics.getCategoryId()).getCategoryId());
	}

	@Test
	void testGetCategoryByIdWhenNotFound() {
		Optional<Category> opCategory = Optional.empty();
		when(categoryRepository.findById(electronics.getCategoryId())).thenReturn(opCategory)
				.thenThrow(DataNotFoundException.class);
		Assertions.assertThrows(DataNotFoundException.class,
				() -> catalogService.getCategoryById(electronics.getCategoryId()));
	}

	@Test
	void testGetSubCategoriesByCategoryId() {
		Optional<Category> opCategory = Optional.of(electronics);
		when(categoryRepository.findById(electronics.getCategoryId())).thenReturn(opCategory);
		when(category.getSubCategories()).thenReturn(subCategories).thenThrow(DataNotFoundException.class);
		Assertions.assertEquals(subCategories.size(),
				catalogService.getSubCategoriesByCategoryId(electronics.getCategoryId()).size());
	}

	@Test
	void testGetSubCategoriesByCategoryIdWhenNotFoundCategory() {
		Optional<Category> opCategory = Optional.empty();
		when(categoryRepository.findById(electronics.getCategoryId())).thenReturn(opCategory)
				.thenThrow(DataNotFoundException.class);
		Assertions.assertThrows(DataNotFoundException.class,
				() -> catalogService.getSubCategoriesByCategoryId(electronics.getCategoryId()));
	}

	@Test
	void testGetSubCategoriesByCategoryIdNotFound() {
		Optional<Category> opCategory = Optional.of(fashion);
		when(categoryRepository.findById(fashion.getCategoryId())).thenReturn(opCategory);
		List<SubCategory> emptySubCatgories = new ArrayList<>();
		when(category.getSubCategories()).thenReturn(emptySubCatgories);
		Assertions.assertThrows(DataNotFoundException.class,
				() -> catalogService.getSubCategoriesByCategoryId(fashion.getCategoryId()));
	}

	@Test
	void testGetSubCategoryBasedOnId() {
		Optional<SubCategory> opSubCategory = Optional.of(mobiles);
		when(subCategoryRepository.findById(mobiles.getSubCategoryId())).thenReturn(opSubCategory);
		Assertions.assertEquals(mobiles.getSubCategoryId(),
				catalogService.getSubCategoryBasedOnId(mobiles.getSubCategoryId()).getSubCategoryId());
	}

	@Test
	void testGetSubCategoryBasedOnIdWhenNotFound() {
		Optional<SubCategory> opSubCategory = Optional.empty();
		when(subCategoryRepository.findById(mobiles.getSubCategoryId())).thenReturn(opSubCategory)
				.thenThrow(DataNotFoundException.class);
		Assertions.assertThrows(DataNotFoundException.class,
				() -> catalogService.getSubCategoryBasedOnId(mobiles.getSubCategoryId()));
	}

}
