package com.epam.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import com.epam.exception.DataNotFoundException;
import com.epam.exception.InsufficientQuantityException;

class EcommerceServiceErrorTest {

	@InjectMocks
	EcommerceServiceError serviceError;

	@Mock
	DataNotFoundException exception;
	
	@Mock
	InsufficientQuantityException exception2;
	@Mock
	HttpHeaders headers;
	@Mock
	WebRequest request;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testHandleDataNotFoundException() {
		Assertions.assertNotNull(serviceError.handleDataNotFoundException(exception, headers, HttpStatus.NOT_FOUND));
	}

	@Test
	void testHandleInsufficientQuantityException() {
		Assertions.assertNotNull(serviceError.handleInsufficientQuantityException(exception2, headers, HttpStatus.BAD_REQUEST));
	}

}
