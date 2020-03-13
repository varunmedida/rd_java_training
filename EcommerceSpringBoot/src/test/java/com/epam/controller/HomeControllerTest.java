package com.epam.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.epam.model.Product;
import com.epam.service.ProductService;

class HomeControllerTest {

	private MockMvc mockMvc;

	@Mock
	ProductService productService;

	@InjectMocks
	HomeController homeController;

	static Product firstProduct;
	static Product secondProduct;
	static List<Product> products = new ArrayList<>();

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();

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
	}

	@Test
	void testHomePage() throws Exception {
		Mockito.when(productService.getAllProducts()).thenReturn(products);
		this.mockMvc.perform(get("/")).andExpect(status().is2xxSuccessful()).andExpect(view().name("index"))
				.andExpect(model().attribute("products", products));
	}

	@Test
	void testHomePage1() throws Exception {
		this.mockMvc.perform(get("/fdfdf")).andExpect(status().is4xxClientError());
	}

}
