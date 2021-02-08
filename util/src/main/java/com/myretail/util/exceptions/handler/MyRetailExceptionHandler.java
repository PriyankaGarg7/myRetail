package com.myretail.util.exceptions.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.myretail.util.exceptions.InvalidInputException;
import com.myretail.util.exceptions.NotFoundException;
import com.myretail.util.exceptions.RecordExistsException;

@ControllerAdvice
@RestController
class MyRetailExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) throws Exception {
		MyRetailErrorInfo exceptionResponse = new MyRetailErrorInfo(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidInputException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(Exception ex, WebRequest request) {
		MyRetailErrorInfo exceptionResponse = new MyRetailErrorInfo(new Date(), ex.getMessage().toString(), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	
	
	@ExceptionHandler(RecordExistsException.class)
	public final ResponseEntity<Object> handleRecordExistsException(Exception ex, WebRequest request) throws Exception {
		MyRetailErrorInfo exceptionResponse = new MyRetailErrorInfo(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}

		MyRetailErrorInfo exceptionResponse = new MyRetailErrorInfo(new Date(), "Validation Failed", errors.toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
