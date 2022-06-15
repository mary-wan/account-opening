package com.kcbgroup.main.service.implementation;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kcbgroup.main.model.Account;
import com.kcbgroup.main.model.Customer;
import com.kcbgroup.main.repository.AccountRepository;
import com.kcbgroup.main.repository.CustomerRepository;
import com.kcbgroup.main.service.AccountService;
import com.kcbgroup.main.utils.CreateAccountFormatter;
import com.kcbgroup.main.utils.HttpClient;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccountServiceImplemetation implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	private CreateAccountFormatter createAccountFormatter;

	@Autowired
	private HttpClient httpClient;

	@Override
	public ResponseEntity<Account> createAccount(Long customerIdNumber, Account accountRequest) {
		
		try {
			Customer customer = customerRepository.findCustomerBycustomerIdNumber(customerIdNumber);

			accountRequest.setCustomer(customer);
			accountRepository.save(accountRequest);

			HashMap<String, String> xmlRequest = new HashMap<String, String>();

			xmlRequest = createAccountFormatter.formatAccountCreateRequest(accountRequest);
			
			if (xmlRequest.get("RESPONSE_CODE").equals("000")) {

				HashMap<String, String> T24 = new HashMap<String, String>();
				
				T24 = httpClient.INVOKE_T24(xmlRequest.get("RESPONSE_BODY"));

				if (T24.get("RESPONSE_CODE").equals("000")) {

					String responseBody = T24.get("RESPONSE_BODY");
					
					String coreTransactionId = StringUtils.substringBetween(responseBody, "<transactionId>",
							"</transactionId>");
					String coreSuccessIndicator = StringUtils.substringBetween(responseBody, "<successIndicator>",
							"</successIndicator>");

					log.info("coreSuccessIndicator ^^^^^^^^^^^^^^^^ {}", coreSuccessIndicator);
					log.info("coreTransactionId ^^^^^^^^^^^^^^^^ {}", coreTransactionId);

					accountRequest.setCustomerAccount(coreSuccessIndicator);

					// 6-RESPOND BACK
					return new ResponseEntity<>(HttpStatus.CREATED);
				

				} else {

					// failure-Handshake
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage());

		}
//		if (customer != null) {
//
//			accountRequest.setCustomer(customer);
////			return new ResponseEntity<>(accountRepository.save(accountRequest), HttpStatus.CREATED);
//			accountRepository.save(accountRequest);
//			
//			HashMap<String, String> xmlRequest = new HashMap<String, String>();
//
//			xmlRequest = createCustomerFormatter.formatCustomerCreateRequest(customer);
//
//			if (xmlRequest != null) {
//
//				// 3-INVOKE T24 SOAP ENDPOINT for CUSTOMER CREATION
//
//				HashMap<String, String> T24 = new HashMap<String, String>();
//				
//				T24 = httpClient.INVOKE_T24(xmlRequest.get("RESPONSE_BODY"));
//
//				if (T24.get("RESPONSE_CODE").equals("000")) {
//
//					String responseBody = T24.get("RESPONSE_BODY");
//
//					// 4-GET RESPONSE - TRANSFORM XML to JSON
//					String coreTransactionId = StringUtils.substringBetween(responseBody, "<transactionId>",
//							"</transactionId>");
//					String coreSuccessIndicator = StringUtils.substringBetween(responseBody, "<successIndicator>",
//							"</successIndicator>");
//
//					log.info("coreSuccessIndicator ^^^^^^^^^^^^^^^^ {}", coreSuccessIndicator);
//					log.info("coreTransactionId ^^^^^^^^^^^^^^^^ {}", coreTransactionId);
//
//					// 5-GET CUSTOMER NUMBER(CIF) from RESPONSE and update records in your DB
//					customer.setCustomerNumber(coreTransactionId);
//								
//			
//				}}		
//			
//
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
		return null;

	}

	@Override
	public List<Account> getCustomerAccounts(Long customerIdNumber) {

		return (List<Account>) accountRepository.findAccountById(customerIdNumber);
	}

	@Override
	public ResponseEntity<List<Account>> getAllAccountsByCustomerId(String customerIdNumber) {
		return null;
	}

}
