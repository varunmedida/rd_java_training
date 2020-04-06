package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.model.Product;
import com.epam.service.ProductService;

@RestController
@RequestMapping("/ecommerce")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
		return ResponseEntity.ok(productService.getProductById(productId));
	}

	@GetMapping("/products/subcategories/{subCategoryId}")
	public ResponseEntity<List<Product>> getProductsBasedOnSubCategory(@PathVariable Long subCategoryId) {
		return ResponseEntity.ok(productService.getProductsBasedOnSubCategory(subCategoryId));
	}

}
