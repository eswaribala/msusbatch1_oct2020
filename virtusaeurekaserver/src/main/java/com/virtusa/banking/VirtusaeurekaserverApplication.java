package com.virtusa.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class VirtusaeurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtusaeurekaserverApplication.class, args);
	}

}
