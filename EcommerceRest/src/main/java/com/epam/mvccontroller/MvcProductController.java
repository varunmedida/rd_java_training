package com.epam.mvccontroller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
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
public class MvcProductController {

	private static final Logger LOGGER = LogManager.getLogger(MvcProductController.class);
	@Autowired
	ProductService productService;

	@PostMapping("productdetail")
	public ModelAndView getProductDetails(ModelAndView model, @RequestParam Long productId) {
		LOGGER.info("----Get Product Details----");
		model.addObject("product", productService.getProductById(productId));
		model.setViewName("singleproduct");
		return model;
	}

	@GetMapping("/getProductPhoto/{productId}")
	public void getProductPhoto(HttpServletResponse response, @PathVariable("productId") Long productId) {
		response.setContentType("image/jpeg");
		byte[] bytes;
		try {
			LOGGER.info("----Get Product Details----");
			bytes = productService.getPhotoById(productId);
			if(bytes==null) {
				 BufferedImage bImage = ImageIO.read(new File("D:\\noimg.jpg"));
			      ByteArrayOutputStream bos = new ByteArrayOutputStream();
			      ImageIO.write(bImage, "jpg", bos);
			      bytes = bos.toByteArray();
			}
			InputStream inputStream = new ByteArrayInputStream(bytes);
			IOUtils.copy(inputStream, response.getOutputStream());
		} catch (IOException exception) {
			LOGGER.info(exception);
		}
	}
}
