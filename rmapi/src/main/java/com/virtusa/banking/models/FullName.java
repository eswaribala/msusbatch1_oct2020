package com.virtusa.banking.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
@Embeddable
@Data
public class FullName {
	
	@Column(name = "First_Name",nullable = false,length = 50)
	@NotEmpty(message = "first name must not be empty")
	@Size(max = 5,message ="Minimum 5 chars")
    @NotBlank(message = "First Name is mandatory")
	private String firstName;
	@NotEmpty(message = "last name must not be empty")
	@Size(max = 5,message ="Minimum 5 chars")
    @NotBlank(message = "Last Name is mandatory")
	@Column(name = "Last_Name",nullable = false,length = 50)
	private String lastName;
	@Column(name = "Middle_Name",nullable = true,length = 50)
	private String middleName;

}
