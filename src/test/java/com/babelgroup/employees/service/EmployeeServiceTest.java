package com.babelgroup.employees.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.babelgroup.employees.dto.EmployeeDto;
import com.babelgroup.employees.dto.ResponseDto;
import com.babelgroup.employees.entities.EmployeeEntity;
import com.babelgroup.employees.exceptions.ResourceNotFoundException;
import com.babelgroup.employees.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService; 

    private EmployeeEntity employee;
    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        employee = new EmployeeEntity(1L, "John", "Michael", "Doe", "Smith", 30, "M", LocalDate.of(1994, 5, 10), "Developer");
        employeeDto = new EmployeeDto(1L, "John", "Michael", "Doe", "Smith", 30, "M", LocalDate.of(1994, 5, 10), "Developer");
    }

    @Test
    void getEmployees_shouldReturnListOfEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));

        ResponseDto<List<EmployeeEntity>> response = employeeService.getEmployees();

        assertFalse(response.getData().isEmpty());
        assertEquals(1, response.getData().size());

        verify(employeeRepository, times(1)).findAll(); 
    }

    @Test
    void getEmployees_shouldReturnEmptyList_whenNoEmployees() {
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseDto<List<EmployeeEntity>> response = employeeService.getEmployees();

        assertTrue(response.getData().isEmpty());

        verify(employeeRepository, times(1)).findAll(); 
    }

    @Test
    void insertEmployees_shouldSaveEmployees() {
        when(employeeRepository.saveAll(any())).thenReturn(Arrays.asList(employee));
        employeeService.insertEmployees(List.of(employeeDto));
        verify(employeeRepository, times(1)).saveAll(any());
    }

    @Test
    void insertEmployees_shouldSaveMultipleEmployees() {
        when(employeeRepository.saveAll(any())).thenReturn(Arrays.asList(employee, employee));
        employeeService.insertEmployees(Arrays.asList(employeeDto, employeeDto));
        verify(employeeRepository, times(1)).saveAll(any());
    }

    @Test
    void deleteEmployees_shouldDeleteEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).deleteById(1L);
        employeeService.deleteEmployees(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteEmployees_shouldNotDelete_whenEmployeeNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.deleteEmployees(1L);
        });

        verify(employeeRepository, never()).deleteById(anyLong());
    }

    @Test
    void updateEmployee_shouldUpdateExistingEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeDto updatedEmployeeDto = new EmployeeDto(1L, "John", "Michael", "Doe", "Smith", 31, "M", LocalDate.of(1994, 5, 10), "Senior Developer");
        when(employeeRepository.save(any())).thenReturn(employee);
        employeeService.updateEmployee(updatedEmployeeDto);

        verify(employeeRepository, times(1)).save(any());

        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void updateEmployee_shouldNotUpdate_whenNoChanges() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.updateEmployee(employeeDto);

        verify(employeeRepository, never()).save(any());
    }

    @Test
    void updateEmployee_shouldReturnErrorIfNotFound() {

        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.updateEmployee(employeeDto);
        });

        verify(employeeRepository, never()).save(any());
    }
}

