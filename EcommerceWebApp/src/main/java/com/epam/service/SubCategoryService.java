package com.epam.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.model.SubCategory;

public interface SubCategoryService {

	List<SubCategory> getSubCategoriesBasedOnCategory(String categoryOption) throws ClassNotFoundException, SQLException; 
}
