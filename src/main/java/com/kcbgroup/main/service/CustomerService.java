package com.kcbgroup.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kcbgroup.main.model.Customer;

@Service
public interface CustomerService {
	
	Customer createCustomer(Customer customer);
	
	List<Customer> getCustomers();
	
	Optional<Customer> getCustomerById(Long customerIdNumber);

}
