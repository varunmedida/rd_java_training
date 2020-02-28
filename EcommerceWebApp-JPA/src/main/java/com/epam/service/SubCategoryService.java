package com.epam.service;

import java.util.List;

import com.epam.model.SubCategory;

public interface SubCategoryService {

	List<SubCategory> getSubCategoriesBasedOnCategory(int categoryOption) ; 
}
