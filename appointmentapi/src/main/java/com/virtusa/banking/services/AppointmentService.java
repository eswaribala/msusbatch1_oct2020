package com.virtusa.banking.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.virtusa.banking.exceptions.CustomException;
import com.virtusa.banking.exceptions.CustomExceptionHandler;
import com.virtusa.banking.models.Appointment;

import com.virtusa.banking.repository.AppointmentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepo;
	@Value("${customer_url}")
    private String customerUrl; 
	@Value("${employee_url}")
    private String employeeUrl;
	@Autowired
	private RestTemplate restTemplate;
	private Appointment appointmentObj=null;
	public Appointment addAppointment(Appointment appointment)
	{
		//validate the customer id
		HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);
	       headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	    HttpEntity request = new HttpEntity<>("body",headers);

	    //customer validation
		ResponseEntity<String> customerResponse,employeeResponse;
		JsonObject jsonObject;
		Set<Map.Entry<String, JsonElement>> entries;
		try
		{
			customerResponse=restTemplate.exchange(customerUrl+"/customers/"
		  		+appointment.getCustomerId(),HttpMethod.GET,request,String.class);		
		log.info(customerResponse.getBody());
		//check customer id
		jsonObject = JsonParser.parseString(customerResponse.getBody()).getAsJsonObject();	     
	    entries = jsonObject.entrySet();
	      for(Map.Entry<String, JsonElement> entry: entries) {
	         System.out.println(entry.getKey()+","+entry.getValue());
	      }
	      employeeResponse =restTemplate.exchange(employeeUrl+"/employees/"
			  		+appointment.getRmId(),HttpMethod.GET,request,String.class);
			
			log.info(employeeResponse.getBody());
			
			jsonObject = JsonParser.parseString(employeeResponse.getBody()).getAsJsonObject();	     
		    entries = jsonObject.entrySet();
		      for(Map.Entry<String, JsonElement> entry: entries) {
		         System.out.println(entry.getKey()+","+entry.getValue());
		      }
			
		      appointmentObj=appointmentRepo.save(appointment);    
		      
		}
		catch(CustomException ex)
		{
			jsonObject = JsonParser.parseString(ex.getMessage()).getAsJsonObject();	     
		    entries = jsonObject.entrySet();
		      for(Map.Entry<String, JsonElement> entry: entries) {
		         System.out.println(entry.getKey()+","+entry.getValue());
		      }
		}
       
		
		
		
		return appointmentObj;

		
	}
	
	//select
		public List<Appointment> getAllAppointments()
		{
			 
			return  appointmentRepo.findAll();
			
		}
	

}
