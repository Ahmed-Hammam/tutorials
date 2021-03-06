package com.orders.core.repository;

import java.util.Optional;

import com.orders.core.entity.CustomerEntity;

// @Repository is not needed as the implementation class SimpleJpaRepository annotated with @Repository
public interface CustomersRepository extends BaseRepository<CustomerEntity, Long> {

	Optional<CustomerEntity> findByEmail(String email);
}
