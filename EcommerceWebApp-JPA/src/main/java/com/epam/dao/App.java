package com.epam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.epam.model.Category;
import com.epam.util.JPAUtil;

public class App {
	
	
	public static void main(String[] args) {
	
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
		Root<Category> category = criteriaQuery.from(Category.class);
		criteriaQuery.select(category);
		TypedQuery<Category> query = entityManager.createQuery(criteriaQuery);
		List<Category> categories = query.getResultList();
		System.out.println(categories.get(0));
		System.out.println(categories.get(1));
		//entityManager.close();
		//JPAUtil.shutdown();

	}

}
