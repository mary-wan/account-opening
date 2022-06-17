package com.kcbgroup.main.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcbgroup.main.model.SmsRequest;
import com.kcbgroup.main.service.SendSmsService;
import com.kcbgroup.main.twilio.TwilioConfiguration;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import lombok.extern.slf4j.Slf4j;

@Service("twilio")
@Slf4j
public class SendSmsServiceImplementation implements SendSmsService{
	
	@Autowired 
	TwilioConfiguration twilioConfiguration;

	@Override
	public void sendSms(SmsRequest smsRequest) {
			
			PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
			PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
			String message = smsRequest.getMessage();	
			MessageCreator creator =Message.creator(to, from, message);
			
			creator.create();		
	}

}
