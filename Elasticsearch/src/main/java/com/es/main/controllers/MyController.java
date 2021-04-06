package com.es.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.es.main.model.Customer;
import com.es.main.repo.CustomerRepository;

@SuppressWarnings("deprecation")
@RestController
public class MyController {
	@Autowired
	private ElasticsearchOperations elasticsearchOperations;

	@Autowired
	private CustomerRepository repository;

	@RequestMapping(value = "")
	public String sayHello() {
		return "Hello";
	}

	@PostMapping("/saveCustomer")
	public int saveCustomer(@RequestBody List<Customer> customers) {
		elasticsearchOperations.indexOps(Customer.class).create();
		repository.saveAll(customers);
		return customers.size();
	}

	@GetMapping("/findAll")
	public Iterable<Customer> findAllCustomers() {
		return repository.findAll();
	}

	@GetMapping("/findByFName/{firstName}")
	public List<Customer> findByFirstName(@PathVariable String firstName) {
		return repository.findByFirstname(firstName);
	}
}
