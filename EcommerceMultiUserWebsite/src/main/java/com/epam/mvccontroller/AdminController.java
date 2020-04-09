package com.epam.mvccontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.epam.model.Category;
import com.epam.model.SubCategory;

@Controller
public class AdminController {

	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/admin/addsubcategory")
	public RedirectView addSubCategory(RedirectView redirectView, @RequestParam Long categoryId,
			@RequestParam String subCategoryName) {
		final String uri = "http://localhost:9091/api/categories";
		SubCategory subCategory = new SubCategory();
		subCategory.setSubCategoryName(subCategoryName);
		restTemplate.postForEntity(uri + "/" + categoryId + "/subcategories", subCategory, Category.class);
		redirectView.setUrl("http://localhost:9091/admin/subcategorymanagement");
		return redirectView;
	}

	@PostMapping("/admin/updatesubcategory")
	public RedirectView updateSubCategory(RedirectView redirectView, @RequestParam Long subCategoryId,
			@RequestParam String subCategoryName) {
		final String uri = "http://localhost:9091/api/subcategories";
		SubCategory subCategory = new SubCategory();
		subCategory.setSubCategoryName(subCategoryName);
		restTemplate.put(uri + "/" + subCategoryId, subCategory);
		redirectView.setUrl("http://localhost:9091/admin/subcategorymanagement");
		return redirectView;
	}

	@PostMapping("/admin/deletesubcategory")
	public RedirectView deleteSubCategory(RedirectView redirectView, @RequestParam Long subCategoryId) {
		final String uri = "http://localhost:9091/api/subcategories";
		restTemplate.delete(uri + "/" + subCategoryId);
		redirectView.setUrl("http://localhost:9091/admin/subcategorymanagement");
		return redirectView;
	}

	@PostMapping("/admin/addcategory")
	public RedirectView addCategory(RedirectView redirectView, @RequestParam String categoryName) {
		final String uri = "http://localhost:9091/api/categories";
		Category category = new Category();
		category.setCategoryName(categoryName);
		restTemplate.postForEntity(uri, category, Category.class);
		redirectView.setUrl("http://localhost:9091/admin/categorymanagement");
		return redirectView;
	}

	@PostMapping("/admin/deletecategory")
	public RedirectView deleteCategory(RedirectView redirectView, @RequestParam Long categoryId) {
		final String uri = "http://localhost:9091/api/categories";
		restTemplate.delete(uri + "/" + categoryId);
		redirectView.setUrl("http://localhost:9091/admin/categorymanagement");
		return redirectView;
	}

	@PostMapping("/admin/updatecategory")
	public RedirectView updateCategory(RedirectView redirectView, @RequestParam Long categoryId,
			@RequestParam String categoryName) {
		final String uri = "http://localhost:9091/api/categories";
		Category category = new Category();
		category.setCategoryName(categoryName);
		restTemplate.put(uri + "/" + categoryId, category);
		redirectView.setUrl("http://localhost:9091/admin/categorymanagement");
		return redirectView;
	}

	@GetMapping("/admin/categorymanagement")
	public ModelAndView displayAddCategoryPage(ModelAndView model) {
		final String uri = "http://localhost:9091/api/categories";
		List<Category> categories = restTemplate.getForObject(uri, List.class);
		model.addObject("categories", categories);
		model.setViewName("categorymanagement");
		return model;
	}

	@GetMapping("/admin/subcategorymanagement")
	public ModelAndView displayAddSubCategoryPage(ModelAndView model) {
		final String uri = "http://localhost:9091/api/categories";
		List<Category> categories = restTemplate.getForObject(uri, List.class);
		model.addObject("categories", categories);
		model.setViewName("subcategorymanagement");
		return model;
	}
	
	@GetMapping("/admin/productmanagement")
	public ModelAndView displayProductManagementPage(ModelAndView model) {
		final String uri = "http://localhost:9091/api/categories";
		List<Category> categories = restTemplate.getForObject(uri, List.class);
		model.addObject("categories", categories);
		model.setViewName("productmanagement");
		return model;
	}

}
