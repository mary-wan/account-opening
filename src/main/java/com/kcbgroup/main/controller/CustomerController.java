package com.kcbgroup.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kcbgroup.main.model.Customer;
import com.kcbgroup.main.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Slf4j
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value="/customer", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
		customerService.createCustomer(customer);
		return new ResponseEntity<>("Customer Created", HttpStatus.CREATED);
	}

	@GetMapping("/all/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);

	}

	@GetMapping("/get/customer/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable Long id) {
		if (customerService.getCustomerById(id) != null) {
			return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
		}
		else {
			log.info("---- Customer with id {} not found -----.", id);
			return new ResponseEntity<>("ID not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/customer/update/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {

		return customerService.updateCustomer(customer, id);

	}
	
	@RequestMapping(value = "/delete/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> Customer(@PathVariable("id") Long id) {
		return customerService.deleteCustomerById(id);
	}

}
