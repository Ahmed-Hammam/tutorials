package com.tutorial.core.service;

import com.tutorial.core.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

	Mono<User> create(User user);
	Mono<User> findById(String id);
	Flux<User> findByName(String name);
	Flux<User> findAll();
	
}
