package com.virtusa.banking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.banking.models.Customer;
import com.virtusa.banking.models.Gender;
import com.virtusa.banking.repositories.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepo;
	
	//insert
	
	public Customer addCustomer(Customer customer)
	{
		if( customer.getAddressList().size() > 0 )
        {
            customer.getAddressList().stream().forEach( address -> {
                address.setCustomer(customer);
            } );
        }
    	return this.customerRepo.save(customer);
		
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
	
	public Customer updateCustomer(Customer customer)
	{
		
		if( customer.getAddressList().size() > 0 )
        {
            customer.getAddressList().stream().forEach( address -> {
                address.setCustomer(customer);
            } );
        }
    	return this.customerRepo.save(customer);
		
	}
	
	//delete
	public boolean deleteCustomer(long customerId)
	{
		boolean status=true;
		this.customerRepo.deleteById(customerId);
		if(getCustomerById(customerId)!=null)
			status=false;
    	  
		return status;
	}
	
	//select customer by gender
	
		public List<Customer> getCustomerByGender(String gender)
		{
			
			//string to Enum
			 
			return  customerRepo.findByGender(Gender.valueOf(gender));
			
		}

}
