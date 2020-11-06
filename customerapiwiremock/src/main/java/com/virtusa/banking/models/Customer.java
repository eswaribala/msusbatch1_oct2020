package com.virtusa.banking.models;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
//value object
@Data
public class Customer {
	
	
	private long customerId;
	
	private FullName name;
	
	private Gender gender;
	
	private LocalDate dob;
	
	private String email;
		
	private long mobileNo;

	
	private  List<Address> addressList;
	 

}
