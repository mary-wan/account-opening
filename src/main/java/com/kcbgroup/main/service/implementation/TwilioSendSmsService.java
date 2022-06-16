package com.kcbgroup.main.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kcbgroup.main.model.SmsRequest;
import com.kcbgroup.main.service.SendSmsService;

@Service
public class TwilioSendSmsService {
	
	@Autowired 
	SendSmsService sendSmsService;
	
	public void sendSms(@Qualifier("twilio") SmsRequest smsRequest) {
		sendSmsService.sendSms(smsRequest);
	}

}
