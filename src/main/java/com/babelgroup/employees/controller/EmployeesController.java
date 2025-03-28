package com.babelgroup.employees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.babelgroup.employees.dto.EmployeeDto;
import com.babelgroup.employees.dto.ResponseDto;
import com.babelgroup.employees.entities.EmployeeEntity;
import com.babelgroup.employees.service.EmployeeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeesController {

	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping
	public ResponseDto<List<EmployeeEntity>> getEmployees() {
		return employeeService.getEmployees();
	}
	
	@PostMapping
    public ResponseDto<?> insertEmployees(@RequestBody @Valid List<EmployeeDto> employees) {
        return employeeService.insertEmployees(employees);
    }
	
	@DeleteMapping
	public ResponseDto<?> deleteEmployees(@RequestParam @Min(1)
			Long idEmployee) {
		return employeeService.deleteEmployees(idEmployee);	
	}
	
	@PutMapping
	public ResponseDto<?> updateEmployee(@RequestBody @Valid EmployeeDto employee) {
		return employeeService.updateEmployee(employee);
	}
	
}