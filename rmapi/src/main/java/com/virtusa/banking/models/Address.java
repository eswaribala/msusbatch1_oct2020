package com.virtusa.banking.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Table(name="Address")
@Data
@XmlRootElement
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Address_Id")
	private long addressId;
	@Column(name = "Door_No",nullable = false,length = 5)
	@NotBlank(message = "Door No is mandatory")
	private String doorNo;
	@Column(name = "Street_Name",nullable = false,length = 100)
	private String streetName;
	@Column(name = "City",nullable = false,length = 150)
	private String city;
	@Column(name = "State",nullable = false,length = 50)
	private String state;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "Employee_Id"), name = "Employee_Id")
	@JsonIgnore
	private Employee employee;
		
}
