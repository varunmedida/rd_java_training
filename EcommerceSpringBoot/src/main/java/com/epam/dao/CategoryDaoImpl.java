package com.epam.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.model.Category;
import com.epam.repository.CategoryRepository;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

}
