package com.epam.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.epam.model.Category;

public interface CategoryService {

	List<Category> getAllCategories() throws SQLException, IOException, ClassNotFoundException; 
}
