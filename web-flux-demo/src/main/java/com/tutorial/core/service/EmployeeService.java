package com.tutorial.core.service;

import com.tutorial.core.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

	// CRUD
	void create(Employee e);
	Mono<Employee> update(Employee e);
	Mono<Void> delete(long id);
	Mono<Employee> findById(long id);

	// custom
	Flux<Employee> findByName(String name);
    Flux<Employee> findAll();
}
