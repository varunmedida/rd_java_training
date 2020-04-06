package com.epam.admincontroller;

import java.net.URI;

import javax.validation.Valid;

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

import com.epam.model.Category;
import com.epam.model.SubCategory;
import com.epam.service.AdminCatalogService;

@RestController
@RequestMapping("/admin/categories")
public class AdminCatalogController {

	private final Logger log = LogManager.getLogger(AdminCatalogController.class);
	
	@Autowired
	AdminCatalogService adminCatalogService;
	
	@PostMapping
	public ResponseEntity<Category> addCategory(@RequestBody @Valid Category category) {
		log.info("Add Product");
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category)
				.toUri();
		return ResponseEntity.created(uri).body(adminCatalogService.addCategory(category));
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId,@RequestBody @Valid Category category){
		log.info("Update Category by Id:{}", categoryId);
		return ResponseEntity.accepted().body(adminCatalogService.updateCategory(categoryId,category));
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Category> deleteCategory(@PathVariable Long categoryId){
		log.info("Delete Category by Id:{}", categoryId);
		return ResponseEntity.accepted().body(adminCatalogService.deleteCategory(categoryId));
	}
	
	@PostMapping("/{categoryId}/subcategories")
	public ResponseEntity<SubCategory> addSubCategoryBasedOnCategory(@PathVariable Long categoryId,@RequestBody @Valid SubCategory subCategory){
		log.info("Add SubCategory by categoryId:{}", categoryId);
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(subCategory)
				.toUri();
		return ResponseEntity.created(uri).body(adminCatalogService.addSubCategoryBasedOnCategory(categoryId,subCategory));
	}
}
