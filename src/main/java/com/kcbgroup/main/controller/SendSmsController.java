package com.kcbgroup.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kcbgroup.main.model.SmsRequest;
import com.kcbgroup.main.service.implementation.TwilioSendSmsService;

@RestController
@RequestMapping("/api")
public class SendSmsController {
	
	@Autowired
	TwilioSendSmsService twilioSendSmsService;
	
	@PostMapping("/sms")
	public void sendsms(@Valid @RequestBody SmsRequest smsRequest) {
		twilioSendSmsService.sendSms(smsRequest);
	}

}
