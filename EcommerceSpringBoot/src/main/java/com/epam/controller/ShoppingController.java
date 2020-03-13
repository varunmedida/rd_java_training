package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.service.CategoryService;
import com.epam.service.ProductService;

@Controller
public class ShoppingController {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/shopbycategory")
	public ModelAndView shopbycatgeory(ModelAndView model) {
		model.addObject("products", productService.getAllProducts());
		model.addObject("categories", categoryService.getAllCategories());
		model.setViewName("products");
		return model;
	}

	@PostMapping("/")
	public ModelAndView getProductsBasedOnSubCategoryId(ModelAndView model, @RequestParam Long subCategoryId) {
		model.addObject("categories", categoryService.getAllCategories());
		model.addObject("products", productService.getProductsBasedOnSubCategoryId(subCategoryId));
		model.setViewName("products");
		return model;
	}
}
