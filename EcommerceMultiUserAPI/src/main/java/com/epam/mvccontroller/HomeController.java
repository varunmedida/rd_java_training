package com.epam.mvccontroller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.service.ProductService;

@Controller
public class HomeController {

	private static final Logger LOGGER = LogManager.getLogger(HomeController.class);
	@Autowired
	ProductService productService;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping({ "/", "/index" })
	public ModelAndView homePage(ModelAndView model) {
		LOGGER.info("------Calling Product Service getAllProducts Method-------");
		model.addObject("products", productService.getAllProducts());
		LOGGER.info("------SuccessFully Executed Product Service-------");
		model.setViewName("index");
		return model;
	}
	
	@GetMapping("/admin")
	public ModelAndView adminPage(ModelAndView model) {
		model.setViewName("admin");
		return model;
	}

}
