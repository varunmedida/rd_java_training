package com.epam.service;

import java.util.List;

import com.epam.beans.Cart;
import com.epam.dao.OnlineShoppingDao;
import com.epam.dao.OnlineShoppingDaoImpl;

public class CartServiceImpl implements CartService {

	OnlineShoppingDao dao = new OnlineShoppingDaoImpl();

	@Override
	public void addProductToCart(int productOption, int subCategoryOption,int quantityToBeAdded) {
		dao.addProductToCart(productOption, subCategoryOption,quantityToBeAdded);
	}

	@Override
	public List<Cart> viewCart() {
		return dao.viewCart();
	}

	@Override
	public void removeProductFromCart(int productId) {
		dao.removeProductFromCart(productId);
		
	}

}
