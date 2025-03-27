package com.babelgroup.employees.service;

import java.util.List;
import java.util.Optional;
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
		ResponseDto<List<EmployeeEntity>> response = new ResponseDto<>();
		response.setData(employeeRepository.findAll());
		response.setMsg("ok");
		
		return response;
	}

	@Override
	public ResponseDto<?> insertEmployees(List<EmployeeDto> employees) {
	    List<EmployeeEntity> employeeEntities = employees.stream()
	            .map(employeeDto -> new EmployeeEntity(
	                    null, 
	                    employeeDto.getPrimerNombre(),
	                    employeeDto.getSegundoNombre(),
	                    employeeDto.getApellidoPaterno(),
	                    employeeDto.getApellidoMaterno(),
	                    employeeDto.getEdad(),
	                    employeeDto.getSexo(),
	                    employeeDto.getFechaNacimiento(),
	                    employeeDto.getPuesto()))
	            .collect(Collectors.toList());

	 
	    employeeRepository.saveAll(employeeEntities);
	    
	    ResponseDto<String> response = new ResponseDto<>();
	    response.setMsg("Employees were inserted successfully.");
	    return response;
	}

	@Override
	public ResponseDto<?> deleteEmployees(Long idPerson) {
		Optional<EmployeeEntity> employee = 
				employeeRepository.findById(idPerson); 
		ResponseDto<String> response = new ResponseDto<>();
    	
    	String mensaje = (employee.isPresent()) ? "Successfully removed" : "The user does not exist,  " + idPerson + " please validate.";
    	
    	employeeRepository.deleteById(idPerson);
    	response.setMsg(mensaje);
		
        return response;
	}

	@Override
	public ResponseDto<?> updateEmployee(EmployeeDto employeeDto) {
	    EmployeeEntity employeeEntity = employeeRepository.findById(employeeDto.getId())
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeDto.getId()));

	    boolean isUpdated = false;

	    isUpdated |= updateIfChanged(employeeEntity::getPrimerNombre, employeeDto.getPrimerNombre(), employeeEntity::setPrimerNombre);
	    isUpdated |= updateIfChanged(employeeEntity::getSegundoNombre, employeeDto.getSegundoNombre(), employeeEntity::setSegundoNombre);
	    isUpdated |= updateIfChanged(employeeEntity::getApellidoPaterno, employeeDto.getApellidoPaterno(), employeeEntity::setApellidoPaterno);
	    isUpdated |= updateIfChanged(employeeEntity::getApellidoMaterno, employeeDto.getApellidoMaterno(), employeeEntity::setApellidoMaterno);
	    isUpdated |= updateIfChanged(employeeEntity::getEdad, employeeDto.getEdad(), employeeEntity::setEdad);
	    isUpdated |= updateIfChanged(employeeEntity::getSexo, employeeDto.getSexo(), employeeEntity::setSexo);
	    isUpdated |= updateIfChanged(employeeEntity::getFechaNacimiento, employeeDto.getFechaNacimiento(), employeeEntity::setFechaNacimiento);
	    isUpdated |= updateIfChanged(employeeEntity::getPuesto, employeeDto.getPuesto(), employeeEntity::setPuesto);

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
