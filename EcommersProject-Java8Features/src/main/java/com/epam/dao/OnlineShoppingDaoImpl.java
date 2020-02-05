package com.epam.dao;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.model.Cart;
import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.SubCategory;

public class OnlineShoppingDaoImpl implements OnlineShoppingDao {

	ArrayList<Category> categoryList = new ArrayList<>();
	ArrayList<SubCategory> subCategoryList = new ArrayList<>();
	ArrayList<Product> productList = new ArrayList<>();
	ArrayList<Cart> cartList = new ArrayList<>();
	static Logger logger = LogManager.getLogger(OnlineShoppingDaoImpl.class);
	public OnlineShoppingDaoImpl() {

		categoryList.add(new Category(1, "Electronics"));
		categoryList.add(new Category(2, "Fashion"));
		subCategoryList.add(new SubCategory(1, 1, "Mobiles"));
		subCategoryList.add(new SubCategory(1, 2, "Laptops"));
		subCategoryList.add(new SubCategory(1, 3, "Home Appliance"));
		productList.add(new Product(1, 1, "IPhone", 5000, 7));
		productList.add(new Product(1, 2, "Samsung", 4000, 7));
		productList.add(new Product(1, 3, "Asus", 3000, 7));
		productList.add(new Product(2, 4, "Dell", 11000, 7));
		productList.add(new Product(2, 5, "Lenovo", 9000, 7));
		productList.add(new Product(2, 6, "Alien", 15000, 7));
	}

	@Override
	public ArrayList<Category> getAllCategories() {

		return categoryList;
	}

	@Override
	public ArrayList<SubCategory> displaySubCategoriesBasedOnCategory(int categoryOption) {

		return (ArrayList<SubCategory>) subCategoryList.stream()
				.filter(subCategory -> subCategory.getCategoryId() == categoryOption).collect(Collectors.toList());
	}

	@Override
	public ArrayList<Product> diplayProductsBasedOnSubCategory(int subCategoryOption) {

		return (ArrayList<Product>) productList.stream()
				.filter(product -> product.getSubCategoryId() == subCategoryOption).collect(Collectors.toList());
	}

	@Override
	public void addProductToCart(int subCategoryOption, int productOption, int quantityToAdd) {

		Product productToCart = productList.stream().filter(
				product -> product.getProductId() == productOption && product.getSubCategoryId() == subCategoryOption)
				.findAny().orElse(null);
		Cart productPresentInCart = cartList.stream().filter(cart -> cart.getProductId() == productOption).findAny()
				.orElse(null);
		Optional<Product> productOptional = Optional.ofNullable(productToCart);
		Optional<Cart> cartOptional = Optional.ofNullable(productPresentInCart);
		if (productOptional.isPresent()) {
			if (cartOptional.isPresent()) {
				alreadyPresentInCart(productPresentInCart, productToCart, quantityToAdd);
			} else {
				notPresentInCart(productToCart, quantityToAdd);
			}
		} else {
			logger.error("Product Not Present");
		}
	}

	private void notPresentInCart(Product productToCart, int quantityToAdd) {

		if (quantityToAdd <= productToCart.getQuantityInStock()) {
			cartList.add(new Cart(productToCart.getProductId(), productToCart.getProductName(),
					productToCart.getProductPrice(), quantityToAdd));
			logger.info("Product Added to Cart.");
		} else {
			logger.error("Insufficient Quantity!");
		}
	}

	private void alreadyPresentInCart(Cart productPresentInCart, Product productToCart, int quantityToAdd) {

		if (productPresentInCart.getQuantityAdded() + quantityToAdd <= productToCart.getQuantityInStock()) {
			productPresentInCart.setQuantityAdded(quantityToAdd + productPresentInCart.getQuantityAdded());
			logger.info("Product already present. Increased quantity.");
		} else {
			logger.error("Insufficient Quantity!");
		}
	}

	@Override
	public ArrayList<Cart> viewCart() {

		return cartList;
	}

	@Override
	public void removeProductFromCart(int productId) {

		Cart productToBeRemoved = cartList.stream().filter(cart -> cart.getProductId() == productId).findAny()
				.orElse(null);
		Optional<Cart> cartOptional = Optional.ofNullable(productToBeRemoved);
		if (cartOptional.isPresent()) {
			cartList.remove(productToBeRemoved);
			logger.info("Product Removed Successfully");
		} else {
			logger.error("Product not found to be removed.");
		}
	}

	@Override
	public void updateInventoryStock(ArrayList<Cart> cartList) {

		for (Cart product : cartList) {
			int productId = product.getProductId();
			int quantityPurchased = product.getQuantityAdded();
			Product productStockChange = productList.stream().filter(cart -> cart.getProductId() == productId).findAny()
					.orElse(null);
			Optional<Product> productOptional=Optional.ofNullable(productStockChange);
			if(productOptional.isPresent()) {
			productStockChange.setQuantityInStock(productStockChange.getQuantityInStock() - quantityPurchased);}
		}
	}

}
