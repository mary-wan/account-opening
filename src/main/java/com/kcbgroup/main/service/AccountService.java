package com.kcbgroup.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kcbgroup.main.model.Account;

@Service
public interface AccountService {
	
	ResponseEntity<Account> createAccount(Long customerIdNumber, Account accountRequest);
	
	List<Account> getCustomerAccounts(Long customerIdNumber);
	
	ResponseEntity<List<Account>> getAllAccountsByCustomerId(String customerId);


}
