package com.epam.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.epam.dao.OnlineShoppingDao;
import com.epam.dao.OnlineShoppingDaoImpl;
import com.epam.model.Category;

public class CategoryServiceImpl implements CategoryService {

	OnlineShoppingDao dao = new OnlineShoppingDaoImpl();

	@Override
	public List<Category> getAllCategories() throws SQLException, IOException, ClassNotFoundException {

		return dao.getAllCategories();

	}

}
