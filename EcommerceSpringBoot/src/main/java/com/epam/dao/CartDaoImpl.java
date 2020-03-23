package com.epam.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.exception.EmptyCartException;
import com.epam.exception.InsufficientQuantityException;
import com.epam.model.CartItem;
import com.epam.model.Orders;
import com.epam.model.Product;
import com.epam.model.ShoppingCart;
import com.epam.repository.CartItemRepository;
import com.epam.repository.OrderRepository;
import com.epam.repository.ProductRepository;
import com.epam.repository.ShoppingCartRepository;

@Repository
public class CartDaoImpl implements CartDao {

	private static final Logger LOGGER = LogManager.getLogger(CartDaoImpl.class);
	@Autowired
	ShoppingCart shoppingCart;
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	@Autowired
	CartItemRepository cartItemRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ProductRepository productRepository;

	@PostConstruct
	private void postConstruct() {
		shoppingCart = new ShoppingCart();
		shoppingCartRepository.save(shoppingCart);
	}

	@Override
	public ShoppingCart viewCart() throws EmptyCartException {
		LOGGER.info("----Fetching Shopping Cart-----");
		ShoppingCart existinghoppingCart = shoppingCartRepository
				.findByShoppingCartId(shoppingCart.getShoppingCartId());
		if (existinghoppingCart == null) {
			existinghoppingCart = shoppingCartRepository.save(shoppingCart);
			checkIfCartIsEmpty(existinghoppingCart);
		}
		LOGGER.info("----Fetched Shopping Cart Data---");
		existinghoppingCart.setTotalAmount();
		return existinghoppingCart;
	}

	private void checkIfCartIsEmpty(ShoppingCart existinghoppingCart) throws EmptyCartException {
		if (existinghoppingCart.getCartItems().isEmpty()) {
			throw new EmptyCartException("----Empty Cart----");
		}
	}

	@Override
	public boolean addToCart(Product product, Long quantity) throws InsufficientQuantityException {
		boolean addedToCart = false;
		CartItem existingcartItem = cartItemRepository.findByProductAndShoppingCart(product, shoppingCart);
		if (existingcartItem != null) {
			addedToCart = increaseQuantityIfProductInCart(product, quantity, existingcartItem);
		} else {
			
			addedToCart = addProductIfNotPresentInCart(product, quantity);
		}
		return addedToCart;
	}

	private boolean addProductIfNotPresentInCart(Product product, Long quantity) {
		boolean addedToCart;
		
		CartItem newcartItem = new CartItem();
		newcartItem.setProduct(product);
		newcartItem.setQuantity(quantity);
		newcartItem.setShoppingCart(shoppingCart);
		shoppingCart.getCartItems().add(newcartItem);
		shoppingCart.setTotalAmount();
		shoppingCartRepository.flush();
		addedToCart = Optional.of(cartItemRepository.save(newcartItem)).isPresent();
		return addedToCart;
	}

	private boolean increaseQuantityIfProductInCart(Product product, Long quantity, CartItem existingcartItem)
			throws InsufficientQuantityException {
		boolean increasedQuantity = false;
		if (product.getQuantity() >= existingcartItem.getQuantity() + quantity) {
			existingcartItem.setQuantity(existingcartItem.getQuantity() + quantity);
			increasedQuantity = Optional.of(cartItemRepository.save(existingcartItem)).isPresent();
		} else {
			throw new InsufficientQuantityException("----Insufficient stock----");
		}
		return increasedQuantity;
	}

	@Override
	public boolean deleteProduct(Long cartId) {
		cartItemRepository.deleteById(cartId);
		return true;
	}

	@Override
	public boolean updateCart(Product product, Long quantity) {
		boolean updatedCart = false;
		CartItem existingcartItem = cartItemRepository.findByProductAndShoppingCart(product, shoppingCart);
		if (existingcartItem != null) {
			updatedCart = true;
			existingcartItem.setQuantity(quantity);
			cartItemRepository.save(existingcartItem);
		}
		return updatedCart;
	}

	@Override
	public ShoppingCart checkout() throws InsufficientQuantityException {
		List<CartItem> cartItems = cartItemRepository.findByShoppingCart(shoppingCart);
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

	private void updateStockQuantityAfterCheckout(List<CartItem> cartItems) throws InsufficientQuantityException {
		for(CartItem cartItem: cartItems) {
			if(cartItem.getQuantity()<=cartItem.getProduct().getQuantity()) {
				Product product = productRepository.findByProductId(cartItem.getProduct().getProductId());
				product.setQuantity(product.getQuantity()-cartItem.getQuantity());
				productRepository.save(product);
			}else {
				throw new InsufficientQuantityException("----Insufficient Quantity----");
			}
		}
	}

}
