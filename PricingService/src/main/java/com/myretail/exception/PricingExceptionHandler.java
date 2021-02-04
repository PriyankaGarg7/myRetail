package com.myretail.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class PricingExceptionHandler {

	@ResponseBody
	@ExceptionHandler(PriceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public final ExceptionResponse handlePriceNotFoundException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return exceptionResponse;
	}

	@ResponseBody
	@ExceptionHandler(PriceExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public final ExceptionResponse handlePriceExistsException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return exceptionResponse;
	}
}
