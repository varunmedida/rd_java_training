package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.exception.DataNotFoundException;
import com.epam.exception.EmptyCartException;
import com.epam.exception.InsufficientQuantityException;
import com.epam.model.CartItem;
import com.epam.model.Orders;
import com.epam.model.Product;
import com.epam.model.ShoppingCart;
import com.epam.model.User;
import com.epam.repository.CartItemRepository;
import com.epam.repository.OrderRepository;
import com.epam.repository.ProductRepository;
import com.epam.repository.ShoppingCartRepository;
import com.epam.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	ProductService productService;

	@Autowired
	OrderRepository orderRepository;

	@Override
	public ShoppingCart viewCart(String userName) throws EmptyCartException {
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User Not found"));
		ShoppingCart existingShoppingCart = shoppingCartRepository.findByUserAndCheckedOut(user, false);
		if (existingShoppingCart == null) {
			existingShoppingCart = new ShoppingCart();
			existingShoppingCart.setUser(user);
			existingShoppingCart.setCheckedOut(false);
		}
		checkIfCartIsEmpty(existingShoppingCart);
		existingShoppingCart.setTotalAmount();
		return shoppingCartRepository.save(existingShoppingCart);
	}

	private void checkIfCartIsEmpty(ShoppingCart existingShoppingCart) throws EmptyCartException {
		if (existingShoppingCart.getCartItems().isEmpty()) {
			throw new EmptyCartException("----Empty Cart----");
		}
	}

	@Override
	public CartItem addToCart(Long productId, Long quantity, String userName) {
		Product product = productService.getProductById(productId);
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User Not found"));
		if (quantity <= 0) {
			throw new InsufficientQuantityException("Enter quantity greater then zero");
		}
		ShoppingCart shoppingCart = shoppingCartRepository.findByUserAndCheckedOut(user, false);
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user);
			shoppingCart.setCheckedOut(false);
			shoppingCartRepository.save(shoppingCart);
		}
		CartItem existingcartItem = cartItemRepository.findByProductAndShoppingCart(product, shoppingCart);
		if (existingcartItem != null) {
			existingcartItem = increaseQuantityIfProductInCart(product, quantity, existingcartItem);
		} else {

			existingcartItem = addProductIfNotPresentInCart(product, quantity, shoppingCart);
		}
		return existingcartItem;
	}

	private CartItem addProductIfNotPresentInCart(Product product, Long quantity, ShoppingCart shoppingCart) {
		CartItem newcartItem = new CartItem();
		if (product.getQuantity() < quantity) {
			throw new InsufficientQuantityException("Insufficient stock");
		}
		newcartItem.setProduct(product);
		newcartItem.setQuantity(quantity);
		newcartItem.setShoppingCart(shoppingCart);
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
	public CartItem updateCart(Long productId, Long quantity, String userName) {
		Product product = productService.getProductById(productId);
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User Not found"));
		ShoppingCart shoppingCart = shoppingCartRepository.findByUserAndCheckedOut(user, false);
		CartItem existingcartItem = cartItemRepository.findByProductAndShoppingCart(product, shoppingCart);
		if (existingcartItem == null) {
			throw new DataNotFoundException("Product Not Present in Cart to update");
		} else {
			if (existingcartItem.getProduct().getQuantity() < quantity) {
				throw new InsufficientQuantityException("Insufficient stock");
			} else if (quantity <= 0) {
				throw new InsufficientQuantityException("Enter quantity greater then zero");
			} else {
				existingcartItem.setQuantity(quantity);
			}
		}
		return cartItemRepository.save(existingcartItem);
	}

	@Override
	public CartItem deleteCartItem(Long productId, String userName) {
		Product product = productService.getProductById(productId);
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User Not found"));
		ShoppingCart shoppingCart = shoppingCartRepository.findByUserAndCheckedOut(user, false);
		CartItem existingcartItem = cartItemRepository.findByProductAndShoppingCart(product, shoppingCart);
		if (existingcartItem == null) {
			throw new DataNotFoundException("Invalid Cart Item");
		} else {
			cartItemRepository.delete(existingcartItem);
		}
		return existingcartItem;
	}

	@Override
	public ShoppingCart checkout(String userName){
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User Not found"));
		ShoppingCart shoppingCart = shoppingCartRepository.findByUserAndCheckedOut(user, false);
		List<CartItem> cartItems = cartItemRepository.findByShoppingCart(shoppingCart);
		if (cartItems.isEmpty()) {
			throw new DataNotFoundException("Cart is empty could not checkout.");
		}
		updateStockQuantityAfterCheckout(cartItems);
		shoppingCart.setCheckedOut(true);
		shoppingCartRepository.save(shoppingCart);
		Orders order = new Orders();
		order.setShoppingCart(shoppingCart);
		orderRepository.save(order);
		return shoppingCart;
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
