package com.kcbgroup.main.utils;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfig {
	
	private int CONNECTION_REQUEST_TIMEOUT=60000;
	
	private int CONNECTION_TIMEOUT=60000;
	
	private int CONNECTION_READ_TIMEOUT=60000;
	
	
	@Bean(name = "RestTemplateByPassSSL")
	public RestTemplate restTemplateByPassSSL() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
	    org.apache.http.ssl.TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
	    HostnameVerifier hostnameVerifier = (s, sslSession) -> true;
	    SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
	    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
	    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
	    requestFactory.setConnectTimeout(CONNECTION_TIMEOUT);
	    requestFactory.setReadTimeout(CONNECTION_READ_TIMEOUT);
	    requestFactory.setHttpClient((org.apache.http.client.HttpClient) httpClient);	    
	    return new RestTemplate(requestFactory);
	}
	
	

	@Bean(name="RestTemplateClient")
	public RestTemplate customRestTemplate() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
		httpRequestFactory.setConnectTimeout(CONNECTION_TIMEOUT);
		httpRequestFactory.setReadTimeout(CONNECTION_READ_TIMEOUT);
		return new RestTemplate(httpRequestFactory);
	}	
	

}
