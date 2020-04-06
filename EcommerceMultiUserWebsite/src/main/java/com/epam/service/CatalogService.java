package com.epam.service;

import java.util.List;

import com.epam.model.Category;
import com.epam.model.SubCategory;

public interface CatalogService {

	List<Category> getCategories();

	Category getCategoryById(Long categoryId);

	List<SubCategory> getSubCategoriesByCategoryId(Long categoryId);

	SubCategory getSubCategoryBasedOnId(Long subCategoryId);

}
