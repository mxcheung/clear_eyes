package com.maxcheung.rest;


import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.maxcheung.domain.Customer;
import com.maxcheung.domain.CustomerRepository;



@RestController
@RequestMapping("/v1/springboot/customer")
public class CustomerRestController {

	private final CustomerRepository customerRepository;

	
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> customer( @RequestBody Customer customer) {
		customer = this.customerRepository.save(customer);
		HttpHeaders httpHeaders = new HttpHeaders();
		String id = "" + customer.getId();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replaceQueryParam("id", "{id}").buildAndExpand(id).toUri();
		httpHeaders.setLocation(uri);
		return new ResponseEntity<>(uri, httpHeaders, HttpStatus.ACCEPTED);
	}
		
	@RequestMapping( method = RequestMethod.GET)
	Customer getCustomer( Long id) {
		return this.customerRepository.findOne(id);
	}

	@RequestMapping(value ="/fetchbyname", method = RequestMethod.GET)
	Customer getCustomerByLastname( String lastname) {
		this.validateUser(lastname);
		return this.customerRepository.findByLastname(lastname).get();
	}
	
	
	@RequestMapping(value = "customers", method = RequestMethod.GET)
	List<Customer> getAllCustomer() {
		return this.customerRepository.findAll();
	}
	
	
	@RequestMapping(value = "fetchOne", method = RequestMethod.GET)
	Customer readBookmark( Long id) {
		return this.customerRepository.findOne(id);
	}


	@RequestMapping(value = "fetchAll", method = RequestMethod.GET)
	List<Customer> readBookmark() {
		return this.customerRepository.findAll();
	}

	@Autowired
	CustomerRestController( CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	
	@RequestMapping(value = "getUrl1", method = RequestMethod.GET)
	ResponseEntity<?> getUrl1() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "getUrl2", method = RequestMethod.GET)
	ResponseEntity<?> getUrl2() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand("22").toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	ResponseEntity<?> add( String lastname) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand("id").toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);

	}
	
	
	private void validateUser(String lastname) {
		this.customerRepository.findByLastname(lastname).orElseThrow(() -> new CustomerNotFoundException(lastname));
	}
	
	
}


