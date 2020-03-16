package com.epam.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.service.CategoryService;
import com.epam.service.ProductService;

class ShoppingControllerTest {

	private MockMvc mockMvc;

	@Mock
	ProductService productService;
	@Mock
	CategoryService categoryService;

	@InjectMocks
	ShoppingController shoppingController;

	static List<Category> categories;
	static Product firstProduct;
	static Product secondProduct;
	static List<Product> products = new ArrayList<>();

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(shoppingController).build();
	}

	@BeforeAll
	public static void setInitVars() {
		firstProduct = new Product();
		secondProduct = new Product();
		firstProduct.setProductId((long) 1);
		firstProduct.setProductName("Apple");
		secondProduct.setProductId((long) 2);
		secondProduct.setProductName("Samsung");
		products.add(firstProduct);
		products.add(secondProduct);
		categories = new ArrayList<>();
		Category firstCategory = new Category();
		Category secondCategory = new Category();
		firstCategory.setCategoryId((long) 1);
		firstCategory.setCategoryName("Apple");
		secondCategory.setCategoryId((long) 2);
		secondCategory.setCategoryName("Samsung");
		categories.add(firstCategory);
		categories.add(secondCategory);
	}

	@Test
	void testShopbycatgeory() throws Exception {
		Mockito.when(productService.getAllProducts()).thenReturn(products);
		Mockito.when(categoryService.getAllCategories()).thenReturn(categories);
		this.mockMvc.perform(get("/shopbycategory")).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("products")).andExpect(model().attribute("products", products))
				.andExpect(model().attribute("categories", categories));
	}

	@Test
	void testGetProductsBasedOnSubCategoryId() throws Exception {
		Mockito.when(productService.getProductsBasedOnSubCategoryId((long) 1)).thenReturn(products);
		Mockito.when(categoryService.getAllCategories()).thenReturn(categories);
		this.mockMvc.perform(post("/?subCategoryId=1").param("subCategoryId", "1"))
				.andExpect(status().is2xxSuccessful()).andExpect(view().name("products"))
				.andExpect(model().attribute("products", products))
				.andExpect(model().attribute("categories", categories));
	}

}
