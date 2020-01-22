package com.epam.dao;

import java.util.ArrayList;

import com.epam.model.Cart;
import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.SubCategory;

public class OnlineShoppingDaoImpl implements OnlineShoppingDao {

	ArrayList<Category> categoryList = new ArrayList<Category>();
	ArrayList<SubCategory> subCategoryList = new ArrayList<SubCategory>();
	ArrayList<Product> productList = new ArrayList<Product>();
	ArrayList<Cart> cartProductsList = new ArrayList<Cart>();

	public OnlineShoppingDaoImpl() {
		productList.add(new Product(1, "Iphonex", 5000, 5));
		productList.add(new Product(2, "Samsung", 4000, 6));
		productList.add(new Product(3, "Pixel", 2000, 7));
		subCategoryList.add(new SubCategory(1, "Mobiles", productList));
		subCategoryList.add(new SubCategory(2, "Laptops", null));
		subCategoryList.add(new SubCategory(3, "Home Appliances", null));
		categoryList.add(new Category(1, "Electronics", subCategoryList));
		categoryList.add(new Category(2, "Fashion", null));
	}

	public ArrayList<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryList;
	}

	public void addProductToCart(Product product, int quantity) {
		// TODO Auto-generated method stub
		cartProductsList
				.add(new Cart(product.getProductId(), product.getProductName(), product.getProductPrice(), quantity));
	}

	public ArrayList<Cart> viewProductsInCart() {
		// TODO Auto-generated method stub
		return cartProductsList;
	}

	public void removeProductsFromCart(int productId) {
		// TODO Auto-generated method stub
		for (int i = 0; i < cartProductsList.size(); i++)
			if (cartProductsList.get(i).getProductId() == productId)
				cartProductsList.remove(i);
	}

	public void updateStockInInventory(ArrayList<Cart> products) {
		for (Cart product : products) {
			int productId = product.getProductId();
			int quantityPurchased = product.getQuantityPurchased();
			int stockQuantity = productList.get(productId - 1).getQuantity();
			productList.get(productId - 1).setQuantity(stockQuantity-quantityPurchased);
		}
	}

}
