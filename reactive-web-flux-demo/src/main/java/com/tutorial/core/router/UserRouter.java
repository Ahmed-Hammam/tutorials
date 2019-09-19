package com.tutorial.core.router;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.tutorial.core.handler.UserHandler;

@Configuration
public class UserRouter {
	
	@Autowired
	private UserHandler handler;
	
	@Bean
	public RouterFunction<ServerResponse> route() {
        return RouterFunctions
            .route(GET("/users/{id}").and(accept(APPLICATION_JSON)), handler::getUserById)
            .andRoute(GET("/users/name").and(accept(APPLICATION_JSON)), handler::getUsersByName)
            .andRoute(GET("/users").and(accept(APPLICATION_JSON)), handler::listUsers)
            .andRoute(POST("/users").and(contentType(APPLICATION_JSON)), handler::addUser);
        
    }
}
