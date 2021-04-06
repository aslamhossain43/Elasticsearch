package com.es.main;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.es.main.model.Customer;
import com.es.main.repo.CustomerRepository;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.es.main.repo")
@ComponentScan(basePackages = {"com.es.main.repo","com.es.main.controllers"})
public class ElasticsearchApplication {
	@Autowired
	private ElasticsearchOperations elasticsearchOperations;
	@Autowired
	private CustomerRepository repository;

	@PostConstruct
	public void buildIndex() {
		elasticsearchOperations.indexOps(Customer.class).refresh();
		Customer customer = new Customer("1", "Aslam", "Hossain", 25);
		repository.save(customer);
	}
	

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchApplication.class, args);
	}

}
