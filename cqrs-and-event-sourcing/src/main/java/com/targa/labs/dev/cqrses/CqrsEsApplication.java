package com.targa.labs.dev.cqrses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//http://localhost:5062/swagger-ui.html
public class CqrsEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CqrsEsApplication.class, args);
    }

}
