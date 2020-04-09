package com.epam.controller;

import java.net.URI;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.epam.adminservice.AdminCategoryService;
import com.epam.model.Category;
import com.epam.service.CatalogService;

@RestController
@RequestMapping(path = "/api")
public class AdminCategoryController {
	
	private final Logger log = LogManager.getLogger(AdminCategoryController.class);
	
	@Autowired
	AdminCategoryService adminCategoryService;
	
	@Autowired
	CatalogService catalogService;

	@PostMapping("/categories")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		log.info("Add Category");
		Category savedCategory = adminCategoryService.addCategory(category);
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCategory.getCategoryId()).toUri();
		return ResponseEntity.created(uri).body(savedCategory);
	}
	
	@GetMapping(path = "/categories",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Category> getCategories(){
		return catalogService.getCategories();
	}
	
	@DeleteMapping("/categories/{categoryId}")
	public ResponseEntity<Category> deleletCategory(@PathVariable Long categoryId){
		Category deletedCategory = adminCategoryService.deleteCategory(categoryId);
		return ResponseEntity.accepted().body(deletedCategory);
	}
	
	@PutMapping("/categories/{categoryId}")
	public ResponseEntity<Category> deleletCategory(@PathVariable Long categoryId,@RequestBody Category category){
		Category updatedCategory = adminCategoryService.updateCategory(categoryId,category);
		return ResponseEntity.accepted().body(updatedCategory);
	}
}
