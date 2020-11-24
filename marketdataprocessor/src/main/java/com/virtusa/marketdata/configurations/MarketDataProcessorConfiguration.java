package com.virtusa.marketdata.configurations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;

import com.google.gson.Gson;
import com.virtusa.marketdataprocessor.models.MarketData;
import com.vitusa.marketdata.util.MarketDataParser;


@EnableBinding(Processor.class)
public class MarketDataProcessorConfiguration {
	
	//reads from rmq message and writes to rmq after conversion
	@ServiceActivator(inputChannel = Processor.INPUT,outputChannel = Processor.OUTPUT)
	public String transformMessage(List<String> futureData)
	{
			//String response=MarketDataParser.parseString(futureData);
		//System.out.println("Processor--->"+futureData);
		futureData.stream().forEach(System.out::println);
		MarketData marketData=new MarketData();
		List<MarketData> marketDataList=new ArrayList<MarketData>();
		try
		{
		for(int i=1;i<futureData.size()-2;i+=2)
		   {
			 
			  marketData=new MarketData();
			  try
			  {
			  marketData.setDate(futureData.get(i));
			   marketData.setPrice(futureData.get(i+1));
			   
			   marketDataList.add(marketData);
			  }
			  catch(NumberFormatException number)
			  {
				  
			  }
		    }
		}
		catch(NullPointerException exception)
		{
			
		}
		Gson gsonObj = new Gson();  
		String jsonStr = gsonObj.toJson(marketDataList);
		System.out.println(jsonStr);
		return jsonStr;
	}

}
