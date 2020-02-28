package com.epam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.service.CartService;
import com.epam.service.CartServiceImpl;
import com.epam.service.CategoryService;
import com.epam.service.CategoryServiceImpl;
import com.epam.service.ProductService;
import com.epam.service.ProductServiceImpl;
import com.epam.service.SubCategoryService;
import com.epam.service.SubCategoryServiceImpl;

public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger(DisplayServlet.class);
	CategoryService categoryService = new CategoryServiceImpl();
	SubCategoryService subCategoryService = new SubCategoryServiceImpl();
	ProductService productService = new ProductServiceImpl();
	final CartService cartService = new CartServiceImpl();

	public DisplayServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher requestDispatcher;
		try {
			request.setAttribute("categories", categoryService.getAllCategories());

			String categoryRequest = request.getParameter("category");
			requestDispatcher = request.getRequestDispatcher("category.jsp");
			if (categoryRequest != null) {
				int categoryOption = Integer.parseInt(categoryRequest);
				request.setAttribute("subcategories",
						subCategoryService.getSubCategoriesBasedOnCategory(categoryOption));
				requestDispatcher = request.getRequestDispatcher("category.jsp");
			}
			String subcategoryRequest = request.getParameter("subcategory");
			if (subcategoryRequest != null) {
				int subcategoryOption = Integer.parseInt(subcategoryRequest);
				request.setAttribute("products", productService.getProductsBasedOnSubCategory(subcategoryOption));
				requestDispatcher = request.getRequestDispatcher("category.jsp");

			}
			String productRequest = request.getParameter("product");
			String quantityRequest = request.getParameter("quantity");
			if (productRequest != null && quantityRequest != null) {

				int productOption = Integer.parseInt(productRequest);
				int quantityAdded = Integer.parseInt(quantityRequest);
				request.setAttribute("addedToCart", cartService.addToCart(productOption, quantityAdded));
				requestDispatcher = request.getRequestDispatcher("category.jsp");
				requestDispatcher.forward(request, response);

			}
			requestDispatcher.forward(request, response);
		} catch (IOException | ServletException message) {
			logger.info(message);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
