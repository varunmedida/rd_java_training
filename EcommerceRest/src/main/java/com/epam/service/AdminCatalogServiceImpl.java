package com.epam.service;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.exception.DataNotFoundException;
import com.epam.model.Category;
import com.epam.model.SubCategory;
import com.epam.repository.CategoryRepository;
import com.epam.repository.SubCategoryRepository;

@Service
public class AdminCatalogServiceImpl implements AdminCatalogService {

	private final Logger log = LogManager.getLogger(AdminCatalogServiceImpl.class);
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	SubCategoryRepository subCategoryRepository;
	
	@Override
	public Category addCategory(@Valid Category category) {
		log.info("Add Category Service");
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Long categoryId, @Valid Category category) {
		log.info("Update Category by Id Service:{}", categoryId);
		Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(()-> new DataNotFoundException("Category Not found to Update"));
		existingCategory.setCategoryName(category.getCategoryName());
		return categoryRepository.save(existingCategory);
	}

	@Override
	public Category deleteCategory(Long categoryId) {
		log.info("Delete Category by Id Service:{}", categoryId);
		Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(()-> new DataNotFoundException("Category Not found to Delete"));
		categoryRepository.delete(existingCategory);
		return existingCategory;
	}

	@Override
	public SubCategory addSubCategoryBasedOnCategory(Long categoryId, @Valid SubCategory subCategory) {
		log.info("Add subcategory to categoryId service:{}", categoryId);
		subCategory.setCategory(categoryRepository.findById(categoryId).orElseThrow(()-> new DataNotFoundException("Category Not found to Delete")));
		return subCategoryRepository.save(subCategory);
	}
}
