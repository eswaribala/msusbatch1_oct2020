package com.virtusa.banking.models;

import lombok.Data;

@Data
public class Address {
	
	private long addressId;
	
	private String doorNo;

	private String streetName;
	
	private String city;

	private String state;
	
	private Customer customer;
		
}
