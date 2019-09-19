package com.tutorial.core.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.tutorial.core.model.User;

import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

	@Query("{name : ?0}")
	Flux<User> findByName(String name);
}
