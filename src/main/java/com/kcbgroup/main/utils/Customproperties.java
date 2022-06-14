package com.kcbgroup.main.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "t24")
public class CustomProperties {

	private String clientUsername;
	private String clientPassword;
	private String serviceName;
	private String baseUrl;
	private String baseResource;
	private int httpConnectionRequestTimeout;
	private int httpConnectionTimeout;
	private int httpConnectionReadTimeout;

}
