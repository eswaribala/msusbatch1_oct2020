package com.virtusa.banking.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Table(name = "Appointmnet")
@Data
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Appointment_Id")
	private long appointmentId;
	@Enumerated(EnumType.STRING)
	@Column(name="Appointment_Type")
	private AppointmentType appointmentType;	
	@Column(name="Appointment_Time")
	private LocalTime  time;
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name="Appointment_Date")
	private LocalDate date;
	@Column(name="RM_Id")
	private long rmId;
	@Column(name="Customer_Id")
	private long customerId;
	@Enumerated(EnumType.STRING)
	@Column(name="Appointment_Mode")
	private CommunicationMode communicationMode;
	
	
}
