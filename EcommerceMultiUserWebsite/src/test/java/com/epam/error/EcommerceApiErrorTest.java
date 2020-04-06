package com.epam.error;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class EcommerceApiErrorTest {
	
	@InjectMocks
	static EcommerceApiError ecommerceApi;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	static EcommerceApiError error;
	
	@BeforeAll
	public static void setInitVars() {
		error = new EcommerceApiError(HttpStatus.OK,"All Ok");
	}
	
	@Test
	void testGetStatus() {
		error.getStatus();
	}

	@Test
	void testSetStatus() {
		error.setStatus(HttpStatus.OK);
	}

	@Test
	void testGetMessage() {
		error.getMessage();
	}

	@Test
	void testSetMessage() {
		error.setMessage("Changed");
	}

}
