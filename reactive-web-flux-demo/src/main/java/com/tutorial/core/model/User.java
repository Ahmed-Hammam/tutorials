package com.tutorial.core.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="users")
public class User {

	@Id
	private String id = UUID.randomUUID().toString();
	private String name;
	private int age;
	
	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	
	
}
