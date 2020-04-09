package com.epam.adminservice;

import com.epam.model.Category;

public interface AdminCategoryService {

	Category addCategory(Category category);

	Category deleteCategory(Long categoryId);

	Category updateCategory(Long categoryId, Category category);

}
