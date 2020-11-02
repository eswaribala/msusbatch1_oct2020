package com.virtusa.banking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.banking.models.Customer;
import com.virtusa.banking.services.CustomerService;

@RestController
public class CustomerController {
    @Autowired
	private CustomerService customerService;
    
    //postmapping
    @PostMapping("/customers")
    @CrossOrigin("*")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer)
    {
    	if(customerService.addCustomer(customer)!=null)
    	{
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.addCustomer(customer));
    	}
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Not Added");
    	
    }
    
    @GetMapping("/customers")
    @CrossOrigin("*")
    public List<Customer> findAllCustomers()
    {
    	
    		return customerService.getAllCustomers();
    	
    }
    
    
}
