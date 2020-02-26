package com.epam.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CategoryServletTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testDoGetHttpServletRequestHttpServletResponse() throws IOException, ServletException {
		when(request.getParameter("category")).thenReturn("1");
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(response.getWriter()).thenReturn(pw);
		CategoryServlet categoryServlet = new CategoryServlet();
		categoryServlet.doGet(request, response);
		String result = sw.getBuffer().toString().trim();
		Assertions.assertEquals(result, new String("1"));
	}

	@Test
	void testDoPostHttpServletRequestHttpServletResponse() {
		fail("Not yet implemented");
	}

}
