package com.epam.dao;

import java.util.List;

import com.epam.beans.Cart;
import com.epam.beans.Category;
import com.epam.beans.Product;
import com.epam.beans.SubCategory;

public interface OnlineShoppingDao {

	List<Category> displayAllCategories();

	List<SubCategory> displaySubCategoriesBasedOnCategory(int categoryOption);

	List<Product> displayProductsBasedOnSubCategory(int subCategoryOption);

	void addProductToCart(int productOption, int subCategoryOption, int quantityToBeAdded);

	List<Cart> viewCart();

	void removeProductFromCart(int productId);

}
