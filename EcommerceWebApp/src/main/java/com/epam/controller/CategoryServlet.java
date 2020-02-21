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

public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger(CategoryServlet.class);
	CategoryService categoryService = new CategoryServiceImpl();

	public CategoryServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			RequestDispatcher requestDispatcher;
			request.setAttribute("categories", categoryService.getAllCategories());
			requestDispatcher = request.getRequestDispatcher("category.jsp");
			requestDispatcher.include(request, response);
		} catch (ClassNotFoundException | SQLException |

				IOException exception) {
			logger.info(exception);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
