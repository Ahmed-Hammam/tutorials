package com.orders.core.service;

import com.orders.core.entity.OrderEntity;

public interface OrderService extends BaseService<OrderEntity, String> {

	Iterable<OrderEntity> findAll();

	Iterable<OrderEntity> selectCustomerOrders(String customerCode);
}
