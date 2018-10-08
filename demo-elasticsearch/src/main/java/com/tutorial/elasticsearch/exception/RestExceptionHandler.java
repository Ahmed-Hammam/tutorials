package com.tutorial.elasticsearch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequestMapping(produces="application/json")
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionModel> invalidOperationHandler(Exception e) {
		ExceptionModel exceptionModel = new ExceptionModel("0001", "General error occurred");
		return new ResponseEntity<ExceptionModel>(exceptionModel,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
}
