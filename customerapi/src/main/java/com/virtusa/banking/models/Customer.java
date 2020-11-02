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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name="Customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Customer_Id")
	private long customerId;
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
	private String email;
	@Column(name="Mobile_No")
	@ApiModelProperty(example = "111111111")	
	private long mobileNo;

	
	  @OneToMany(mappedBy ="customer",
	  cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval = true)
	  
	  @JsonProperty("addresses")
	  
	  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY) private
	  List<Address> addressList;
	 
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public FullName getName() {
		return name;
	}
	public void setName(FullName name) {
		this.name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	  public List<Address> getAddressList() { return addressList; } public void
	  setAddressList(List<Address> addressList) { this.addressList = addressList; }
	 
	
	
	

}
