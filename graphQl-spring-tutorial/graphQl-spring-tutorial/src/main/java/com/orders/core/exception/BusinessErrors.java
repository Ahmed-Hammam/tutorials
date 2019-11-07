package com.orders.core.exception;

import java.util.Arrays;

import lombok.Getter;

public enum BusinessErrors {

	CUSTOMER_id_NOT_FOUND("1000", "No customer found with the following id !"),
	CUSTOMER_code_NOT_FOUND("1001", "No customer found with the following code !"),
	CUSTOMER_ALREADY_EXIST("1002","A customer with the same email already exist !"),
	INVALID_ORDER("1100", "Invalid order data, please try again");

	private BusinessErrors(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	@Getter
	private String errorCode;
	@Getter
	private String errorMessage;
	
	public static final BusinessErrors findByErrorCode(String code) {
		return Arrays.stream(BusinessErrors.values()).filter(err->err.getErrorCode().equals(code)).findFirst().get();
	}
}
