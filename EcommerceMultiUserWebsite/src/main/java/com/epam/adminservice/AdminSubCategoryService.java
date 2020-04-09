package com.epam.adminservice;

import com.epam.model.SubCategory;

public interface AdminSubCategoryService {

	SubCategory addSubCategory(Long categoryId, SubCategory subCategory);

	SubCategory deleteSubCategory(Long subCategoryId);

	SubCategory updateSubCategory(Long subCategoryId, SubCategory subCategory);
}
