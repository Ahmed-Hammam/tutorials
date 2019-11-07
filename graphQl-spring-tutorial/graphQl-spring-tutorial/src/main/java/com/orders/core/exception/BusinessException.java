package com.orders.core.exception;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1545651765687691824L;

	public BusinessException(String message) {
		super(message);
	}
}
