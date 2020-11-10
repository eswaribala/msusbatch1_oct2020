package com.virtusa.banking.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class HystrixHandler {
  
    @Value("${serviceUrl}")
    private String serviceUrl; 
	@Value("${fallbackUrl}")
    private String fallbackUrl;

	@Autowired
	private RestTemplate restTemplate;
	
	
	public HttpEntity getHttpEntity()
	{
		HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);
	       headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	    HttpEntity request = new HttpEntity<>("body",headers);
	    return request;
	}
	
	/*
	@HystrixCommand(commandKey = "GetCustomerCommand", groupKey = "CustomerGroupKey", threadPoolKey = "Test",
    		fallbackMethod = "fallbackRequestHandler",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "110"),
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "false"),
                    @HystrixProperty(name="execution.isolation.strategy", value="ThreadPool")
            }
		
            )
            */
	@HystrixCommand(commandKey = "PostCustomerCommand", groupKey = "CustomerGroupKey", threadPoolKey = "Test",
    		fallbackMethod = "fallbackRequestHandler",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "110"),
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "false")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            })
	public ResponseEntity<String> validateCustomer(long customerId)
	{
		
			  
	return restTemplate.exchange(serviceUrl+"/customers/"
				  		+customerId,HttpMethod.GET,getHttpEntity(),String.class);		
				   
		
		
	}
	
	 public ResponseEntity<String> fallbackRequestHandler(long customerId)
		{
			
					return restTemplate.exchange(fallbackUrl+"/customers/"
					  		+customerId,HttpMethod.GET,getHttpEntity(),String.class);		
					   
			
		}
	
	
	
	
}
