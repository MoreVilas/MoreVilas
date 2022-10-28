package com.employee.management.demo.service;

import java.util.List;

import com.employee.management.demo.entity.Employee;


public interface  EmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	
	public List<Employee> searchBy(String first_name ,String last_name, String email);
}
