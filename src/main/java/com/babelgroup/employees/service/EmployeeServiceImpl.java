package com.babelgroup.employees.service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babelgroup.employees.constants.MessageConstants;
import com.babelgroup.employees.dto.EmployeeDto;
import com.babelgroup.employees.dto.ResponseDto;
import com.babelgroup.employees.entities.EmployeeEntity;
import com.babelgroup.employees.exceptions.EmployeeAlreadyExistsException;
import com.babelgroup.employees.exceptions.InvalidEmployeeDataException;
import com.babelgroup.employees.exceptions.ResourceNotFoundException;
import com.babelgroup.employees.repository.EmployeeRepository;

/**
 * Service implementation for managing employee-related operations.
 * 
 * <p>This class provides CRUD functionalities for employees, including:
 * retrieving, inserting, updating, and deleting employee records.</p>
 * 
 * <p>It interacts with the {@link EmployeeRepository} to perform database operations.</p>
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    /**
     * Retrieves all employees from the database.
     * @return ResponseDto containing a list of employees.
     */
    @Override
    public ResponseDto<List<EmployeeEntity>> getEmployees() {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        ResponseDto<List<EmployeeEntity>> response = new ResponseDto<>();
        response.setData(employees);
        response.setMsg(employees.isEmpty() ? MessageConstants.NO_EMPLOYEES_FOUND : MessageConstants.SUCCESS);
        return response;
    }
    
    /**
     * Inserts a list of new employees into the database.
     * @param employees List of EmployeeDto objects to be inserted.
     * @return ResponseDto with a success message.
     * @throws EmployeeAlreadyExistsException if an employee with the same data already exists.
     */
    @Override
    public ResponseDto<?> insertEmployees(List<EmployeeDto> employees) {
        // Verifica si ya existen empleados con los mismos datos
        for (EmployeeDto employeeDto : employees) {
            if (employeeRepository.existsByFirstNameAndLastName(employeeDto.getFirstName(), employeeDto.getLastName())) {
                throw new EmployeeAlreadyExistsException(String.format(MessageConstants.EMPLOYEE_ALREADY_EXISTS, employeeDto.getFirstName() + " " + employeeDto.getLastName()));
            }
        }
        
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
        response.setMsg(MessageConstants.EMPLOYEES_INSERTED);
        return response;
    }
    
    /**
     * Deletes an employee by their ID.
     * @param idPerson ID of the employee to be deleted.
     * @return ResponseDto with a success or error message.
     * @throws ResourceNotFoundException if the employee is not found.
     */
    @Override
    public ResponseDto<?> deleteEmployees(Long idPerson) {
        return employeeRepository.findById(idPerson)
                .map(employee -> {
                    employeeRepository.deleteById(idPerson);
                    ResponseDto<String> response = new ResponseDto<>();
                    response.setMsg(MessageConstants.EMPLOYEE_DELETED);
                    return response;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(MessageConstants.EMPLOYEE_NOT_FOUND, idPerson)));
    }

    /**
     * Updates an existing employee's information.
     * @param employeeDto EmployeeDto object containing updated data.
     * @return ResponseDto with update status.
     * @throws ResourceNotFoundException if the employee is not found.
     * @throws InvalidEmployeeDataException if the updated data is invalid.
     */
    @Override
    public ResponseDto<?> updateEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeDto.getId()));

        // Validación de datos del empleado
        if (employeeDto.getFirstName() == null || employeeDto.getLastName() == null) {
            throw new InvalidEmployeeDataException(MessageConstants.INVALID_EMPLOYEE_DATA);
        }

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
            response.setMsg(MessageConstants.EMPLOYEE_UPDATED);
        } else {
            response.setMsg(MessageConstants.EMPLOYEE_NO_CHANGES);
        }
        return response;
    }

    /**
     * Helper method to update an entity field if the new value is different.
     * @param getter Method reference to retrieve the current value.
     * @param newValue The new value to be set.
     * @param setter Method reference to update the value.
     * @return true if the field was updated, false otherwise.
     */
    private <T> boolean updateIfChanged(Supplier<T> getter, T newValue, Consumer<T> setter) {
        if (getter.get() == null && newValue == null || (getter.get() != null && getter.get().equals(newValue))) {
            return false;
        }
        setter.accept(newValue); 
        return true;
    }
}
