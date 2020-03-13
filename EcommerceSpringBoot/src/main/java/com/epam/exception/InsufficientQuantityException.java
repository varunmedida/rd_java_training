package com.epam.exception;

@SuppressWarnings("serial")
public class InsufficientQuantityException extends Exception{

	public InsufficientQuantityException(String message) {
		super(message);
	}
}
