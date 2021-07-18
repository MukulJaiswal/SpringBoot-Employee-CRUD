package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
//	https://www.youtube.com/watch?v=QPiAhiUwb4I&ab_channel=ProgRank
	@GetMapping("/")
	public String getBookDetails() {
		String res = employeeService.consumeApi();
		return res;
	}

	@GetMapping("employees")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> emps = employeeService.findAllEmployee();
		return ResponseEntity.ok().body(emps);
	}

	@GetMapping("employees/{empid}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int empid) {
		Employee employee = employeeService.findEmployeeById(empid);
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("employees")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee newEmp) {
		Employee employee = employeeService.addEmployee(newEmp);
		return ResponseEntity.created(null).body(employee);
	}
	
	@PutMapping("employees/{empid}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int empid, @RequestBody Employee newEmp) {
		newEmp.setEmpid(empid);
		Employee employee = employeeService.updateEmployee(newEmp);
		return ResponseEntity.ok().body(employee);
	}
	
	@DeleteMapping("employees/{empid}")
	public HttpStatus deleteEmployee(@PathVariable int empid) {
		this.employeeService.deleteEmployee(empid);
		return HttpStatus.OK;
	}
}








