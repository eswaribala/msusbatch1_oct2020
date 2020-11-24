package com.virtusa.marketdata.util;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Service
public class MarketDataGenerator {
     @Autowired
	 private RestTemplate restTemplate;
     
    //asynchronous concurrent data --> non blocking call 
    //alternative reactive java mono and flux --- non blocking call
    //reactor framework  
	//@Async
    public Map<String, Object> getLIBOR() {
        String url = "https://www.quandl.com/api/v3/datasets/FRED/USDONTD156N.json?api_key=FZzZ6q4zZFFQVaELX-Zs";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,null,String.class);
           
        return parseString(response.getBody());
    }
    public static Map<String, Object> parseString(String response)
	{
		JSONParser parser = new JSONParser(); 
		String data="";
		String flattenedJson="";
		Map<String, Object> flattenedJsonMap =null;
	  	try {
	  		 
			// Put above JSON content to crunchify.txt file and change path location
			Object obj = parser.parse(response);
			JSONObject jsonObject = (JSONObject) obj;
 
			// JsonFlattener: A Java utility used to FLATTEN nested JSON objects
			flattenedJson = JsonFlattener.flatten(jsonObject.toString());
			//System.out.println("\n=====Simple Flatten===== \n" + flattenedJson);
 
			flattenedJsonMap = JsonFlattener.flattenAsMap(jsonObject.toString());
		 	//data=(String) flattenedJsonMap.get("dataset");   
			//flattenedJsonMap.values().stream().forEach(System.out::println);
			//log("\n=====Flatten As Map=====\n" + flattenedJson);
			// We are using Java8 forEach loop. More info: https://crunchify.com/?p=8047
			//flattenedJsonMap.forEach((k, v) -> log(k + " : " + v));
 
			// Unflatten it back to original JSON
			String nestedJson = JsonUnflattener.unflatten(flattenedJson);
			//System.out.println("\n=====Unflatten it back to original JSON===== \n" + nestedJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 return flattenedJsonMap;

	}

}
