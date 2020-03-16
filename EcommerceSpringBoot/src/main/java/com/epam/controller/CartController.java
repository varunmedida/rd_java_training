package com.epam.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.exception.EmptyCartException;
import com.epam.exception.InsufficientQuantityException;
import com.epam.service.CartService;
import com.epam.service.ProductService;

@Controller
public class CartController {
	private static final Logger LOGGER = LogManager.getLogger(CartController.class);
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;

	@PostMapping("/addtocart")
	public ModelAndView addProductToCart(ModelAndView model, @RequestParam("productId") Long productId,
			@RequestParam("quantity") Long quantity) {
		String addedToCart = "";
		try {
			LOGGER.info("-----Add product to Cart-----");
			cartService.addToCart(productId, quantity);
			LOGGER.info("-----Product Added to Cart-----");
			addedToCart = "<div class='alert alert-success'>Product Added To Cart.</div>";
		} catch (InsufficientQuantityException exception) {
			LOGGER.error(exception.getMessage());
			addedToCart = "<div class='alert alert-danger'>You cannot add item because it has exceeded stock quantity.</div>";
		} finally {
			model.addObject("addedToCart", addedToCart);
			model.addObject("product", productService.getProductDetails(productId));
			model.setViewName("singleproduct");
		}
		return model;
	}

	@GetMapping("/viewcart")
	public ModelAndView viewCart(ModelAndView model) {
		try {
			LOGGER.info("-----View Cart-----");
			model.addObject("cart", cartService.viewCart());
		} catch (EmptyCartException exception) {
			LOGGER.error(exception.getMessage());
		}
		model.setViewName("cart");
		return model;
	}

	@PostMapping("/delete")
	public ModelAndView deleteProduct(ModelAndView model, @RequestParam("cartId") Long cartId) {
		model.addObject("productRemoved", cartService.deleteProduct(cartId));
		try {
			LOGGER.info("-----Delete product from cart-----");
			model.addObject("cart", cartService.viewCart());
		} catch (EmptyCartException exception) {
			LOGGER.error(exception.getMessage());
		}
		model.setViewName("cart");
		return model;
	}

}
