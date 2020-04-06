package com.epam.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.exception.EmptyCartException;
import com.epam.model.CartItem;
import com.epam.model.ShoppingCart;
import com.epam.service.CartService;

@RestController
@RequestMapping("/ecommerce")
public class CartController {

	@Autowired
	CartService cartService;
	
	@GetMapping("/viewcart")
	public ResponseEntity<ShoppingCart> viewCart(HttpServletRequest request) throws EmptyCartException{
		Principal principal = request.getUserPrincipal();
		return ResponseEntity.ok(cartService.viewCart(principal.getName()));
	}
	
	@PostMapping("/addtocart")
	public ResponseEntity<CartItem> addToCart(@RequestParam Long productId,@RequestParam Long quantity,HttpServletRequest request){
		Principal principal = request.getUserPrincipal();
		return ResponseEntity.accepted().body(cartService.addToCart(productId,quantity,principal.getName()));
	}
	
	@PutMapping("/updatecart/{productId}/{quantity}")
	public ResponseEntity<CartItem> updateCart(@PathVariable Long productId,@PathVariable Long quantity,HttpServletRequest request){
		Principal principal = request.getUserPrincipal();
		return ResponseEntity.ok(cartService.updateCart(productId,quantity,principal.getName()));
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<CartItem> deleteCartItem(@PathVariable Long productId,HttpServletRequest request){
		Principal principal = request.getUserPrincipal();
		return ResponseEntity.ok(cartService.deleteCartItem(productId,principal.getName()));
	}
	
	@GetMapping("/checkout")
	public ResponseEntity<ShoppingCart> checkout(HttpServletRequest request){
		Principal principal = request.getUserPrincipal();
		return ResponseEntity.ok(cartService.checkout(principal.getName()));
	}
	
}
