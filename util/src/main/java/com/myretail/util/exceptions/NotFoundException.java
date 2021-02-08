package com.myretail.util.exceptions;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8159129719151032057L;

	public NotFoundException(String message) {
		super(message);
	}
}
