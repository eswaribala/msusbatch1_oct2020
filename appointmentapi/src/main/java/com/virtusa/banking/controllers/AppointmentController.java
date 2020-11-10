package com.virtusa.banking.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.banking.models.Appointment;

import com.virtusa.banking.services.AppointmentService;

@RestController
public class AppointmentController {
    @Autowired
	private AppointmentService appointmentService;
    //postmapping
    @PostMapping("/appointments")
    
    @CrossOrigin("*")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Appointment appointment)
    {
    	
    	Appointment appointmentObj=appointmentService.addAppointment(appointment);
    	
    	
    	
    	return ResponseEntity.status(HttpStatus.ACCEPTED).body(appointmentObj);
    	
    	
    }
    
    @RequestMapping(value = "/appointments", 
  		  produces = { "application/json", "application/xml" }, 
  		  method = RequestMethod.GET)

  @CrossOrigin("*")
  public List<Appointment> findAllAppointments()
  {
  	
  		return appointmentService.getAllAppointments();
  	
  }
    
}
