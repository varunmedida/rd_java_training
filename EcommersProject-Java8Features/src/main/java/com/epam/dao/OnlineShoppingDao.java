package com.epam.dao;

import java.util.ArrayList;

import com.epam.model.Cart;
import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.SubCategory;

public interface OnlineShoppingDao {

	ArrayList<Category> getAllCategories();

	ArrayList<SubCategory> displaySubCategoriesBasedOnCategory(int categoryOption);

	ArrayList<Product> diplayProductsBasedOnSubCategory(int subCategoryOption);

	void addProductToCart(int subCategoryOption,int productOption, int quantityToAdd);

	ArrayList<Cart> viewCart();

	void removeProductFromCart(int productId);

	void updateInventoryStock(ArrayList<Cart> cartList);



}
