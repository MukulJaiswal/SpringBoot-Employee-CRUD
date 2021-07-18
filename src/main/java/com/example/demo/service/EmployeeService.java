package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee emp);

	List<Employee> findAllEmployee();

	Employee findEmployeeById(int empid);

	Employee updateEmployee(Employee emp);

	void deleteEmployee(int empid);
	
	String consumeApi();

}
