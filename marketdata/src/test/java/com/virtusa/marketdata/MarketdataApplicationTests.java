package com.virtusa.marketdata;
/**
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import com.virtusa.marketdata.util.MarketDataGenerator;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@SpringBootTest
class MarketdataApplicationTests {

	private RestTemplate restTemplate=new RestTemplate();
	@Test
	void contextLoads() {
		Map<String,Object> data= new MarketDataGenerator().getLIBOR();
		// data.values().stream().forEach(System.out::println);
		//List values= data.values().stream().limit(10).collect(Collectors.toList());
		//System.out.println("Size:"+values.size());
		
	}
	
	
}
**/