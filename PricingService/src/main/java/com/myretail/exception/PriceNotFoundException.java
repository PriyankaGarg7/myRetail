package com.myretail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PriceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 515603064717270618L;

	public PriceNotFoundException(String message) {
		super(message);
	}
}