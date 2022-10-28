package com.employee.management.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.management.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByFirstNameContainsAndLastNameContainsAndEmailContainsAllIgnoreCase(String FirstName,String LastName, String eMail);

}
