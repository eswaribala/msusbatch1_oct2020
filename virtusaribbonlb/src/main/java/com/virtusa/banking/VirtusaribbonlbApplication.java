package com.virtusa.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.virtusa.banking.configurations.RibbonConfiguration;

@SpringBootApplication
@RibbonClient(name = "VIRTUSA-RIBBON",configuration = RibbonConfiguration.class)
public class VirtusaribbonlbApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtusaribbonlbApplication.class, args);
	}

}
