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
import com.virtusa.banking.models.Employee;
import com.virtusa.banking.models.Gender;

import com.virtusa.banking.repositories.EmployeeRepository;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;



@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private EntityManager entityManager;
	
	//insert
	
	public Employee addEmployee(Employee employee)
	{
		
		if( employee.getAddressList().size() > 0 )
        {
			employee.getAddressList().stream().forEach( address -> {
                address.setEmployee(employee);
            } );
        }
    	return this.employeeRepo.save(employee);
		
	}
	
	
	//select
	public List<Employee> getAllEmployees()
	{
		 
		return  employeeRepo.findAll();
		
	}
	
	//select customer by id
	
	public Employee getEmployeeById(long employeeId)
	{
		 
		return  employeeRepo.findById(employeeId).orElse(null);
		
	}
		
	

}
