package com.kcbgroup.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kcbgroup.main.model.Customer;

@Service
public interface CustomerService {
	
	ResponseEntity<Customer> createCustomer(Customer customer);
	
	List<Customer> getCustomers();

	Customer getCustomerById(Long customerIdNumber);
	
	ResponseEntity<?> updateCustomer(Customer customer,Long customerIdNumber);
	
	ResponseEntity<HttpStatus> deleteCustomerById(Long customerIdNumber);

}
