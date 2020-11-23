package com.virtusa.banking.facades;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.virtusa.banking.models.UpdatedIRMessage;

import lombok.extern.slf4j.Slf4j;

@EnableBinding(UpdatedIRSink.class)
@Slf4j
public class UpdatedIRMessageListener {
	
	
  
    @StreamListener(target = UpdatedIRSink.input)
    public void listenForUpdatedIRMessage(UpdatedIRMessage updatedIRMessage) throws Exception
    {
    	
    	if(updatedIRMessage.getNewInterestRate()<=0)
    		throw new Exception("Interest Rate cannot be zero or less than that");
      
    	log.info(updatedIRMessage.toString());
    }


}
