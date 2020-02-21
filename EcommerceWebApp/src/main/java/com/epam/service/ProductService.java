package com.epam.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.model.Product;

public interface ProductService {

	List<Product> getProductsBasedOnSubCategory(String subCategoryOption) throws ClassNotFoundException, SQLException;
}
