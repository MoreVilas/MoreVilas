package com.employee.management.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.demo.entity.Employee;
import com.employee.management.demo.repository.EmployeeRepository;
import com.employee.management.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> findAll() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		return employeeRepository.findById(theId).get();
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);

	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

	@Override
	public List<Employee> searchBy(String first_name, String last_name, String email) {
		List<Employee> employees = employeeRepository.findByFirstNameContainsAndLastNameContainsAndEmailContainsAllIgnoreCase(first_name, last_name, email);
		return employees;
	}

}
