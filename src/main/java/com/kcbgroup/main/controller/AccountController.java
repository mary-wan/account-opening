package com.kcbgroup.main.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kcbgroup.main.exceptions.ResourceNotFoundException;
import com.kcbgroup.main.model.Account;
import com.kcbgroup.main.service.AccountService;
import com.kcbgroup.main.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@Autowired
	CustomerService customerService;

	@PostMapping("/account/{customerId}")
	public ResponseEntity<?> createAccount(@RequestBody Account account, @PathVariable Long customerId) {
		return accountService.createAccount(customerId, account);
		
	}

	@GetMapping("/customer/accounts/{customerId}")
	public ResponseEntity<List<Account>> getAllCustomers(@PathVariable("customerId") Long customerIdNumber) {
			
		return new ResponseEntity<>(accountService.getCustomerAccounts(customerIdNumber), HttpStatus.OK);

	}

}
