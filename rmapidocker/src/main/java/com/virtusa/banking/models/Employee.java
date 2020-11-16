package com.virtusa.banking.models;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="Employee")
@Data
@XmlRootElement
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Employee_Id")
	private long employeeId;
	@Embedded
	private FullName name;
	@Enumerated(EnumType.STRING)
	@Column(name="Gender")
	private Gender gender;
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name="DOB")
	private LocalDate dob;
	@Column(name="Email",nullable = false,length = 150)
	@ApiModelProperty(example = "sample@gmail.com")	
	@Email
	@NotBlank(message = "Email is mandatory")
	private String email;
	@Column(name="Mobile_No")
	@ApiModelProperty(example = "111111111")		
	private long mobileNo;
	@Column(name="Role",nullable = false,length = 150)
    private String role;
    
	
	  @OneToMany(mappedBy ="employee",
	  cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval = true)
	  
	  @JsonProperty("addresses")
	  
	  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY) private
	  List<Address> addressList;
	 

}
