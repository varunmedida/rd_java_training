package com.epam.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.CartDao;
import com.epam.dao.ProductDao;
import com.epam.exception.EmptyCartException;
import com.epam.exception.InsufficientQuantityException;
import com.epam.model.ShoppingCart;

@Service
public class CartServiceImpl implements CartService {

	private static final Logger LOGGER = LogManager.getLogger(CartServiceImpl.class);
	@Autowired
	CartDao cartDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	ShoppingCart shoppingCart;

	@Override
	public ShoppingCart viewCart() throws EmptyCartException {
		LOGGER.info("----View Cart Service---");
		return cartDao.viewCart();
	}

	@Override
	public boolean addToCart(Long productId, Long quantity) throws InsufficientQuantityException {
		boolean addedToCart = false;
		if (productDao.getProductDetails(productId).getQuantity() >= quantity && quantity > 0) {
			addedToCart = cartDao.addToCart(productDao.getProductDetails(productId), quantity);
		} else {
			throw new InsufficientQuantityException("----Insufficient stock----");
		}
		return addedToCart;
	}

	@Override
	public boolean deleteProduct(Long cartId) {
		return cartDao.deleteProduct(cartId);
	}

	@Override
	public boolean updateCart(Long productId, Long quantity) throws InsufficientQuantityException {
		boolean updatedCart = false;
		if (productDao.getProductDetails(productId).getQuantity() >= quantity && quantity > 0) {
			updatedCart = cartDao.updateCart(productDao.getProductDetails(productId), quantity);
		} else {
			throw new InsufficientQuantityException("----Insufficient stock----");
		}
		return updatedCart;
	}

	@Override
	public ShoppingCart checkout() throws InsufficientQuantityException {
		return cartDao.checkout();
	}

}
