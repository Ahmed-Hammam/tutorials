package com.orders.core.query.mutation;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.orders.core.entity.CustomerEntity;
import com.orders.core.entity.OrderEntity;
import com.orders.core.service.CustomerService;
import com.orders.core.service.OrderService;

@Component
public class OrderEntityMutation implements GraphQLMutationResolver {

	private OrderService orderService;
	private CustomerService customerService;

	@Autowired
	public OrderEntityMutation(OrderService orderService, CustomerService customerService) {
		super();
		this.orderService = orderService;
		this.customerService = customerService;
	}

	public OrderEntity createOrder(Long customerId) {
		CustomerEntity customer = customerService.selectOneById(customerId)
				.orElseThrow(() -> new NullPointerException("No customer found !"));
		OrderEntity entity = OrderEntity.builder().creationDate(Instant.now())
				.customer(customer)
				.build();
		entity.setCode(UUID.randomUUID().toString());
		return orderService.insert(entity);
	}

	// void methods disallowed in graphQL so we must return anything in this case we
	// have to return Boolean
	public void removeOrder(String orderCode) {
		orderService.remove(orderCode);
	}
}
