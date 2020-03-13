package com.epam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.service.CartServiceImpl;
import com.epam.service.CategoryServiceImpl;
import com.epam.service.ProductServiceImpl;
import com.epam.service.SubCategoryServiceImpl;

public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisplayServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("category.jsp");

		request.setAttribute("categories", new CategoryServiceImpl().getAllCategories());

		String categoryRequest = request.getParameter("category");

		String subcategoryRequest = request.getParameter("subcategory");
		String productRequest = request.getParameter("product");
		String quantityRequest = request.getParameter("quantity");
		handleCategoryRequest(request, categoryRequest);

		handleSubCategoryRequest(request, subcategoryRequest);
		handleAddToCartRequest(request, productRequest, quantityRequest);
		requestDispatcher.forward(request, response);
	}

	private void handleAddToCartRequest(HttpServletRequest request, String productRequest, String quantityRequest) {
		if (productRequest != null && quantityRequest != null) {
			int productOption = Integer.parseInt(productRequest);
			int quantityAdded = Integer.parseInt(quantityRequest);
			request.setAttribute("addedToCart", new CartServiceImpl().addToCart(productOption, quantityAdded));

		}

	}

	private void handleSubCategoryRequest(HttpServletRequest request, String subcategoryRequest) {
		if (subcategoryRequest != null) {
			int subcategoryOption = Integer.parseInt(subcategoryRequest);
			request.setAttribute("products", new ProductServiceImpl().getProductsBasedOnSubCategory(subcategoryOption));

		}

	}

	private void handleCategoryRequest(HttpServletRequest request, String categoryRequest) {
		if (categoryRequest != null) {
			int categoryOption = Integer.parseInt(categoryRequest);
			request.setAttribute("subcategories",
					new SubCategoryServiceImpl().getSubCategoriesBasedOnCategory(categoryOption));

		}

	}

}
