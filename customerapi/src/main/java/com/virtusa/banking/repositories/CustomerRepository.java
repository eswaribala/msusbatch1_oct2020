package com.virtusa.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.banking.models.Customer;

public interface CustomerRepository  extends JpaRepository<Customer, Long>{

}
