package com.kcbgroup.main.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.kcbgroup.main.model.Account;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component

public class CreateAccountFormatter {
	@Autowired
	private Configuration freemarker;

	@Autowired
	private CustomProperties customProperties;

	public HashMap<String, String> formatAccountCreateRequest(Account requestWrapper) {

		HashMap<String, String> response = new HashMap<String, String>();
		Map<String, Object> templateData = new HashMap<String, Object>();

		try {
			freemarker.setClassForTemplateLoading(this.getClass(), "/templates");
			Template templates = freemarker.getTemplate("account-create.ftl");

			templateData.put("clientUsername", customProperties.getClientUsername());
			templateData.put("clientPassword", customProperties.getClientPassword());

			templateData.put("customerNumber", requestWrapper.getAccountNumber());
			templateData.put("kraPin", requestWrapper.getKraPin());
			
			String requestXml = FreeMarkerTemplateUtils.processTemplateIntoString(templates, templateData);

			log.info("-------------------{}", requestXml);

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
