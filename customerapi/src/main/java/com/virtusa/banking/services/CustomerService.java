package com.virtusa.banking.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import com.virtusa.banking.exceptions.MobileNoException;
import com.virtusa.banking.models.Customer;
import com.virtusa.banking.models.Gender;
import com.virtusa.banking.repositories.CustomerRepository;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;



@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private EntityManager entityManager;
	
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
		
		
		public Page<Customer> query(String condition, Pageable pageable){
	        // 1.Create the JPA Visitor
	        RSQLVisitor<CriteriaQuery<Customer>, EntityManager> visitor = 
	        		new JpaCriteriaQueryVisitor<Customer>();
	        // 2.Parse a RSQL into a Node
	        Node rootNode = new RSQLParser().parse(condition);
	        // 3.Create CriteriaQuery
	        CriteriaQuery<Customer> criteriaQuery = rootNode.accept(visitor, entityManager);
	        List<Customer> total = entityManager.createQuery(criteriaQuery).getResultList();
	        List<Customer> resultList = entityManager.createQuery(criteriaQuery)
	                .setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
	 
	        return new PageImpl<>(resultList,pageable, total.size());
	    }


}
