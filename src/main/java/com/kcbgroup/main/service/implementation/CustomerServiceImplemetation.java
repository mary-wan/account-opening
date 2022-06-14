package com.kcbgroup.main.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kcbgroup.main.model.Customer;
import com.kcbgroup.main.repository.CustomerRepository;
import com.kcbgroup.main.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomerServiceImplemetation implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) {
		try {
			log.info("---------------------------------------");
			return customerRepository.save(customer);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return null;
	}

	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(Long customerIdNumber) {
		return customerRepository.findCustomerBycustomerIdNumber(customerIdNumber);
	}

	@Override
	public ResponseEntity<?> updateCustomer(Customer customer,Long customerIdNumber) {
		try {
			Customer cust = customerRepository.findCustomerBycustomerIdNumber(customerIdNumber).orElseThrow();
			cust.setFirstName(customer.getFirstName());
			cust.setLastName(customer.getLastName());
			cust.setDocumentType(customer.getDocumentType());
			cust.setCustomerIdNumber(customer.getCustomerIdNumber());
			cust.setDateOfBirth(customer.getDateOfBirth());
			cust.setGender(customer.getGender());
			cust.setPhoneNumber(customer.getPhoneNumber());
			cust.setEmail(customer.getEmail());
			return new ResponseEntity<>(customerRepository.save(cust), HttpStatus.OK);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<HttpStatus> deleteCustomerById(Long customerIdNumber) {
		customerRepository.deleteById(customerIdNumber);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
