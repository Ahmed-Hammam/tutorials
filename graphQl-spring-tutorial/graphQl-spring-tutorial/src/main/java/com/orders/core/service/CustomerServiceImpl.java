package com.orders.core.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orders.core.entity.CustomerEntity;
import com.orders.core.repository.CustomersRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomersRepository customerRepo;

	@Autowired
	public CustomerServiceImpl(CustomersRepository customerRepo) {
		super();
		this.customerRepo = customerRepo;
	}

	@Override
	public CustomerEntity insert(CustomerEntity entity) {
		return customerRepo.save(entity);
	}

	@Override
	public Optional<CustomerEntity> selectOneById(Long id) {
		return customerRepo.findById(id);
	}

	@Override
	public Optional<CustomerEntity> selectByEntityCode(String code) {
		return customerRepo.findByCode(code);
	}

	@Override
	public Void remove(String code) {
		return customerRepo.deleteByCode(code);
	}

	@Override
	public Optional<CustomerEntity> selectOneByEmail(String email) {
		return customerRepo.findByEmail(email);
	}

}
