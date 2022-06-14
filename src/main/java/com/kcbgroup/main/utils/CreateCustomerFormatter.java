package com.kcbgroup.main.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.kcbgroup.main.model.Customer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CreateCustomerFormatter {
	
	@Autowired
	private Configuration freemarker;

	@Autowired
	private CustomProperties customProperties;
	
	public HashMap<String, String> formatCustomerCreateRequest(Customer requestWrapper) {
		HashMap<String, String> response = new HashMap<String, String>();
		Map<String, Object> templateData = new HashMap<String, Object>();
		
		try {
			freemarker.setClassForTemplateLoading(this.getClass(), "/templates");
			Template templates = freemarker.getTemplate("customer-create.ftl");
			
			templateData.put("clientUsername", customProperties.getClientUsername());
			templateData.put("clientPassword", customProperties.getClientPassword());
			
			templateData.put("firstName", requestWrapper.getFirstName());
			templateData.put("customerIdNumber", requestWrapper.getCustomerIdNumber());
			templateData.put("lastName", requestWrapper.getLastName());
			templateData.put("fullName", requestWrapper.getLastName());
			templateData.put("street", requestWrapper.getFirstName() + " " + requestWrapper.getStreet());
			templateData.put("townCountry", requestWrapper.getStreet());
			
			templateData.put("phoneNumber", requestWrapper.getPhoneNumber());
			templateData.put("email", requestWrapper.getEmail());
			
			
			String requestXml = FreeMarkerTemplateUtils.processTemplateIntoString(templates, templateData);
			
			response.put("RESPONSE_CODE", "000");
			response.put("RESPONSE_BODY", requestXml);
			
			return response;
			
		} catch (Exception e) {
			
			response.put("RESPONSE_CODE", "500");
			response.put("RESPONSE_BODY", e.getMessage());
			
		}
		return response;
	}
	}


