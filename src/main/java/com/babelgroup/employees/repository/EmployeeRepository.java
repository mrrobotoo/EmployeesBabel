package com.babelgroup.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babelgroup.employees.entities.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{

}
