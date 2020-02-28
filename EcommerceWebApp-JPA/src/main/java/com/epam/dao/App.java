package com.epam.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.SubCategory;
import com.epam.util.JPAUtil;

public class App {

	public static void main(String[] args)  {
		EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		List<SubCategory> subCategories = new ArrayList<>();
		List<Product> products = new ArrayList<>();
		Category electronics = new Category("Electronics");
		Category fashion = new Category("Fashion");
		SubCategory mobile = new SubCategory("Mobile",electronics);
		SubCategory laptop = new SubCategory("Laptop",electronics);
		SubCategory homeAppliance = new SubCategory("Home Appliance",electronics);
		Product apple = new Product(mobile,"Apple", 10000, 10);
		Product samsung = new Product(mobile,"Samsung", 8000, 8);
		Product moto = new Product(mobile,"Moto", 5000, 5);
		entityManager.persist(apple);
		entityManager.persist(samsung);
		entityManager.persist(moto);
		products.add(apple);
		products.add(samsung);
		products.add(moto);
		mobile.setProducts(products);
		entityManager.persist(mobile);
		entityManager.persist(laptop);
		entityManager.persist(homeAppliance);
		subCategories.add(mobile);
		subCategories.add(laptop);
		subCategories.add(homeAppliance);
		electronics.setSubCategories(subCategories);
		entityManager.persist(electronics);
		entityManager.persist(fashion);
		entityManager.getTransaction().commit();
	}

}
