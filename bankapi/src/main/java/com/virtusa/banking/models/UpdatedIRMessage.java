package com.virtusa.banking.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class UpdatedIRMessage {

	private float oldInterestRate;
	private float newInterestRate;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate effectiveFrom;
	private int changedTenure;
	
}
