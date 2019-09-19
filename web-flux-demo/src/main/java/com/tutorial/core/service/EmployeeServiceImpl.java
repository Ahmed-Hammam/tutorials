package com.tutorial.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.core.model.Employee;
import com.tutorial.core.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public void create(Employee e) {
		employeeRepository.save(e);
	}

	@Override
	public Mono<Employee> update(Employee e) {
		return employeeRepository.save(e);
	}

	@Override
	public Mono<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}
	
	@Override
	public Mono<Void> delete(long id) {
		return employeeRepository.deleteById(id);
	}

	@Override
	public Flux<Employee> findByName(String name) {
		return employeeRepository.findByName(name);
	}

	@Override
	public Flux<Employee> findAll() {
		return employeeRepository.findAll();
	}

}
