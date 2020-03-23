package com.epam.controller;

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

import com.epam.model.CartItem;
import com.epam.model.ShoppingCart;
import com.epam.service.CartService;

@RestController
@RequestMapping("/ecommerce")
public class CartController {

	@Autowired
	CartService cartService;
	
	@GetMapping("/viewcart")
	public ResponseEntity<ShoppingCart> viewCart(){
		return ResponseEntity.ok(cartService.viewCart());
	}
	
	@PostMapping("/addtocart")
	public ResponseEntity<CartItem> addToCart(@RequestParam Long productId,@RequestParam Long quantity){
		return ResponseEntity.accepted().body(cartService.addToCart(productId,quantity));
	}
	
	@PutMapping("/updatecart/{productId}/{quantity}")
	public ResponseEntity<CartItem> updateCart(@PathVariable Long productId,@PathVariable Long quantity){
		return ResponseEntity.ok(cartService.updateCart(productId,quantity));
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<CartItem> deleteCartItem(@PathVariable Long productId){
		return ResponseEntity.ok(cartService.deleteCartItem(productId));
	}
	
	@GetMapping("/checkout")
	public ResponseEntity<ShoppingCart> checkout(){
		return ResponseEntity.ok(cartService.checkout());
	}
	
}
