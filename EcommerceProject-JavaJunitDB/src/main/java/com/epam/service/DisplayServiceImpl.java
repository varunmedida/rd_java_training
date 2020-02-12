package com.epam.service;

import java.util.List;

import com.epam.beans.Category;
import com.epam.beans.Product;
import com.epam.beans.SubCategory;
import com.epam.dao.OnlineShoppingDao;
import com.epam.dao.OnlineShoppingDaoImpl;

public class DisplayServiceImpl implements DisplayService {

	OnlineShoppingDao dao = new OnlineShoppingDaoImpl();

	@Override
	public List<Category> displayAllCategories() {
		return dao.displayAllCategories();
	}

	@Override
	public List<SubCategory> displaySubCategoriesBasedOnCategory(int categoryOption) {
		return dao.displaySubCategoriesBasedOnCategory(categoryOption);
	}

	@Override
	public List<Product> displayProductsBasedOnSubCategory(int subCategoryOption) {
		return dao.displayProductsBasedOnSubCategory(subCategoryOption);
	}

}
