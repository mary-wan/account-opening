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
import com.kcbgroup.main.utils.Utils;

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
		String CIF = null;
		try {
			
			List<Customer> customerList = customerRepository.findByCustomerIdNumber(customerIdNumber);
			
			if(customerList.size() > 0) {
				log.info("******************************************");
				CIF = customerList.get(0).getCustomerNumber();
			}
			
			Customer customer = customerRepository.findCustomerBycustomerIdNumber(customerIdNumber);
			
			customerRepository.findCustomerBycustomerIdNumber(customerIdNumber);

			accountRequest.setCustomer(customer);
			
			HashMap<String, String> t24XmlRequest = new HashMap<String, String>();

			t24XmlRequest = createAccountFormatter.formatAccountCreateRequest(accountRequest);
			
			if (t24XmlRequest.get("RESPONSE_CODE").equals("000")) {

				HashMap<String, String> T24 = new HashMap<String, String>();
				
				T24 = httpClient.INVOKE_T24(t24XmlRequest.get("RESPONSE_BODY"));

				if (T24.get("RESPONSE_CODE").equals("000")) {

					String responseBody = T24.get("RESPONSE_BODY");
					
					String coreTransactionId = StringUtils.substringBetween(responseBody, "<transactionId>","</transactionId>");
					String coreSuccessIndicator = StringUtils.substringBetween(responseBody, "<successIndicator>","</successIndicator>");
					log.info("coreSuccessIndicator ^^^^^^^^^^^^^^^^ {}", coreSuccessIndicator);
					log.info("coreTransactionId ^^^^^^^^^^^^^^^^ {}", coreTransactionId);
					accountRequest.setCustomerAccount(coreSuccessIndicator);
					
					Utils utils = new Utils();
					String accountNumber = String.valueOf(utils.generate()); //MOCK RANDOM A/C GENERATION
					
					accountRequest.setAccountNumber(accountNumber);
					accountRepository.save(accountRequest);
					// 6-RESPOND BACK
					return new ResponseEntity<Account>(HttpStatus.CREATED);//201
				

				} else {
                    //MOCK RESPONSE
					//failure-Handshake
					
					Utils utils = new Utils();
					String mockedResponse = utils.mockCreateAccountResponse();
					
					String coreTransactionId = StringUtils.substringBetween(mockedResponse, "<transactionId>","</transactionId>");
					String coreSuccessIndicator = StringUtils.substringBetween(mockedResponse, "<successIndicator>","</successIndicator>");
					String accountNumber = String.valueOf(utils.generate()); //MOCK RANDOM A/C GENERATION
		
					accountRequest.setAccountNumber(accountNumber);
					accountRequest.setCustomerNumber(CIF == null ? customer.getCustomerNumber() : CIF);
	
					//accountRequest.getCustomer().setCustomerNumber(customer.getCustomerNumber());
					accountRepository.save(accountRequest);
					return new ResponseEntity<>(HttpStatus.CREATED);//201
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		//return null;
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
