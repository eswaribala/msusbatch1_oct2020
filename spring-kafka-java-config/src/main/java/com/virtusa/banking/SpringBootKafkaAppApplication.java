package com.virtusa.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class SpringBootKafkaAppApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(SpringBootKafkaAppApplication.class, args);
	}
}
