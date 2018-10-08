package com.tutorial.elasticsearch.exception;

public class ExceptionModel {

	private String errorCode;
	private String message;
	
	public ExceptionModel(String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}
	
	
}