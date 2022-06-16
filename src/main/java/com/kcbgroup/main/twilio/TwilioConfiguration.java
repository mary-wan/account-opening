package com.kcbgroup.main.twilio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfiguration {
	
	private String accountSid;
	private String authtoken;
	private String trialNumber;
	
}
