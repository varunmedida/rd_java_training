package com.epam.mvccontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	@GetMapping("/admin/addproduct")
	public ModelAndView displayAddProductPage(ModelAndView model) {
		model.setViewName("addproduct");
		return model;
	}
	
	@GetMapping("/admin/addcategory")
	public ModelAndView displayAddCategoryPage(ModelAndView model) {
		model.setViewName("addcategory");
		return model;
	}
	
}
