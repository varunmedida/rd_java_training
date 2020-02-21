package com.epam.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.SubCategory;

public interface OnlineShoppingDao {

	List<Category> getAllCategories() throws SQLException, IOException, ClassNotFoundException;

	List<SubCategory> getSubCategoriesBasedOnCategory(String categoryOption) throws ClassNotFoundException, SQLException;

	List<Product> getProductsBasedOnSubCategory(String subCategoryOption) throws ClassNotFoundException, SQLException; 
}
