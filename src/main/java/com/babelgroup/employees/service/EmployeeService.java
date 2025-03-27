package com.babelgroup.employees.service;

import java.util.List;

import com.babelgroup.employees.dto.EmployeeDto;
import com.babelgroup.employees.dto.ResponseDto;
import com.babelgroup.employees.entities.EmployeeEntity;

public interface EmployeeService {
	
	ResponseDto<List<EmployeeEntity>> getEmployees();
	ResponseDto<?> insertEmployees(List<EmployeeDto> employees);
	ResponseDto<?> deleteEmployees(Long idPerson);
	ResponseDto<?> updateEmployee(EmployeeDto employee);
}
