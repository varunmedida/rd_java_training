package com.epam.controller;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.epam.adminservice.AdminSubCategoryService;
import com.epam.model.SubCategory;

@RestController
@RequestMapping(path = "/api")
public class AdminSubCategoryController {

	private final Logger log = LogManager.getLogger(AdminSubCategoryController.class);
	
	@Autowired
	AdminSubCategoryService adminSubCategoryService;
	
	@PostMapping("/categories/{categoryId}/subcategories")
	public ResponseEntity<SubCategory> addSubcategory(@PathVariable Long categoryId,@RequestBody SubCategory subCategory){
		log.info("Add Subcategory");
		SubCategory savedSubCategory = adminSubCategoryService.addSubCategory(categoryId,subCategory);
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedSubCategory.getSubCategoryId()).toUri();
		return ResponseEntity.created(uri).body(savedSubCategory);
	}
	
	@DeleteMapping("/subcategories/{subCategoryId}")
	public ResponseEntity<SubCategory> deleteSubCategory(@PathVariable Long subCategoryId){
		SubCategory deletedSubCategory = adminSubCategoryService.deleteSubCategory(subCategoryId);
		return ResponseEntity.accepted().body(deletedSubCategory);
	}
	
	@PutMapping("/subcategories/{subCategoryId}")
	public ResponseEntity<SubCategory> updateSubCategory(@PathVariable Long subCategoryId,@RequestBody SubCategory subCategory){
		SubCategory updatedSubCategory = adminSubCategoryService.updateSubCategory(subCategoryId,subCategory);
		return ResponseEntity.accepted().body(updatedSubCategory);
	}
	
}
