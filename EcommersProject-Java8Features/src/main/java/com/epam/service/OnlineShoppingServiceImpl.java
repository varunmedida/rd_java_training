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
		
		return dao.getAllCategories();
	}

	@Override
	public ArrayList<SubCategory> displaySubCategoriesBasedOnCategory(int categoryOption) {
		
		return dao.displaySubCategoriesBasedOnCategory(categoryOption);
	}

	@Override
	public ArrayList<Product> diplayProductsBasedOnSubCategory(int subCategoryOption) {
		
		return dao.diplayProductsBasedOnSubCategory(subCategoryOption);
	}

	@Override
	public void addProductToCart(int subCategoryOption, int productOption, int quantityToAdd) {
		
		dao.addProductToCart(subCategoryOption,productOption,quantityToAdd);
	}

	@Override
	public ArrayList<Cart> viewCart() {
		
		return dao.viewCart();
	}

	@Override
	public void removeProductFromCart(int productId) {
		
		dao.removeProductFromCart(productId);
	}

	@Override
	public double calculateTotalPrice(ArrayList<Cart> cartList) {
		
		double totalAmount=0;
		for (Cart cart : cartList) {
			totalAmount+=cart.getProductPrice()*cart.getQuantityAdded();
		}
		return totalAmount;
	}

	@Override
	public void updateInventoryStock(ArrayList<Cart> cartList) {

		dao.updateInventoryStock(cartList);
	}



}
