package com.epam.exception;

@SuppressWarnings("serial")
public class EmptyCartException extends Exception {
	public EmptyCartException(String message) {
		super(message);
	}
}
