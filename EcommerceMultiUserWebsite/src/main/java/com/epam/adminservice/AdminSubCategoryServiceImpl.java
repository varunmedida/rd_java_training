package com.epam.adminservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.exception.DataNotFoundException;
import com.epam.model.Category;
import com.epam.model.SubCategory;
import com.epam.repository.CategoryRepository;
import com.epam.repository.SubCategoryRepository;

@Service
public class AdminSubCategoryServiceImpl implements AdminSubCategoryService {

	@Autowired
	SubCategoryRepository subCategoryRespository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public SubCategory deleteSubCategory(Long subCategoryId) {
		SubCategory existingSubCategory= subCategoryRespository.findById(subCategoryId).orElseThrow(()-> new DataNotFoundException("Sub Category not found"));
		subCategoryRespository.delete(existingSubCategory);
		return existingSubCategory;
	}

	@Override
	public SubCategory updateSubCategory(Long subCategoryId, SubCategory subCategory) {
		SubCategory existingSubCategory= subCategoryRespository.findById(subCategoryId).orElseThrow(()-> new DataNotFoundException("Sub Category not found"));
		existingSubCategory.setSubCategoryName(subCategory.getSubCategoryName());
		return subCategoryRespository.save(existingSubCategory);
	}

	@Override
	public SubCategory addSubCategory(Long categoryId, SubCategory subCategory) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new DataNotFoundException("Category Not found"));
		subCategory.setCategory(category);
		return subCategoryRespository.save(subCategory);
	}

}
