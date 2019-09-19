package com.tutorial.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ReactiveWebFluxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveWebFluxDemoApplication.class, args);
	}

}
