package com.babelgroup.employees.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Entity class representing an Employee in the database.
 * Mapped to the "EMPLOYEE" table.
 */
@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeEntity {
	/** The unique identifier for an employee. */
 	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
 	/** The first name of the employee. Cannot be null. */
    @Column(name = "PRIMERNOMBRE" ,nullable = false, length = 50)
    private String firstName;

    /** The second name of the employee. Optional field. */
    @Column(name = "SEGUNDONOMBRE" , length = 50)
    private String secondName;
    
    /** The last name (paternal surname) of the employee. Cannot be null. */
    @Column(name = "APELLIDOPATERNO" ,nullable = false, length = 50)
    private String lastName;

    /** The mother's last name (maternal surname) of the employee. Optional field. */
    @Column(name = "APELLIDOMATERNO" ,length = 50)
    private String mothersLastName;

    /** The age of the employee. Cannot be null. */
    @Column(name = "EDAD" ,nullable = false)
    private int age;

    /** The gender of the employee. Cannot be null. (e.g., 'M' for male, 'F' for female) */
    @Column(name = "SEXO" ,nullable = false, length = 1)
    private String gender;

    /** The birth date of the employee. Cannot be null. */
    @Column(name = "FECHANACIMIENTO" ,nullable = false)
    private LocalDate dateOfBirth;
    
    /** The job position/title of the employee. Cannot be null. */
    @Column(name = "PUESTO" ,nullable = false, length = 100)
    private String jobPosition;
}
