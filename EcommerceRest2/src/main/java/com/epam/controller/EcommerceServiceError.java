package com.epam.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.epam.error.EcommerceApiError;
import com.epam.exception.DataNotFoundException;
import com.epam.exception.InsufficientQuantityException;

@RestControllerAdvice
public class EcommerceServiceError extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ DataNotFoundException.class})
	public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException exception, HttpHeaders headers,
			HttpStatus status) {
		EcommerceApiError error = new EcommerceApiError(HttpStatus.NOT_FOUND, exception.getMessage());
		return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
	}
	
	@ExceptionHandler({ InsufficientQuantityException.class})
	public ResponseEntity<Object> handleInsufficientQuantityException(InsufficientQuantityException exception, HttpHeaders headers,
			HttpStatus status) {
		EcommerceApiError error = new EcommerceApiError(HttpStatus.BAD_REQUEST, exception.getMessage());
		return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
	}
}