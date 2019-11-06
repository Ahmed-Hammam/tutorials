package com.orders.core.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orders.core.entity.OrderEntity;
import com.orders.core.repository.OrdersRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private OrdersRepository orderRepo;

	@Autowired
	public OrderServiceImpl(OrdersRepository orderRepo) {
		super();
		this.orderRepo = orderRepo;
	}

	@Override
	public OrderEntity insert(OrderEntity entity) {
		return orderRepo.save(entity);
	}

	@Override
	public Iterable<OrderEntity> findAll() {
		return orderRepo.findAll();
	}

	@Override
	public Optional<OrderEntity> selectByEntityCode(String code) {
		return orderRepo.findByCode(code);
	}

	@Override
	public Void remove(String code) {
		return orderRepo.deleteByCode(code);
	}

	@Override
	public Iterable<OrderEntity> selectCustomerOrders(String customerCode) {
		return orderRepo.findByCustomerCode(customerCode);
	}

}
