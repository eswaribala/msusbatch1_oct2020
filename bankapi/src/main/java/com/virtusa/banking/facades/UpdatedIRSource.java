package com.virtusa.banking.facades;

import org.springframework.messaging.MessageChannel;
import org.springframework.cloud.stream.annotation.Output;

public interface UpdatedIRSource {
	
	@Output("updatedIRChannel")
	MessageChannel updatedIRQueue();

}
