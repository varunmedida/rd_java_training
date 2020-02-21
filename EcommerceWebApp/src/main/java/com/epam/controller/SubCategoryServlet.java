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
import com.epam.service.SubCategoryService;
import com.epam.service.SubCategoryServiceImpl;

public class SubCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger(SubCategoryServlet.class);
	SubCategoryService subCategoryService = new SubCategoryServiceImpl();
	CategoryService categoryService=new CategoryServiceImpl();
	public SubCategoryServlet() {
		super();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String categoryOption = request.getParameter("category");
		RequestDispatcher requestDispatcher;
		try {
			if (!subCategoryService.getSubCategoriesBasedOnCategory(categoryOption).isEmpty()) {
				request.setAttribute("subcategories",
						subCategoryService.getSubCategoriesBasedOnCategory(categoryOption));
				request.setAttribute("categoryOption", categoryOption);
				requestDispatcher = request.getRequestDispatcher("subcategory.jsp");
				requestDispatcher.include(request, response);
			} else {
				request.setAttribute("categories", categoryService.getAllCategories());
				request.setAttribute("message", "No SubCategories are present in this option");
				requestDispatcher = request.getRequestDispatcher("category.jsp");
				requestDispatcher.include(request, response);
			}
		} catch (ClassNotFoundException | SQLException exception) {
			logger.info(exception);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

}
