package com.epam.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.dao.OnlineShoppingDao;
import com.epam.dao.OnlineShoppingDaoImpl;
import com.epam.model.Product;

public class ProductServiceImpl implements ProductService {

	OnlineShoppingDao dao = new OnlineShoppingDaoImpl();

	@Override
	public List<Product> getProductsBasedOnSubCategory(String subCategoryOption)
			throws ClassNotFoundException, SQLException {
		return dao.getProductsBasedOnSubCategory(subCategoryOption);

	}

}
