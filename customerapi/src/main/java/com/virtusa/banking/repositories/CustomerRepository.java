package com.virtusa.banking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.banking.models.Customer;
import com.virtusa.banking.models.Gender;

public interface CustomerRepository  extends JpaRepository<Customer, Long>{

	//JPA refers class and fields not table
			@Query("select customer from Customer customer where customer.gender=:gender")
			public List<Customer> findByGender(@Param("gender") Gender gender);
}
