package com.epam.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.SubCategory;
import com.epam.util.JPAUtil;

public class OnlineShoppingDaoImpl implements OnlineShoppingDao {

	@Override
	public List<Category> getAllCategories() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		List<Category> categories = entityManager.createQuery("SELECT c from category c").getResultList();
		System.out.println(categories);
		entityManager.close();
		JPAUtil.shutdown();
		return categories;
	}

	@Override
	public List<SubCategory> getSubCategoriesBasedOnCategory(String categoryOption) {
		return null;
	}

	@Override
	public List<Product> getProductsBasedOnSubCategory(String subCategoryOption) {

		return null;
	}

}
