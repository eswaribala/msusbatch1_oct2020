package com.virtusa.banking.virtusaconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class VirtusaconfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtusaconfigserverApplication.class, args);
	}

}
