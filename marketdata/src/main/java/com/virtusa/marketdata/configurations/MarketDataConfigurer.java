package com.virtusa.marketdata.configurations;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;
import com.virtusa.marketdata.util.MarketDataGenerator;


@Configuration
@EnableBinding(Source.class)
public class MarketDataConfigurer {
	@Autowired
	private MarketDataGenerator marketDataGenerator;
	private List<Object> response;
	private static final Logger logger = LoggerFactory.getLogger(MarketDataConfigurer.class);
	
	@PostConstruct
	public void init()
	{
		//send market data
				//lambda expression
				//functional interface (java 8)
		//response=marketDataGenerator.getLIBOR();
		
		response=marketDataGenerator.getLIBOR().values().stream().limit(50).collect(Collectors.toList());
		
		//marketDataGenerator.getLIBOR().keySet().stream().forEach(System.out::println);
		//marketDataGenerator.getLIBOR().values().stream().forEach(System.out::println);
		response.stream().forEach(System.out::println);
	}
	
	
	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "100000", 
	maxMessagesPerPoll = "1"))
	public MessageSource<List<Object>>  sendMessage()
	{	
				
		return ()->MessageBuilder.withPayload(response).build();
	}
	
	
	
	
	
}
