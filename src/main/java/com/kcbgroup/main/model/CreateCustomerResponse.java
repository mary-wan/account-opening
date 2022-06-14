package com.kcbgroup.main.model;

import lombok.Data;

@Data
public class CreateCustomerResponse {
	
	private String transactionId;
	private String successIndicator;
	private String successIndicatorDescription;

}
