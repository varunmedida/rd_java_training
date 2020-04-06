package com.epam.mvccontroller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.epam.exception.EmptyCartException;
import com.epam.exception.InsufficientQuantityException;
import com.epam.service.CartService;
import com.epam.service.ProductService;

@Controller
public class MvcCartController {
	private static final Logger LOGGER = LogManager.getLogger(MvcCartController.class);
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;

	@PostMapping("/addtocart")
	public ModelAndView addProductToCart(ModelAndView model, @RequestParam("productId") Long productId,
			@RequestParam("quantity") Long quantity,HttpServletRequest request) {
		String addedToCart = "";
		Principal principal = request.getUserPrincipal();
		try {
			LOGGER.info("-----Add product to Cart-----");
			cartService.addToCart(productId, quantity,principal.getName());
			LOGGER.info("-----Product Added to Cart-----");
			addedToCart = "<div class='alert alert-success'>Product Added To Cart.</div>";
		} catch (InsufficientQuantityException exception) {
			LOGGER.error(exception.getMessage());
			addedToCart = "<div class='alert alert-danger'>Product already in cart.Exceeded Stock Quantity</div>";
		} finally {
			model.addObject("addedToCart", addedToCart);
			model.addObject("product", productService.getProductById(productId));
			model.setViewName("singleproduct");
		}
		return model;
	}

	@GetMapping("/viewcart")
	public ModelAndView viewCart(ModelAndView model,HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		try {
			LOGGER.info("-----View Cart-----");
			model.addObject("cart", cartService.viewCart(principal.getName()));
		} catch (EmptyCartException exception) {
			LOGGER.error(exception.getMessage());
		}
		model.setViewName("cart");
		return model;
	}

	@PostMapping("/delete")
	public RedirectView deleteProduct(ModelAndView model, @RequestParam("productId") Long productId,
			RedirectView redirectView,HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		try {
			LOGGER.info("-----Delete product from cart-----");
			model.addObject("productRemoved", cartService.deleteCartItem(productId,principal.getName()));
			model.addObject("cart", cartService.viewCart(principal.getName()));
		} catch (EmptyCartException exception) {
			LOGGER.error(exception.getMessage());
		}
		redirectView.setUrl("/viewcart");
		return redirectView;
	}

	@PostMapping("/updatecart")
	public RedirectView updateCart(ModelAndView model, @RequestParam("productId") Long productId,
			@RequestParam("quantity") Long quantity, RedirectView redirectView,HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		try {
			LOGGER.info("-----Update product from cart-----");
			model.addObject("updatedCart", cartService.updateCart(productId, quantity,principal.getName()));
		} catch (InsufficientQuantityException exception) {
			LOGGER.error(exception.getMessage());
		}
		redirectView.setUrl("/viewcart");

		return redirectView;
	}
	
	@GetMapping("/checkout")
	public ModelAndView checkout(ModelAndView model,HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		try {
			model.addObject("shoppingCart", cartService.checkout(principal.getName()));
		} catch (InsufficientQuantityException message) {
			LOGGER.error(message.getMessage());
		}
		model.setViewName("checkout");
		return model;
	}

}
