package com.babelgroup.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.babelgroup.employees.dto.EmployeeDto;
import com.babelgroup.employees.dto.ResponseDto;
import com.babelgroup.employees.entities.EmployeeEntity;
import com.babelgroup.employees.service.EmployeeSevice;

@RestController
public class EmployeesController {
	@Autowired
	private EmployeeSevice employeeSevice;
	
	
	@GetMapping(value = "/getEmployees")
	public ResponseDto<EmployeeEntity> getEmployees() {
		return employeeSevice.getEmployees();
	}
	

	
	@PostMapping(value = "/employee")
	public ResponseDto insertEmployees(
			@RequestBody EmployeeDto employee) {
		return employeeSevice.insertEmployees(employee);
		
	}
	
	@DeleteMapping(value = "/employee")
	public ResponseDto deleteEmployees(@RequestParam 
			Long idEmployee) {
		return employeeSevice.deleteEmployees()(idPerson);	
	}
	
	@PutMapping(value = "/employee")
	public ResponseDto updateEmployee(@RequestBody EmployeeDto employee) {
		return employeeSevice.updateEmployee(employee);
	}
	
}
