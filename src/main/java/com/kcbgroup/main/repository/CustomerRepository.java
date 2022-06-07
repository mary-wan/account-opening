package com.kcbgroup.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcbgroup.main.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
