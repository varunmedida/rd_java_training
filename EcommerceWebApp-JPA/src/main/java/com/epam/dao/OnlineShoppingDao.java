package com.epam.dao;

import java.util.List;
import java.util.Set;

import com.epam.model.CartItem;
import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.SubCategory;

public interface OnlineShoppingDao {

	List<Category> getAllCategories();

	List<SubCategory> getSubCategoriesBasedOnCategory(int categoryOption);

	List<Product> getProductsBasedOnSubCategory(int subCategoryOption);

	Product getProductById(int productOption);

	void addToCart(Product product, int quantityAdded);

	Set<CartItem> viewCart();
}
