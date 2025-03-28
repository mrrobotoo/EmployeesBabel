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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeesController {

	@Autowired
	private EmployeeService employeeService;
	
	@Operation(summary = "Get all employees")
	@GetMapping
	public ResponseDto<List<EmployeeEntity>> getEmployees() {
		return employeeService.getEmployees();
	}
	
	@Operation(summary = "Insert multiple employees")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Employees inserted successfully"),
        @ApiResponse(responseCode = "403", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "General error")

    })
	@PostMapping
    public ResponseDto<?> insertEmployees(@RequestBody @Valid List<EmployeeDto> employees) {
        return employeeService.insertEmployees(employees);
    }
	
	@Operation(summary = "Delete an employee by ID")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Employee delete successfully"),
	        @ApiResponse(responseCode = "500", description = "General error")

	    })
	@DeleteMapping
	public ResponseDto<?> deleteEmployees(@RequestParam @Min(1)
			Long idEmployee) {
		return employeeService.deleteEmployees(idEmployee);	
	}
	
	@Operation(summary = "Update employee details")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Employee update successfully"),
	        @ApiResponse(responseCode = "404", description = "Employee not exist"),
	        @ApiResponse(responseCode = "500", description = "General error")

	    })
	@PutMapping
	public ResponseDto<?> updateEmployee(@RequestBody @Valid EmployeeDto employee) {
		return employeeService.updateEmployee(employee);
	}
	
}