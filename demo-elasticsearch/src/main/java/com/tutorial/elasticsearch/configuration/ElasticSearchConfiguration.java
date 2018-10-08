package com.tutorial.elasticsearch.configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@Configuration
//@EnableElasticsearchRepositories(basePackages= "com.tutorial.elasticsearch.repository")
	public class ElasticSearchConfiguration {

	@Value("${elasticsearch.clustername}")
	private String elasticClusterName;
	
	@Value("${elasticsearch.host}")
	private String elasticHost;
	
	@Value("${elasticsearch.port}")
	private int elasticPort;
	
	
	@Bean
	public Client client() throws UnknownHostException {
		Settings elasticSearchSettings = Settings.builder()
                .put("cluster.name", elasticClusterName)
                .put("node.name","node-1")
                .build();
		
		return TransportClient.builder()
                .settings(elasticSearchSettings)
                .build()
                .addTransportAddress(
				  new InetSocketTransportAddress(InetAddress.getByName(elasticHost), elasticPort));
	}
	
	@Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(NodeBuilder.nodeBuilder().local(true).build().client());
    }
}
