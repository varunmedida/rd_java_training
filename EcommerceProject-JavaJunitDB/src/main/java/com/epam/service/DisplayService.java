package com.epam.service;

import java.util.List;

import com.epam.beans.Category;
import com.epam.beans.Product;
import com.epam.beans.SubCategory;

public interface DisplayService {

	List<Category> displayAllCategories();

	List<SubCategory> displaySubCategoriesBasedOnCategory(int categoryOption);

	List<Product> displayProductsBasedOnSubCategory(int subCategoryOption);

}
