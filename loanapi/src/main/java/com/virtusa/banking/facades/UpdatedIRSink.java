package com.virtusa.banking.facades;

import org.springframework.messaging.MessageChannel;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface UpdatedIRSink {
	
	String input="updatedIRChannel";
	@Input(input)
	MessageChannel updatedIRQueue();

}
