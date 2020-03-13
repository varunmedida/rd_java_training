package com.epam.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.service.ProductService;

@Controller
public class ProductController {

	private static final Logger LOGGER = LogManager.getLogger(ProductController.class);
	@Autowired
	ProductService productService;

	@PostMapping("productdetail")
	public ModelAndView getProductDetails(ModelAndView model, @RequestParam Long productId) {
		model.addObject("product", productService.getProductDetails(productId));
		model.setViewName("singleproduct");
		return model;
	}

	@GetMapping("/getProductPhoto/{productId}")
	public void getProductPhoto(HttpServletResponse response, @PathVariable("productId") Long productId) {
		response.setContentType("image/jpeg");
		byte[] bytes;
		try {
			bytes = productService.getPhotoById(productId);
			InputStream inputStream = new ByteArrayInputStream(bytes);
			IOUtils.copy(inputStream, response.getOutputStream());
		} catch (IOException e) {
			LOGGER.info(e);
		}
	}
}
