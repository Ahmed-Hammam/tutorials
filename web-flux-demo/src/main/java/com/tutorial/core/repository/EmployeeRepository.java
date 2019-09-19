package com.tutorial.core.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.tutorial.core.model.Employee;

import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Long> {

	@Query("{ 'name' : ?0 }")
	Flux<Employee> findByName(String name);
}
