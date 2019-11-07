package com.orders.core.service;

import java.util.Optional;

import com.orders.core.entity.CustomerEntity;

public interface CustomerService extends BaseService<CustomerEntity, String> {

	Optional<CustomerEntity> selectOneById(Long id);

	Optional<CustomerEntity> selectOneByEmail(String email);
}
