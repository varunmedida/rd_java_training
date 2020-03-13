package com.epam.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.epam.dao.ProductDao;
import com.epam.model.Product;
import com.epam.model.SubCategory;

class ProductServiceImplTest {

	@InjectMocks
	ProductServiceImpl productService;
	@Mock
	ProductDao productDao;

	static SubCategory subCategory;
	static Product firstProduct;
	static Product secondProduct;
	static List<Product> products = new ArrayList<>();

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	public static void setInitVars() {
		firstProduct = new Product();
		secondProduct = new Product();
		subCategory = new SubCategory();
		subCategory.setSubCategoryId((long) 1);
		firstProduct.setProductId((long) 1);
		firstProduct.setProductName("Apple");
		firstProduct.setSubCategory(subCategory);
		secondProduct.setProductId((long) 2);
		secondProduct.setProductName("Samsung");
		secondProduct.setSubCategory(subCategory);
		products.add(firstProduct);
		products.add(secondProduct);
	}

	@Test
	void testGetAllProducts() {
		Mockito.when(productDao.getAllProducts()).thenReturn(products);
		Assertions.assertEquals(products.size(), productService.getAllProducts().size());

	}

	@Test
	void testGetPhotoById() {
		Mockito.when(productDao.getPhotoById(firstProduct.getProductId())).thenReturn(firstProduct);
		Assertions.assertEquals(null, productService.getPhotoById(firstProduct.getProductId()));
	}

	@Test
	void testGetProductsBasedOnSubCategoryId() {
		Mockito.when(productDao.getProductsBasedOnSubCategoryId(subCategory.getSubCategoryId())).thenReturn(products);
		Assertions.assertEquals(products.size(),
				productService.getProductsBasedOnSubCategoryId(subCategory.getSubCategoryId()).size());
	}

	@Test
	void testGetProductDetails() {
		Mockito.when(productDao.getProductDetails(firstProduct.getProductId())).thenReturn(firstProduct);
		Assertions.assertEquals(firstProduct.getProductId(),
				productService.getProductDetails(firstProduct.getProductId()).getProductId());
	}

}
