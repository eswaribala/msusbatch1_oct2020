package com.virtusa.banking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.banking.facades.UpdatedIRSource;
import com.virtusa.banking.models.UpdatedIRMessage;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@EnableBinding(UpdatedIRSource.class)

public class UpdateIRController {

	@Autowired
	private UpdatedIRSource updatedIRSource;
	
	@PostMapping("/messages")
	public String updatedInterestRate(@RequestBody UpdatedIRMessage updatedIRMessage)
	{
		updatedIRSource.updatedIRQueue().send(MessageBuilder.withPayload(updatedIRMessage).build());
		log.info("Message Published");
		
		return "Notification Sent";
	}
	
}
