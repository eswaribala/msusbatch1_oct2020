package com.virtusa.banking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.banking.models.Customer;
import com.virtusa.banking.repositories.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepo;
	
	//insert
	
	public Customer addCustomer(Customer customer)
	{
		 //issue
		return customerRepo.save(customer);		
		
	}
	
	
	//select
	public List<Customer> getAllCustomers()
	{
		 
		return  customerRepo.findAll();
		
	}
	
	//select customer by id
	
	public Customer getCustomerById(long customerId)
	{
		 
		return  customerRepo.findById(customerId).orElse(null);
		
	}
		
	//update 
	
	
	
	//delete
	
	
	

}
