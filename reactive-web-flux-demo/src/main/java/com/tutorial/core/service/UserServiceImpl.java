package com.tutorial.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.core.model.User;
import com.tutorial.core.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public Mono<User> create(User user) {
		 /*Mono<User> userMono = user.doOnNext(value->{
			repository.save(value);                      
        } );
		return userMono.then();*/
		
		return repository.save(user);
	}

	@Override
	public Mono<User> findById(String id) {
		return repository.findById(id);
	}

	@Override
	public Flux<User> findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Flux<User> findAll() {
		return repository.findAll();
	}

}
