package com.epam.mvccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.service.CatalogService;
import com.epam.service.ProductService;

@Controller
public class ShoppingController {

	@Autowired
	ProductService productService;
	@Autowired
	CatalogService catalogService;
	
	@GetMapping("/shopbycategory")
	public ModelAndView shopbycatgeory(ModelAndView model) {
		model.addObject("products", productService.getAllProducts());
		model.addObject("categories", catalogService.getCategories());
		model.setViewName("products");
		return model;
	}

	@PostMapping("/")
	public ModelAndView getProductsBasedOnSubCategoryId(ModelAndView model, @RequestParam Long subCategoryId) {
		model.addObject("categories", catalogService.getCategories());
		model.addObject("products", productService.getProductsBasedOnSubCategory(subCategoryId));
		model.setViewName("products");
		return model;
	}
}
