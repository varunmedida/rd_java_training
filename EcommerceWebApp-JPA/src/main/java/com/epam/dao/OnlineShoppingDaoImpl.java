package com.epam.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
		Root<Category> category = criteriaQuery.from(Category.class);
		criteriaQuery.select(category);
		TypedQuery<Category> query = entityManager.createQuery(criteriaQuery);
		List<Category> categories = query.getResultList();
		return categories;
	}

	@Override
	public List<SubCategory> getSubCategoriesBasedOnCategory(int categoryOption) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		Category category = entityManager.find(Category.class, categoryOption);
		return category.getSubCategories();
	}

	@Override
	public List<Product> getProductsBasedOnSubCategory(int subCategoryOption) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		SubCategory subCategory = entityManager.find(SubCategory.class, subCategoryOption);
		return subCategory.getProducts();
	}

	@Override
	public Product getProductById(int productOption) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		return entityManager.find(Product.class, productOption);
	}

	@Override
	public void addToCart(Product product, int quantityAdded) {
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		if (shoppingCart.getCartItems().isEmpty()) {
			cartItem.setQuantityToCart(quantityAdded);

			shoppingCart.addToShoppingCart(cartItem);

		} else {
			for (CartItem cartProduct : shoppingCart.getCartItems()) {
				if (cartProduct.getProduct().getProductId() == cartItem.getProduct().getProductId()) {
					cartProduct.setQuantityToCart(quantityAdded + cartProduct.getQuantityToCart());
				} else {
					cartItem.setQuantityToCart(quantityAdded);

					shoppingCart.addToShoppingCart(cartItem);

				}
			}
		}

	}

	@Override
	public Set<CartItem> viewCart() {
		return shoppingCart.getCartItems();
	}

}
