package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.exception.DataNotFoundException;
import com.epam.model.Category;
import com.epam.model.SubCategory;
import com.epam.repository.CategoryRepository;
import com.epam.repository.SubCategoryRepository;

@Service
public class CatalogServiceImpl implements CatalogService{

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	SubCategoryRepository subCategoryRepository;
	
	@Override
	public List<Category> getCategories(){
		List<Category> categories = categoryRepository.findAll();
		if(categories.isEmpty()) {
			throw new DataNotFoundException("Categories Not Present right now.");
		}
		return categories;
	}

	@Override
	public Category getCategoryById(Long categoryId) throws DataNotFoundException {
		return categoryRepository.findById(categoryId).orElseThrow(()-> new DataNotFoundException("Category Not Found"));
	}

	@Override
	public List<SubCategory> getSubCategoriesByCategoryId(Long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new DataNotFoundException("Category Not Found"));
		List<SubCategory> subCategories = category.getSubCategories();
		if(subCategories.isEmpty()) {
			throw new DataNotFoundException("Sub Categories not present in this category"); 
		}
		return subCategories;
	}

	@Override
	public SubCategory getSubCategoryBasedOnId(Long subCategoryId) {
		return subCategoryRepository.findById(subCategoryId).orElseThrow(()-> new DataNotFoundException("SubCategory Not Found"));
	}

}
