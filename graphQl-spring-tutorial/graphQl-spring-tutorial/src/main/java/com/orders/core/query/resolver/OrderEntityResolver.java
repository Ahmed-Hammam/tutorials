package com.orders.core.query.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.orders.core.entity.OrderEntity;
import com.orders.core.service.OrderService;

@Component
public class OrderEntityResolver implements GraphQLQueryResolver {

	private OrderService orderService;

	@Autowired
	public OrderEntityResolver(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	public Iterable<OrderEntity> getAllOrders() {
		return orderService.findAll();
	}

	public Iterable<OrderEntity> getAllCustomerOrders(String customerCode) {
		return orderService.selectCustomerOrders(customerCode);
	}
}
