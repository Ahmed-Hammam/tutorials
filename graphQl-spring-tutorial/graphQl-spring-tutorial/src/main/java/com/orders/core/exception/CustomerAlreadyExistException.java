package com.orders.core.exception;

public class CustomerAlreadyExistException extends BusinessException {

	private static final long serialVersionUID = 5629156512465782059L;

	public CustomerAlreadyExistException(String errorCode) {
		super(BusinessErrors.findByErrorCode(errorCode).getErrorMessage());
	}
}
