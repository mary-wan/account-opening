package com.kcbgroup.main.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kcbgroup.main.model.Customer;
import com.kcbgroup.main.repository.CustomerRepository;
import com.kcbgroup.main.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomerServiceImplemetation implements CustomerService{
	
	@Autowired 
	CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) {
		try {
			log.info("---------------------------------------");
			return customerRepository.save(customer);
		}catch(Exception ex) {
			log.error(ex.getMessage());
		}
		return null;
	}

	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(String customerIdNumber) {
		return customerRepository.findCustomerBycustomerIdNumber(customerIdNumber);
	}
	

}
