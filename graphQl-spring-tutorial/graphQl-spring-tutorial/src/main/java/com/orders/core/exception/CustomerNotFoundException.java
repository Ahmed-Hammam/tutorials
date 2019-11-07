package com.orders.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) // 404
public class CustomerNotFoundException extends BusinessException {

	private static final long serialVersionUID = 2395418863339306408L;

	public CustomerNotFoundException(String errorCode) {
		super(BusinessErrors.findByErrorCode(errorCode).getErrorMessage());
	}

}
