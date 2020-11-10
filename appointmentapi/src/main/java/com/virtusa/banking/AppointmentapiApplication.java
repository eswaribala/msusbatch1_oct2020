package com.virtusa.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//import com.virtusa.banking.exceptions.CustomResponseErrorHandler;

@SpringBootApplication
@EnableHystrixDashboard
@EnableHystrix
@EnableCircuitBreaker
public class AppointmentapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentapiApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate()
	{
		//return new RestTemplateBuilder().errorHandler(new CustomResponseErrorHandler()).build();
		return new RestTemplate();
	}
}
