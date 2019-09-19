package com.tutorial.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

public class MongoConfig extends AbstractReactiveMongoConfiguration {

	@Value("${port}")
    private String port;
     
    @Value("${dbname}")
    private String dbName;
    
	@Override
	public MongoClient reactiveMongoClient() {
		return MongoClients.create();
	}

	@Override
	protected String getDatabaseName() {
		return dbName;
	}
	
	@Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }

}
