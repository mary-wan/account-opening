package com.kcbgroup.main.utils;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpClient {
	
	@Autowired
	private RestTemplateConfig restTemplateConfig;
	
	@Autowired
	private CustomProperties customProperties;
			
	private Logger logger = LoggerFactory.getLogger(HttpClient.class);
	
	public HashMap<String, String>  INVOKE_T24(String requestXml) {
		
		String HttpMessageCode = "500";
		String HttpMessage = null;
		
		HttpHeaders headers = new HttpHeaders();
		HashMap<String, String> responsePayload = new HashMap<String, String>();

		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_XML, MediaType.TEXT_XML }));
		headers.setContentType(MediaType.TEXT_XML);
	
		HttpEntity<String> entity = new HttpEntity<String>(requestXml, headers);
		
		final String BASE_URL = customProperties.getBaseUrl() + customProperties.getBaseResource();
		
		
		try {
			
			RestTemplate restTemplate = restTemplateConfig.restTemplateByPassSSL();
			
			ResponseEntity<String> response = restTemplate.exchange(
					BASE_URL,
					HttpMethod.POST,
					entity, 
					String.class
			);

			HttpStatus statusCode = response.getStatusCode();
			
			if (statusCode == HttpStatus.OK) {
				HttpMessageCode = "000";
			}else {
				HttpMessageCode = "500";
			}
			
			HttpMessage = response.getBody();
			
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
				logger.error("Error::NOT_FOUND::Exception [{}]", e.getMessage()); 
				HttpMessageCode = e.getStatusCode().toString();
				HttpMessage = e.getMessage();
			} else if (HttpStatus.BAD_REQUEST.equals(e.getStatusCode())) {
				logger.error("Error::BAD_REQUEST::Exception [{}]", e.getMessage()); 
				HttpMessageCode = e.getStatusCode().toString();
				HttpMessage = e.getMessage();
			} else if (HttpStatus.GATEWAY_TIMEOUT.equals(e.getStatusCode())) {
				logger.error("Error::GATEWAY_TIMEOUT::Exception [{}]", e.getMessage()); 
				HttpMessageCode = e.getStatusCode().toString();
				HttpMessage = e.getMessage();
			} else if (HttpStatus.INTERNAL_SERVER_ERROR.equals(e.getStatusCode())) {
				logger.error("Error::INTERNAL_SERVER_ERROR::Exception [{}]",e.getMessage()); 
				HttpMessageCode = e.getStatusCode().toString();
				HttpMessage = e.getMessage();
			} else if (HttpStatus.REQUEST_TIMEOUT.equals(e.getStatusCode())) {
				logger.error("Error::REQUEST_TIMEOUT::Exception [{}]", e.getMessage()); 
				HttpMessageCode = e.getStatusCode().toString();
				HttpMessage = e.getMessage();
			} else {
				logger.error("Error::Exception [{}]", e.getMessage()); 
				HttpMessageCode = e.getStatusCode().toString();
				HttpMessage = e.getMessage();
			}
		} catch (RestClientException ex) {
			HttpMessage = ex.getMessage();
		} catch (KeyManagementException e) {
			HttpMessage = e.getMessage();
		} catch (KeyStoreException e) {
			HttpMessage = e.getMessage();
		} catch (NoSuchAlgorithmException e) {
			HttpMessage = e.getMessage();
		} catch (Exception e) {
			HttpMessage = e.getMessage();
		}
		logger.error("Error::Exception [{}]", HttpMessage);
		responsePayload.put("RESPONSE_CODE", HttpMessageCode);
		responsePayload.put("RESPONSE_BODY", HttpMessage);
		
		return responsePayload;
	}
	

	


}

