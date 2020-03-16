package com.epam.restcontroller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.epam.exception.EmptyCartException;
import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.ShoppingCart;
import com.epam.service.CartService;
import com.epam.service.CategoryService;
import com.epam.service.ProductService;

@RestController
public class HomeRestController {

	private static final Logger LOGGER = LogManager.getLogger(HomeRestController.class);
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	CartService cartService;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		LOGGER.info("Get All Products");
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> getProductDetails(@PathVariable("productId") Long productId) {
		LOGGER.info("Get Product Details");
		return ResponseEntity.ok(productService.getProductDetails(productId));
	}

	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getCategories() {
		LOGGER.info("Get Categories");
		return ResponseEntity.ok(categoryService.getAllCategories());
	}

	@GetMapping("/viewcarts")
	public ResponseEntity<ShoppingCart> viewCart() throws EmptyCartException {
		/* try { */
			
			return ResponseEntity.ok(cartService.viewCart());
		/*}catch (EmptyCartException exception) {
			LOGGER.info("Empty Cart");
			message = ResponseEntity.ok(shoppingCart);
		}*/
	
	}

}
