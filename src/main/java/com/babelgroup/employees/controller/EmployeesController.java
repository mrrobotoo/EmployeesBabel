package com.babelgroup.employees.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.babelgroup.employees.dto.EmployeeDto;
import com.babelgroup.employees.dto.ResponseDto;
import com.babelgroup.employees.entities.EmployeeEntity;
import com.babelgroup.employees.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

/**
 * REST Controller for managing employee operations.
 * Provides endpoints for retrieving, inserting, updating, and deleting employees.
 */
@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Retrieves a list of all employees.
     *
     * @return ResponseDto containing a list of employees.
     */
    @Operation(summary = "Get all employees")
    @GetMapping
    public ResponseDto<List<EmployeeEntity>> getEmployees() {
        return employeeService.getEmployees();
    }

    /**
     * Inserts multiple employees into the system.
     *
     * @param employees List of employees to be inserted.
     * @return ResponseDto indicating success or failure.
     */
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

    /**
     * Deletes an employee by ID.
     *
     * @param idEmployee ID of the employee to be deleted.
     * @return ResponseDto indicating success or failure.
     */
    @Operation(summary = "Delete an employee by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Employee deleted successfully"),
        @ApiResponse(responseCode = "500", description = "General error")
    })
    @DeleteMapping
    public ResponseDto<?> deleteEmployees(@RequestParam @Min(1) Long idEmployee) {
        return employeeService.deleteEmployees(idEmployee);
    }

    /**
     * Updates the details of an existing employee.
     *
     * @param employee Employee data to update.
     * @return ResponseDto indicating success or failure.
     */
    @Operation(summary = "Update employee details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
        @ApiResponse(responseCode = "404", description = "Employee does not exist"),
        @ApiResponse(responseCode = "500", description = "General error")
    })
    @PutMapping
    public ResponseDto<?> updateEmployee(@RequestBody @Valid EmployeeDto employee) {
        return employeeService.updateEmployee(employee);
    }
}
