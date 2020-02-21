package com.epam.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.service.CategoryService;
import com.epam.service.CategoryServiceImpl;
import com.epam.service.ProductService;
import com.epam.service.ProductServiceImpl;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger(ProductServlet.class);
	ProductService productService = new ProductServiceImpl();
	CategoryService categoryService=new CategoryServiceImpl();
	public ProductServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String subcategoryOption = request.getParameter("subcategory");
		RequestDispatcher requestDispatcher;
		try {
			if (!productService.getProductsBasedOnSubCategory(subcategoryOption).isEmpty()) {
				request.setAttribute("products", productService.getProductsBasedOnSubCategory(subcategoryOption));
				requestDispatcher = request.getRequestDispatcher("product.jsp");
				requestDispatcher.include(request, response);
			}else {
				request.setAttribute("categories", categoryService.getAllCategories());
				request.setAttribute("message", "No Products are present in this option");
				requestDispatcher = request.getRequestDispatcher("category.jsp");
				requestDispatcher.include(request, response);
			}
		} catch (ClassNotFoundException | SQLException exception) {
			logger.info(exception);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
