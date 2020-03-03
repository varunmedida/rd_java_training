package com.epam.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.service.CartServiceImpl;

class ViewCartServletTest {

	@Mock
	CartServiceImpl cartService;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private RequestDispatcher rd;
	@InjectMocks
	private ViewCartServlet servlet;

	@BeforeEach
	void initialize() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testViewCartServlet() {
		servlet= new ViewCartServlet();
		assertNotNull(servlet);
	}

	@Test
	void testDoGetHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		when(request.getRequestDispatcher("categories.jsp")).thenReturn(rd);
		doThrow(new ServletException("Servlet Exception"));
		 verify(rd).forward(request, response);
	}

	@Test
	void testDoPostHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		servlet.doGet(request, response);
	}

}
