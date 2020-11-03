package com.virtusa.banking.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
@Embeddable
@Data
public class FullName {
	
	@Column(name = "First_Name",nullable = false,length = 50)
	private String firstName;
	@Column(name = "Last_Name",nullable = false,length = 50)
	private String lastName;
	@Column(name = "Middle_Name",nullable = true,length = 50)
	private String middleName;

}
