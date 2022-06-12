package com.kcbgroup.main.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kcbgroup.main.exceptions.ResourceNotFoundException;
import com.kcbgroup.main.model.Account;
import com.kcbgroup.main.repository.AccountRepository;
import com.kcbgroup.main.repository.CustomerRepository;
import com.kcbgroup.main.service.AccountService;

@Component
public class AccountServiceImplemetation implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public ResponseEntity<Account> createAccount(Long customerId, Account accountRequest) {

		Account account = customerRepository.findById(customerId).map(customer -> {
			accountRequest.setCustomer(customer);
			return accountRepository.save(accountRequest);
		}).orElseThrow(() -> new ResourceNotFoundException("Not found Customer with id = " + customerId));
		return new ResponseEntity<>(account, HttpStatus.CREATED);

	}


	@Override
	public List<Account> getCustomerAccounts(String customerIdNumber) {
		// TODO Auto-generated method stub
		return (List<Account>) accountRepository.findAccountByCustomerId(customerIdNumber);
	}
	


	@Override
	public ResponseEntity<List<Account>> getAllAccountsByCustomerId(String customerIdNumber) {
		return null;
	}
	

//	@Override
//	public ResponseEntity<List<Account>> getAllAccountsByCustomerId(Long customerId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
