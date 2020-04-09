package com.epam.adminservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.exception.DataNotFoundException;
import com.epam.model.Category;
import com.epam.repository.CategoryRepository;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category deleteCategory(Long categoryId) {
		Category deletedCategory = categoryRepository.findById(categoryId).orElseThrow(()-> new DataNotFoundException("Category Not found to delete"));
		categoryRepository.delete(deletedCategory);
		return deletedCategory;
	}

	@Override
	public Category updateCategory(Long categoryId, Category category) {
		Category updatedCategory = categoryRepository.findById(categoryId).orElseThrow(()-> new DataNotFoundException("Category Not found to delete"));
		updatedCategory.setCategoryName(category.getCategoryName());
		return categoryRepository.save(updatedCategory);
	}

	
}
