package com.epam.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.dao.OnlineShoppingDao;
import com.epam.dao.OnlineShoppingDaoImpl;
import com.epam.model.SubCategory;

public class SubCategoryServiceImpl implements SubCategoryService {

	OnlineShoppingDao dao = new OnlineShoppingDaoImpl();

	@Override
	public List<SubCategory> getSubCategoriesBasedOnCategory(String categoryOption) throws ClassNotFoundException, SQLException {
		return dao.getSubCategoriesBasedOnCategory(categoryOption);
	}
	

}
