package com.virtusa.banking.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.virtusa.banking.exceptions.MobileNoException;
import com.virtusa.banking.exceptions.RecordNotFoundException;
import com.virtusa.banking.models.Employee;
import com.virtusa.banking.services.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RefreshScope
public class EmployeeController {
    @Autowired
	private EmployeeService employeeService;
   
    //postmapping
    @PostMapping("/employees")
    @CrossOrigin("*")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee)
    {
    	
    	Employee employeeObj=employeeService.addEmployee(employee);
    	
    	
    	return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeObj);
    	
    	
    }
    
    @RequestMapping(value = "/employees", 
    		  produces = { "application/json", "application/xml" }, 
    		  method = RequestMethod.GET)

    @CrossOrigin("*")
    public List<Employee> findAllEmployees()
    {
    	
    		return employeeService.getAllEmployees();
    	
    }
    
    @GetMapping("/employees/{employeeId}")
    @CrossOrigin("*")
    public ResponseEntity<?> getEmployeeById(@PathVariable("employeeId") long employeeId)
    {
    	Employee employee=employeeService.getEmployeeById(employeeId);
    	
    	  if(employee == null) {
    	         throw new RecordNotFoundException("Invalid employee id : " + employeeId);
    	    }
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(employee);
    	
    }
    
    
}
