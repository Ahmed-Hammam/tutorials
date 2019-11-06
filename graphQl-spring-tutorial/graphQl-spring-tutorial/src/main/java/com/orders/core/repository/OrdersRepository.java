package com.orders.core.repository;

import org.springframework.data.repository.query.Param;

import com.orders.core.entity.OrderEntity;

public interface OrdersRepository extends BaseRepository<OrderEntity, Long> {

	Iterable<OrderEntity> findByCustomerCode(@Param(value = "code") String code);
}
