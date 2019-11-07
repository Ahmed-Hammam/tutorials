package com.orders.core.query.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.orders.core.entity.CustomerEntity;
import com.orders.core.exception.CustomerNotFoundException;
import com.orders.core.service.CustomerService;

@Component
public class CustomerEntityResolver implements GraphQLQueryResolver {

	private CustomerService customerService; // composition (not null)

	@Autowired
	public CustomerEntityResolver(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	public CustomerEntity findCustomer(Long id) {
		return customerService.selectOneById(id).orElseThrow(() -> new CustomerNotFoundException("1000"));
	}

	public CustomerEntity findCustomerByCode(String code) {
		return customerService.selectByEntityCode(code)
				.orElseThrow(() -> new CustomerNotFoundException("1001"));
	}
}
