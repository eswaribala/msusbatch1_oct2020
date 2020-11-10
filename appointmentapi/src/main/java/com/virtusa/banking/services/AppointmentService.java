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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
@Slf4j
public class AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepo;
	@Autowired
	private HystrixHandler hystrixHandler;
	@Value("${employee_url}")
    private String employeeUrl;
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
	
	public ResponseEntity<?> validateRM(long rmId) {

		// validate the customer id

		// customer validation
		ResponseEntity<String> employeeResponse;
		JsonObject jsonObject;
		Set<Map.Entry<String, JsonElement>> entries;
		try {

			employeeResponse = restTemplate.exchange(employeeUrl + "/employees/" + rmId, HttpMethod.GET,
					getHttpEntity(), String.class);

			// log.info(employeeResponse.getBody());

			jsonObject = JsonParser.parseString(employeeResponse.getBody()).getAsJsonObject();

		} catch (CustomException ex) {
			jsonObject = JsonParser.parseString(ex.getMessage()).getAsJsonObject();
			entries = jsonObject.entrySet();
			for (Map.Entry<String, JsonElement> entry : entries) {
				System.out.println(entry.getKey() + "," + entry.getValue());
			}
		}

		return ResponseEntity.ok(jsonObject);

	}
	 
	
	
	
	public Appointment addAppointment(Appointment appointment)
	{
		boolean status=false;
		ResponseEntity<?> customerResponse = hystrixHandler.validateCustomer(appointment.getCustomerId());
		log.info(customerResponse.getBody().toString());
		JsonObject jsonObject = JsonParser.parseString(customerResponse.getBody().toString()).getAsJsonObject();
		Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
		for (Map.Entry<String, JsonElement> entry : entries) {
			System.out.println(entry.getKey() + "," + entry.getValue());
			if (entry.getKey().equals("customerId"))
			{
					status=true;
			        break; 		
			}
		}
		log.info("Customer Status"+status);
		if(status)
		{
		
			status=false;
		ResponseEntity<?> rmResponse = validateRM(appointment.getRmId());
		log.info(rmResponse.getBody().toString());
		jsonObject = JsonParser.parseString(rmResponse.getBody().toString()).getAsJsonObject();
		entries = jsonObject.entrySet();
		for (Map.Entry<String, JsonElement> entry : entries) {
			System.out.println(entry.getKey() + "," + entry.getValue());
			if (entry.getKey().equals("employeeId"))
			{
					status=true;
			        break; 		
			}
		}
		}
		 Appointment appointmentObj=null;
		 log.info("RM Status"+status);
		if(status)
		appointmentObj=appointmentRepo.save(appointment);
		
		
		return appointmentObj;

		
	}
	
	//select
		public List<Appointment> getAllAppointments()
		{
			 
			return  appointmentRepo.findAll();
			
		}
	

}
