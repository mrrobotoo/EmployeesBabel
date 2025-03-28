package com.babelgroup.employees.service;

import java.util.List;

import com.babelgroup.employees.dto.EmployeeDto;
import com.babelgroup.employees.dto.ResponseDto;
import com.babelgroup.employees.entities.EmployeeEntity;

/**
 * Service interface for managing employee operations.
 * 
 * <p>This interface defines the contract for employee-related operations, 
 * including retrieval, insertion, updating, and deletion of employees.</p>
 * 
 * <h2>Operations:</h2>
 * <ul>
 *   <li>Retrieve a list of employees</li>
 *   <li>Insert new employees</li>
 *   <li>Update employee information</li>
 *   <li>Delete an employee by ID</li>
 * </ul>
 * 
 * <p>Implementations of this interface should handle validation and 
 * exception management appropriately.</p>
 */
public interface EmployeeService {
	
	ResponseDto<List<EmployeeEntity>> getEmployees();
	ResponseDto<?> insertEmployees(List<EmployeeDto> employees);
	ResponseDto<?> deleteEmployees(Long idPerson);
	ResponseDto<?> updateEmployee(EmployeeDto employee);
}
