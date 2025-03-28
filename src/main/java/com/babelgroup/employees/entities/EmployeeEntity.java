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


@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeEntity {
 	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRIMERNOMBRE" ,nullable = false, length = 50)
    private String firstName;

    @Column(name = "SEGUNDONOMBRE" , length = 50)
    private String secondName;

    @Column(name = "APELLIDOPATERNO" ,nullable = false, length = 50)
    private String lastName;

    @Column(name = "APELLIDOMATERNO" ,length = 50)
    private String mothersLastName;

    @Column(name = "EDAD" ,nullable = false)
    private int age;

    @Column(name = "SEXO" ,nullable = false, length = 1)
    private char gender;

    @Column(name = "FECHANACIMIENTO" ,nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "PUESTO" ,nullable = false, length = 100)
    private String jobPosition;
}
