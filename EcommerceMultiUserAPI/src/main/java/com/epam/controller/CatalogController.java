package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.model.Category;
import com.epam.model.SubCategory;
import com.epam.service.CatalogService;

@RestController
@RequestMapping("/ecommerce")
public class CatalogController {

	@Autowired
	CatalogService catalogService;

	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getCategories() {
		return ResponseEntity.ok(catalogService.getCategories());
	}

	@GetMapping("/categories/{categoryId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
		return ResponseEntity.ok(catalogService.getCategoryById(categoryId));
	}

	@GetMapping("/categories/{categoryId}/subcategories")
	public ResponseEntity<List<SubCategory>> getSubCategoriesByCategoryId(@PathVariable Long categoryId) {
		return ResponseEntity.ok(catalogService.getSubCategoriesByCategoryId(categoryId));
	}

	@GetMapping("/subcategories/{subCategoryId}")
	public ResponseEntity<SubCategory> getSubCategoryBasedOnId(@PathVariable Long subCategoryId) {
		return ResponseEntity.ok(catalogService.getSubCategoryBasedOnId(subCategoryId));
	}

}
