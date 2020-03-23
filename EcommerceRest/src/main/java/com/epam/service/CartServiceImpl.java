package com.epam.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.exception.DataNotFoundException;
import com.epam.exception.InsufficientQuantityException;
import com.epam.model.CartItem;
import com.epam.model.Orders;
import com.epam.model.Product;
import com.epam.model.ShoppingCart;
import com.epam.repository.CartItemRepository;
import com.epam.repository.OrderRepository;
import com.epam.repository.ProductRepository;
import com.epam.repository.ShoppingCartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	@Autowired
	ProductRepository productRepository;

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	ProductService productService;

	@Autowired
	ShoppingCart shoppingCart;

	@Autowired
	OrderRepository orderRepository;

	@PostConstruct
	private void postConstruct() {
		shoppingCart = new ShoppingCart();
		shoppingCartRepository.save(shoppingCart);
	}

	@Override
	public ShoppingCart viewCart() {
		ShoppingCart existinghoppingCart = shoppingCartRepository
				.findByShoppingCartId(shoppingCart.getShoppingCartId());
		if (existinghoppingCart == null) {
			existinghoppingCart = shoppingCartRepository.save(shoppingCart);
		}
		existinghoppingCart.setTotalAmount();
		return shoppingCartRepository.save(existinghoppingCart);
	}

	@Override
	public CartItem addToCart(Long productId, Long quantity) {
		Product product = productService.getProductById(productId);
		if(quantity<=0) {
			throw new InsufficientQuantityException("Enter quantity greater then zero");
		}
		CartItem existingcartItem = cartItemRepository.findByProductAndShoppingCart(product, shoppingCart);
		if (existingcartItem != null) {
			existingcartItem = increaseQuantityIfProductInCart(product, quantity, existingcartItem);
		} else {

			existingcartItem = addProductIfNotPresentInCart(product, quantity);
		}
		return existingcartItem;
	}

	private CartItem addProductIfNotPresentInCart(Product product, Long quantity) {
		CartItem newcartItem = new CartItem();
		if (product.getQuantity() < quantity) {
			throw new InsufficientQuantityException("Insufficient stock");
		}
		newcartItem.setProduct(product);
		newcartItem.setQuantity(quantity);
		newcartItem.setShoppingCart(shoppingCart);
		shoppingCart.getCartItems().add(newcartItem);
		shoppingCart.setTotalAmount();
		shoppingCartRepository.flush();
		return cartItemRepository.save(newcartItem);
	}

	private CartItem increaseQuantityIfProductInCart(Product product, Long quantity, CartItem existingcartItem) {
		if (product.getQuantity() >= existingcartItem.getQuantity() + quantity) {
			existingcartItem.setQuantity(existingcartItem.getQuantity() + quantity);
			existingcartItem = cartItemRepository.save(existingcartItem);
		} else {
			throw new InsufficientQuantityException("Insufficient stock");
		}
		return existingcartItem;
	}

	@Override
	public CartItem updateCart(Long productId, Long quantity) {
		Product product = productService.getProductById(productId);
		CartItem existingcartItem = cartItemRepository.findByProductAndShoppingCart(product, shoppingCart);
		if (existingcartItem.getProduct().getQuantity() < quantity) {
			throw new InsufficientQuantityException("Insufficient stock");
		} else if(quantity<=0) {
			throw new InsufficientQuantityException("Enter quantity greater then zero");
		}else {
			existingcartItem.setQuantity(quantity);
		}
		return cartItemRepository.save(existingcartItem);
	}

	@Override
	public CartItem deleteCartItem(Long productId) {
		Product product = productService.getProductById(productId);
		CartItem existingcartItem = cartItemRepository.findByProductAndShoppingCart(product, shoppingCart);
		if (existingcartItem == null) {
			throw new DataNotFoundException("Product is Not added to Cart");
		} else {
			cartItemRepository.delete(existingcartItem);
		}
		return existingcartItem;
	}

	@Override
	public ShoppingCart checkout() {
		List<CartItem> cartItems = cartItemRepository.findByShoppingCart(shoppingCart);
		if(cartItems.isEmpty()) {
			throw new DataNotFoundException("Cart is empty could not checkout.");
		}
		shoppingCart.setCartItems(cartItems);
		shoppingCart.setTotalAmount();
		Orders order = new Orders();
		order.setShoppingCart(shoppingCart);
		orderRepository.save(order);
		updateStockQuantityAfterCheckout(cartItems);
		ShoppingCart existingShoppingCart = shoppingCart;
		shoppingCart = new ShoppingCart();
		List<CartItem> newCartItems = new ArrayList<>();
		shoppingCart.setCartItems(newCartItems);
		shoppingCartRepository.save(shoppingCart);
		return existingShoppingCart;
	}

	private void updateStockQuantityAfterCheckout(List<CartItem> cartItems) {
		for (CartItem cartItem : cartItems) {
			if (cartItem.getQuantity() <= cartItem.getProduct().getQuantity()) {
				Product product = productRepository.findByProductId(cartItem.getProduct().getProductId());
				product.setQuantity(product.getQuantity() - cartItem.getQuantity());
				productRepository.save(product);
			} else {
				throw new InsufficientQuantityException("----Insufficient Quantity----");
			}
		}

	}

}
