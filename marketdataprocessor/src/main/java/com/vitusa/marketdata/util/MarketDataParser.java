package com.vitusa.marketdata.util;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Component
public class MarketDataParser {

	
	public static String parseString(String response)
	{
		JSONParser parser = new JSONParser(); 
		String data="";
		String flattenedJson="";
	  	try {
	  		 
			// Put above JSON content to crunchify.txt file and change path location
			Object obj = parser.parse(response);
			JSONObject jsonObject = (JSONObject) obj;
 
			// JsonFlattener: A Java utility used to FLATTEN nested JSON objects
			flattenedJson = JsonFlattener.flatten(jsonObject.toString());
			System.out.println("\n=====Simple Flatten===== \n" + flattenedJson);
 
			Map<String, Object> flattenedJsonMap = JsonFlattener.flattenAsMap(jsonObject.toString());
		 	//data=(String) flattenedJsonMap.get("dataset");   
			flattenedJsonMap.values().stream().forEach(System.out::println);
			//log("\n=====Flatten As Map=====\n" + flattenedJson);
			// We are using Java8 forEach loop. More info: https://crunchify.com/?p=8047
			//flattenedJsonMap.forEach((k, v) -> log(k + " : " + v));
 
			// Unflatten it back to original JSON
			String nestedJson = JsonUnflattener.unflatten(flattenedJson);
			System.out.println("\n=====Unflatten it back to original JSON===== \n" + nestedJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 return flattenedJson;

	}
}
