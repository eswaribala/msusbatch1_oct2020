package com.virtusa.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.virtusa.banking.exceptions.CustomResponseErrorHandler;

@SpringBootApplication
public class AppointmentapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentapiApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplateBuilder().errorHandler(new CustomResponseErrorHandler()).build();
	}
}
