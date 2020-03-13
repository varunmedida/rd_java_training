package com.epam.dao;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.exception.EmptyCartException;
import com.epam.exception.InsufficientQuantityException;
import com.epam.model.CartItem;
import com.epam.model.Product;
import com.epam.model.ShoppingCart;
import com.epam.repository.CartItemRepository;
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
	
	@Override
	public ShoppingCart viewCart() throws EmptyCartException {
		LOGGER.info("----Fetching Shopping Cart-----");
		ShoppingCart existinghoppingCart = shoppingCartRepository.findByShoppingCartId(shoppingCart.getShoppingCartId());
		if (existinghoppingCart==null) {
			existinghoppingCart = shoppingCartRepository.save(shoppingCart);
			checkIfCartIsEmpty(existinghoppingCart);
		}
		LOGGER.info("----Fetched Shopping Cart Data---");
		existinghoppingCart.setTotalAmount();
		return existinghoppingCart;
	}

	private void checkIfCartIsEmpty(ShoppingCart existinghoppingCart) throws EmptyCartException {
		if(existinghoppingCart.getCartItems().isEmpty()) {
			throw new EmptyCartException("----Empty Cart----");
		}
	}

	@Override
	public boolean addToCart(Product product, Long quantity) throws InsufficientQuantityException {
		boolean addedToCart=false;
		CartItem existingcartItem = cartItemRepository.findByProductAndShoppingCart(product,shoppingCart);
		if(existingcartItem!=null) {
			addedToCart = increaseQuantityIfProductInCart(product, quantity, existingcartItem);
		}else {
		addedToCart = addProductIfNotPresentInCart(product, quantity);
		}
		return addedToCart;
	}

	private boolean addProductIfNotPresentInCart(Product product, Long quantity) {
		boolean addedToCart;
		CartItem newcartItem=new CartItem();
		newcartItem.setProduct(product);
		newcartItem.setQuantity(quantity);
		newcartItem.setShoppingCart(shoppingCart);
		shoppingCartRepository.save(shoppingCart);
		addedToCart= Optional.of(cartItemRepository.save(newcartItem)).isPresent();
		return addedToCart;
	}

	private boolean increaseQuantityIfProductInCart(Product product, Long quantity,
			CartItem existingcartItem) throws InsufficientQuantityException {
		boolean increasedQuantity=false;
		if(product.getQuantity() >= existingcartItem.getQuantity()+ quantity) {
			existingcartItem.setQuantity(existingcartItem.getQuantity()+ quantity);
			increasedQuantity = Optional.of(cartItemRepository.save(existingcartItem)).isPresent();
		}else {
			throw new InsufficientQuantityException("----Insufficient stock----");
		}
		return increasedQuantity;
	}

	@Override
	public boolean deleteProduct(Long cartId) {
		cartItemRepository.deleteById(cartId);
		return true;
	}
	

}
