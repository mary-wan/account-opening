package com.kcbgroup.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kcbgroup.main.model.Customer;

@Service
public interface CustomerService {
	
	Customer createCustomer(Customer customer);
	
	List<Customer> getCustomers();
	
	Customer getCustomerById(String customerIdNumber);

}
