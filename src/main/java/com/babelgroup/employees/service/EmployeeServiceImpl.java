package com.babelgroup.employees.service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babelgroup.employees.dto.EmployeeDto;
import com.babelgroup.employees.dto.ResponseDto;
import com.babelgroup.employees.entities.EmployeeEntity;
import com.babelgroup.employees.exceptions.ResourceNotFoundException;
import com.babelgroup.employees.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public ResponseDto<List<EmployeeEntity>> getEmployees() {
		List<EmployeeEntity> employees = employeeRepository.findAll();

		ResponseDto<List<EmployeeEntity>> response = new ResponseDto<>();
	    response.setData(employees);
	    response.setMsg(employees.isEmpty() ? "No employees found" : "ok");

	    return response;
	}

	@Override
	public ResponseDto<?> insertEmployees(List<EmployeeDto> employees) {
	    List<EmployeeEntity> employeeEntities = employees.stream()
	            .map(employeeDto -> new EmployeeEntity(
	                    null, 
	                    employeeDto.getFirstName(),
	                    employeeDto.getSecondName(),
	                    employeeDto.getLastName(),
	                    employeeDto.getMothersLastName(),
	                    employeeDto.getAge(),
	                    employeeDto.getGender(),
	                    employeeDto.getDateOfBirth(),
	                    employeeDto.getJobPosition()))
	            .collect(Collectors.toList());

	 
	    employeeRepository.saveAll(employeeEntities);
	    
	    ResponseDto<String> response = new ResponseDto<>();
	    response.setMsg("Employees were inserted successfully.");
	    return response;
	}

	@Override
	public ResponseDto<?> deleteEmployees(Long idPerson) {
		 return employeeRepository.findById(idPerson)
			        .map(employee -> {
			            employeeRepository.deleteById(idPerson);
			            ResponseDto<String> response = new ResponseDto<>();
			            response.setMsg("Successfully removed");
			            return response;
			        })
			        .orElseGet(() -> {
			            throw new ResourceNotFoundException("The user with ID " + idPerson + " does not exist, please validate.");
			        });
	}

	@Override
	public ResponseDto<?> updateEmployee(EmployeeDto employeeDto) {
	    EmployeeEntity employeeEntity = employeeRepository.findById(employeeDto.getId())
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeDto.getId()));

	    boolean isUpdated = false;

	    isUpdated |= updateIfChanged(employeeEntity::getFirstName, employeeDto.getFirstName(), employeeEntity::setFirstName);
	    isUpdated |= updateIfChanged(employeeEntity::getSecondName, employeeDto.getSecondName(), employeeEntity::setSecondName);
	    isUpdated |= updateIfChanged(employeeEntity::getLastName, employeeDto.getLastName(), employeeEntity::setLastName);
	    isUpdated |= updateIfChanged(employeeEntity::getMothersLastName, employeeDto.getMothersLastName(), employeeEntity::setMothersLastName);
	    isUpdated |= updateIfChanged(employeeEntity::getAge, employeeDto.getAge(), employeeEntity::setAge);
	    isUpdated |= updateIfChanged(employeeEntity::getGender, employeeDto.getGender(), employeeEntity::setGender);
	    isUpdated |= updateIfChanged(employeeEntity::getDateOfBirth, employeeDto.getDateOfBirth(), employeeEntity::setDateOfBirth);
	    isUpdated |= updateIfChanged(employeeEntity::getJobPosition, employeeDto.getJobPosition(), employeeEntity::setJobPosition);

	    if (isUpdated) {
	        employeeRepository.save(employeeEntity);
	    }

	    ResponseDto<String> response = new ResponseDto<>();
	    if (isUpdated) {
	        response.setMsg("The user was updated successfully.");
	    } else {
	        response.setMsg("No changes were made to the user.");
	    }
	    return response;
	}

	private <T> boolean updateIfChanged(Supplier<T> getter, T newValue, Consumer<T> setter) {
	    if (getter.get() == null && newValue == null || (getter.get() != null && getter.get().equals(newValue))) {
	        return false; 
	    }
	    setter.accept(newValue); 
	    return true; 
	}

}
