package com.kcbgroup.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.main.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findCustomerBycustomerIdNumber(Long customerIdNumber);
	
	Customer deleteByCustomerIdNumber(Long customerIdNumber);
	
	

}
