package com.epam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InsufficientQuantityException extends RuntimeException {

	public InsufficientQuantityException(String message) {
		super(message);
	}
}
