package com.tutorial.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= "com.tutorial.elasticsearch.*")
public class DemoElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoElasticsearchApplication.class, args);
	}
}
