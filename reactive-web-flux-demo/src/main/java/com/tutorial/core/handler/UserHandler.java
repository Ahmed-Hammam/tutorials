package com.tutorial.core.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.tutorial.core.model.User;
import com.tutorial.core.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserHandler {

	@Autowired
	private UserService service;

	public Mono<ServerResponse> addUser(ServerRequest request) {
//		Mono<User> newUser = request.bodyToMono(User.class);
		Mono<User> userMono = request.bodyToMono(User.class).flatMap(user -> service.create(user));
		return ServerResponse.ok().contentType(APPLICATION_JSON).body(userMono, User.class);
	}

	public Mono<ServerResponse> getUserById(ServerRequest request) {
		String userId = request.pathVariable("id");
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<User> userMono = service.findById(userId);
		return userMono.flatMap(user -> ServerResponse.ok().contentType(APPLICATION_JSON)
				.body(BodyInserters.fromObject(user)).switchIfEmpty(notFound));
	}

	public Mono<ServerResponse> getUsersByName(ServerRequest request) {
		String userName = request.pathVariable("name");
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Flux<User> users = service.findByName(userName);
		return 	ServerResponse.ok().contentType(APPLICATION_JSON).body(users, User.class).switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> listUsers(ServerRequest request) {
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Flux<User> users = service.findAll();
		return 	ServerResponse.ok().contentType(APPLICATION_JSON).body(users, User.class).switchIfEmpty(notFound);
	}
	
}
