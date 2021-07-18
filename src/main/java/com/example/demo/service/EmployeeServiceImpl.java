package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	private final RestTemplate restTemplate;

	@Autowired
	public EmployeeServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public Employee addEmployee(Employee emp) {
		return employeeRepository.save(emp);
	}

	@Override
	public List<Employee> findAllEmployee() {
		List<Employee> allEmp = employeeRepository.findAll();
		return allEmp;
	}

	@Override
	public Employee findEmployeeById(int empid) {
		Optional<Employee> emp = employeeRepository.findById(empid);// Returns whether an entity with the given id
																	// exists.

		if (emp.isPresent()) {
			return emp.get();
		} else {
			throw new ResourceNotFoundException("Employee not found with ID : " + empid);
		}
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		Optional<Employee> getEmp = employeeRepository.findById(emp.getEmpid());

		if (getEmp.isPresent()) {
			Employee updateEmployee = getEmp.get();
			updateEmployee.setEmpid(emp.getEmpid());
			updateEmployee.setName(emp.getName());
			updateEmployee.setSalary(emp.getSalary());
			employeeRepository.save(updateEmployee);

			return updateEmployee;

		} else {
			throw new ResourceNotFoundException("Employee not found with  Employee ID : " + emp.getEmpid());
		}
	}

	@Override
	public void deleteEmployee(int empid) {
		Optional<Employee> employee = employeeRepository.findById(empid);

		if (employee.isPresent()) {
			employeeRepository.delete(employee.get());
		} else {
			throw new ResourceNotFoundException("Employee not found with  Employee ID : " + empid);
		}
	}

	@Override
	public String consumeApi() {
		return restTemplate.getForObject("https://s3-ap-southeast-1.amazonaws.com/he-public-data/books8f8fe52.json",
				String.class);
	}
}











