package com.babelgroup.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.babelgroup.employees.entities.EmployeeEntity;

/**
 * Repository interface for accessing data related to the {@link EmployeeEntity}.
 * <p>
 * This interface extends {@link JpaRepository}, providing CRUD (Create, Read, Update, Delete)
 * operations on the database without the need for explicit method implementation.
 * </p>
 * <p>
 * The identifier for the {@link EmployeeEntity} is of type {@link Long}.
 * </p>
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{

}
