package com.kcbgroup.main.service.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kcbgroup.main.model.Account;
import com.kcbgroup.main.model.Customer;
import com.kcbgroup.main.repository.CustomerRepository;
import com.kcbgroup.main.service.CustomerService;
import com.kcbgroup.main.utils.CreateCustomerFormatter;
import com.kcbgroup.main.utils.HttpClient;
import com.kcbgroup.main.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomerServiceImplemetation implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	private CreateCustomerFormatter createCustomerFormatter;

	@Autowired
	private HttpClient httpClient;

	@Override
	public ResponseEntity<Customer> createCustomer(Customer customer) {
		try {

			// 1-SAVE REQUEST TO DATABASE

			// 2-TRANSFORM JSON TO XML for T24
			HashMap<String, String> t24XmlRequest = new HashMap<String, String>();

			t24XmlRequest = createCustomerFormatter.formatCustomerCreateRequest(customer);

			if (t24XmlRequest.get("RESPONSE_CODE").equals("000")) {

				// 3-INVOKE T24 SOAP ENDPOINT for CUSTOMER CREATION

				HashMap<String, String> T24Response = new HashMap<String, String>();
				
				T24Response = httpClient.INVOKE_T24(t24XmlRequest.get("RESPONSE_BODY"));

				if (T24Response.get("RESPONSE_CODE").equals("000")) {

					String responseBody = T24Response.get("RESPONSE_BODY");

					// 4-GET RESPONSE - TRANSFORM XML to JSON
					String transactionId = StringUtils.substringBetween(responseBody, "<transactionId>",
							"</transactionId>");
					String successIndicator = StringUtils.substringBetween(responseBody, "<successIndicator>",
							"</successIndicator>");

					log.info("coreSuccessIndicator ^^^^^^^^^^^^^^^^ {}", successIndicator);
					log.info("coreTransactionId ^^^^^^^^^^^^^^^^ {}", transactionId);
					
					Utils utils = new Utils();
					String customerNumber = String.valueOf(utils.generate()); 

					// 5-GET CUSTOMER NUMBER(CIF) from RESPONSE and update records in your DB
					customer.setCustomerNumber(customerNumber);
					customerRepository.save(customer);
					
					return new ResponseEntity<>(HttpStatus.CREATED);

				} else {
					Utils utils = new Utils();
					String mockedResponse = utils.mockCreateCustomerResponse();
					
					String coreTransactionId = StringUtils.substringBetween(mockedResponse, "<transactionId>","</transactionId>");
					String coreSuccessIndicator = StringUtils.substringBetween(mockedResponse, "<successIndicator>","</successIndicator>");
					String customerNumber = String.valueOf(utils.generate()); 
		
					customer.setCustomerNumber(customerNumber);
					customerRepository.save(customer);
					return new ResponseEntity<>(HttpStatus.CREATED);//201

					// failure-Handshake
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());

		}
		return null;
	}

	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	
	@Override
	public Customer getCustomerById(Long customerIdNumber) {
		return customerRepository.findCustomerBycustomerIdNumber(customerIdNumber);
	}

	@Override
	public ResponseEntity<?> updateCustomer(Customer customer, Long customerIdNumber) {
//		try {
//			Customer cust = customerRepository.findCustomerBycustomerIdNumber(customerIdNumber).orElseThrow();
//			cust.setFirstName(customer.getFirstName());
//			cust.setLastName(customer.getLastName());
//			cust.setDocumentType(customer.getDocumentType());
//			cust.setCustomerIdNumber(customer.getCustomerIdNumber());
//			cust.setDateOfBirth(customer.getDateOfBirth());
//			cust.setGender(customer.getGender());
//			cust.setPhoneNumber(customer.getPhoneNumber());
//			cust.setEmail(customer.getEmail());
//			return new ResponseEntity<>(customerRepository.save(cust), HttpStatus.OK);
//
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//		}
		return null;
	}

	@Override
	public ResponseEntity<HttpStatus> deleteCustomerById(Long customerIdNumber) {
		customerRepository.deleteById(customerIdNumber);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
