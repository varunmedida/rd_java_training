package com.epam.dao;

import java.util.List;

import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.SubCategory;

public interface OnlineShoppingDao {

	List<Category> getAllCategories();

	List<SubCategory> getSubCategoriesBasedOnCategory(String categoryOption);

	List<Product> getProductsBasedOnSubCategory(String subCategoryOption); 
}
