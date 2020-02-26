package com.epam.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.model.Category;
import com.epam.service.CategoryService;
import com.epam.service.CategoryServiceImpl;
import com.epam.service.ProductService;
import com.epam.service.ProductServiceImpl;
import com.epam.service.SubCategoryService;
import com.epam.service.SubCategoryServiceImpl;

public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger(CategoryServlet.class);
	CategoryService categoryService = new CategoryServiceImpl();
	SubCategoryService subCategoryService = new SubCategoryServiceImpl();
	ProductService productService = new ProductServiceImpl();
	public CategoryServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			  EntityManagerFactory factory =
			  Persistence.createEntityManagerFactory("my-local-db"); EntityManager
			  entityManager = factory.createEntityManager(); Category category =
			  entityManager.find(Category.class, 1); System.out.println(category);
			 
			RequestDispatcher requestDispatcher;
			request.setAttribute("categories", categoryService.getAllCategories());

			String categoryOption = request.getParameter("category");
			request.setAttribute("subcategories", subCategoryService.getSubCategoriesBasedOnCategory(categoryOption));

			String subcategoryOption = request.getParameter("subcategory");
			request.setAttribute("products", productService.getProductsBasedOnSubCategory(subcategoryOption));
			
			requestDispatcher = request.getRequestDispatcher("category.jsp");
			requestDispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException |

				IOException exception) {
			logger.info(exception);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	} 

}
