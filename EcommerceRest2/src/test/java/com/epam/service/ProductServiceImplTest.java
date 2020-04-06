package com.epam.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.exception.DataNotFoundException;
import com.epam.model.Product;
import com.epam.model.SubCategory;
import com.epam.repository.ProductRepository;
import com.epam.repository.SubCategoryRepository;

class ProductServiceImplTest {

	@InjectMocks
	ProductServiceImpl productService;
	@Mock
	ProductRepository productRepository;
	@Mock
	SubCategoryRepository subCategoryRepository;
	@Mock
	SubCategory subCategory;

	static Product apple;
	static SubCategory mobiles;
	static SubCategory laptops;
	static List<Product> products;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	public static void setInitVars() {
		mobiles = new SubCategory();
		laptops = new SubCategory();
		mobiles.setSubCategoryId((long) 1);
		mobiles.setSubCategoryName("Mobiles");
		laptops.setSubCategoryId((long) 2);
		laptops.setSubCategoryName("Laptops");
		products = new ArrayList<>();
		apple = new Product();
		apple.setProductId((long) 1);
		apple.setProductName("Apple");
		apple.setQuantity((long) 10);
		apple.setSubCategory(mobiles);
		apple.setProductPrice((double) 1000);
		products.add(apple);
		mobiles.setProducts(products);
	}

	@Test
	void testGetAllProducts() {
		when(productRepository.findAll()).thenReturn(products);
		Assertions.assertEquals(products.size(), productService.getAllProducts().size());
	}

	@Test
	void testGetAllProductsNotFound() {
		List<Product> emptyProducts = new ArrayList<>();
		when(productRepository.findAll()).thenReturn(emptyProducts);
		Assertions.assertThrows(DataNotFoundException.class, () -> productService.getAllProducts());
	}

	@Test
	void testGetProductById() {
		Optional<Product> opProduct = Optional.of(apple);
		when(productRepository.findById((long) 1)).thenReturn(opProduct);
		Assertions.assertEquals((long) 1, productService.getProductById((long) 1).getProductId());
	}

	@Test
	void testGetProductByIdNotFound() {
		Optional<Product> opProduct = Optional.empty();
		when(productRepository.findById(mobiles.getSubCategoryId())).thenReturn(opProduct);
		Assertions.assertThrows(DataNotFoundException.class, () -> productService.getProductById(mobiles.getSubCategoryId()));
	}

	@Test
	void testGetProductsBasedOnSubCategoryWhenSubCategoryFoundButProductsArePresent() {
		Optional<SubCategory> opSubCategory = Optional.of(mobiles);
		when(subCategoryRepository.findById((long) 1)).thenReturn(opSubCategory);
		when(subCategory.getProducts()).thenReturn(products);
		Assertions.assertEquals(products.size(), productService.getProductsBasedOnSubCategory((long) 1).size());
	}

	@Test
	void testGetProductsBasedOnSubCategoryWhenSubCategoryFoundButProductsEmpty() {
		Optional<SubCategory> opSubCategory = Optional.of(laptops);
		when(subCategoryRepository.findById(laptops.getSubCategoryId())).thenReturn(opSubCategory);
		List<Product> emptyProducts = new ArrayList<>();
		when(subCategory.getProducts()).thenReturn(emptyProducts);
		Assertions.assertThrows(DataNotFoundException.class,
				() -> productService.getProductsBasedOnSubCategory(laptops.getSubCategoryId()));
	}
	
	@Test
	void testGetProductsBasedOnSubCategoryButSubCategoryNotFound() {
		Optional<SubCategory> opSubCategory = Optional.empty();
		when(subCategoryRepository.findById((long) 1)).thenReturn(opSubCategory);
		Assertions.assertThrows(DataNotFoundException.class,
				() -> productService.getProductsBasedOnSubCategory((long) 1));
	}

}
