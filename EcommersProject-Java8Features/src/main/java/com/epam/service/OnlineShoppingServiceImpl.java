package com.epam.service;

import java.util.ArrayList;

import com.epam.dao.OnlineShoppingDao;
import com.epam.dao.OnlineShoppingDaoImpl;
import com.epam.model.Cart;
import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.SubCategory;

public class OnlineShoppingServiceImpl implements OnlineShoppingService {

	static OnlineShoppingDao dao = new OnlineShoppingDaoImpl();

	@Override
	public ArrayList<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return dao.getAllCategories();
	}

	@Override
	public ArrayList<SubCategory> displaySubCategoriesBasedOnCategory(int categoryOption) {
		// TODO Auto-generated method stub
		return dao.displaySubCategoriesBasedOnCategory(categoryOption);
	}

	@Override
	public ArrayList<Product> diplayProductsBasedOnSubCategory(int subCategoryOption) {
		// TODO Auto-generated method stub
		return dao.diplayProductsBasedOnSubCategory(subCategoryOption);
	}

	@Override
	public void addProductToCart(int subCategoryOption, int productOption, int quantityToAdd) {
		// TODO Auto-generated method stub
		dao.addProductToCart(subCategoryOption,productOption,quantityToAdd);
	}

	@Override
	public ArrayList<Cart> viewCart() {
		// TODO Auto-generated method stub
		return dao.viewCart();
	}

	@Override
	public void removeProductFromCart(int productId) {
		// TODO Auto-generated method stub
		dao.removeProductFromCart(productId);
	}

	@Override
	public double calculateTotalPrice(ArrayList<Cart> cartList) {
		// TODO Auto-generated method stub
		double totalAmount=0;
		for (Cart cart : cartList) {
			totalAmount+=cart.getProductPrice()*cart.getQuantityAdded();
		}
		return totalAmount;
	}

	@Override
	public void updateInventoryStock(ArrayList<Cart> cartList) {
		// TODO Auto-generated method stub
		dao.updateInventoryStock(cartList);
	}



}
