package com.epam.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import com.epam.model.CartItem;
import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.ShoppingCart;
import com.epam.model.SubCategory;
import com.epam.util.JPAUtil;

public class OnlineShoppingDaoImpl implements OnlineShoppingDao {

	static ShoppingCart shoppingCart = new ShoppingCart();

	@Override
	public List<Category> getAllCategories() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		List<Category> categories = entityManager.createQuery("select c from Category c", Category.class)
				.getResultList();
		entityManager.close();
		return categories;
	}

	@Override
	public List<SubCategory> getSubCategoriesBasedOnCategory(int categoryOption) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		List<SubCategory> subCategories = entityManager
				.createQuery("select s from SubCategory s where s.category=" + categoryOption, SubCategory.class)
				.getResultList();
		entityManager.close();
		return subCategories;
	}

	@Override
	public List<Product> getProductsBasedOnSubCategory(int subCategoryOption) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		SubCategory subCategory = entityManager.find(SubCategory.class, subCategoryOption);
		entityManager.close();
		return subCategory.getProducts();
	}

	@Override
	public Product getProductById(int productOption) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		Product product = entityManager.find(Product.class, productOption);
		entityManager.close();
		return product;
	}

	@Override
	public void addToCart(Product product, int quantityAdded) {
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		if (shoppingCart.getCartItems().isEmpty()) {
			addItemToCart(quantityAdded, cartItem);
		} else {
			updateOrAddItemToCart(quantityAdded, cartItem);
		}

	}

	private void updateOrAddItemToCart(int quantityAdded, CartItem cartItem) {
		for (CartItem cartProduct : shoppingCart.getCartItems()) {
			if (cartProduct.getProduct().getProductId() == cartItem.getProduct().getProductId()) {
				cartProduct.setQuantityToCart(quantityAdded + cartProduct.getQuantityToCart());
			} else {
				addItemToCart(quantityAdded, cartItem);

			}
		}
	}

	private void addItemToCart(int quantityAdded, CartItem cartItem) {
		cartItem.setQuantityToCart(quantityAdded);
		shoppingCart.addToShoppingCart(cartItem);
	}

	@Override
	public Set<CartItem> viewCart() {
		return shoppingCart.getCartItems();
	}

}
