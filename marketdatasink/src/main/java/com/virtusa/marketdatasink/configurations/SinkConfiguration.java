package com.virtusa.marketdatasink.configurations;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.virtusa.marketdatasink.models.MarketData;
import com.virtusa.marketdatasink.services.MarketDataService;



@Configuration
@EnableBinding(Sink.class)
public class SinkConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(SinkConfiguration.class);
    @Autowired
	private MarketDataService marketDataService;
	@StreamListener(Sink.INPUT)
	public void saveBeneficiary(String marketData) {
		//marketData.values().stream().forEach(System.out::println);
		//logger.info("MarketData===>"+marketData);
		System.out.println("MarketData===>"+marketData);
		Gson gson = new Gson();
	    Type type = new TypeToken<List<MarketData>>(){}.getType();
	    List<MarketData> marketDataList = gson.fromJson(marketData, type);
	    for (MarketData marketObj : marketDataList){
	        System.out.println(marketObj.getDate());
	    }
		this.marketDataService.addMarketData(marketDataList); 
        
	}

	

}
