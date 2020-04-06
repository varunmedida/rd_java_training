package com.epam.service;

import javax.validation.Valid;

import com.epam.model.Category;
import com.epam.model.SubCategory;

public interface AdminCatalogService {

	Category addCategory(@Valid Category category);

	Category updateCategory(Long categoryId, @Valid Category category);

	Category deleteCategory(Long categoryId);

	SubCategory addSubCategoryBasedOnCategory(Long categoryId, @Valid SubCategory subCategory);

}
