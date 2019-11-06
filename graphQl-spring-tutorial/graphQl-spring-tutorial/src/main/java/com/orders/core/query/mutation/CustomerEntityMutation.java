package com.orders.core.query.mutation;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.orders.core.entity.CustomerEntity;
import com.orders.core.service.CustomerService;

@Component
public class CustomerEntityMutation implements GraphQLMutationResolver {

	private CustomerService customerService;

	@Autowired
	public CustomerEntityMutation(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	// method name is same as name in the order.graphqls
	public CustomerEntity createCustomer(String firstName, String lastName, String email) {
		CustomerEntity customerEntity = CustomerEntity.builder().code(UUID.randomUUID().toString()).firstName(firstName)
				.lastName(lastName).email(email).build();
		return customerService.insert(customerEntity);
	}

	// method name is same as name in the order.graphqls
	// void methods disallowed in graphQL so we must return anything in this case we
	// have to return Boolean
	public void removeCustomer(String code) {
		customerService.remove(code);
	}
}
