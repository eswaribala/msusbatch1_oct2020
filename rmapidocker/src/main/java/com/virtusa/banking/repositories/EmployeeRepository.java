package com.virtusa.banking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.banking.models.Employee;
import com.virtusa.banking.models.Gender;

public interface EmployeeRepository  extends JpaRepository<Employee, Long>{

	//JPA refers class and fields not table
			@Query("select employee from Employee employee where employee.gender=:gender")
			public List<Employee> findByGender(@Param("gender") Gender gender);
}
