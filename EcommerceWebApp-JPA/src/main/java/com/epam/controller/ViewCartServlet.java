package com.epam.controller;

import java.io.IOException;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.model.CartItem;
import com.epam.service.CartService;
import com.epam.service.CartServiceImpl;

public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger(ViewCartServlet.class);
	CartService cartService = new CartServiceImpl();
	public ViewCartServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		RequestDispatcher requestDispatcher;
		Set<CartItem> cartItems = cartService.viewCart();
		request.setAttribute("cart",cartItems);
		request.setAttribute("totalAmount", cartService.calculateAmount(cartItems));
		requestDispatcher = request.getRequestDispatcher("viewcart.jsp");
		requestDispatcher.forward(request, response);
		}catch (ServletException | IOException message) {
			logger.info(message);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
