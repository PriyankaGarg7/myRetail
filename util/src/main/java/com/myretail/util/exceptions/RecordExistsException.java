package com.myretail.util.exceptions;

public class RecordExistsException extends RuntimeException {

	private static final long serialVersionUID = 2022362735353131173L;

	public RecordExistsException(String message) {
		super(message);
	}
	
}
