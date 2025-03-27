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
	    private String primerNombre;

	    @Column(name = "SEGUNDONOMBRE" , length = 50)
	    private String segundoNombre;

	    @Column(name = "APELLIDOPATERNO" ,nullable = false, length = 50)
	    private String apellidoPaterno;

	    @Column(name = "APELLIDOMATERNO" ,length = 50)
	    private String apellidoMaterno;

	    @Column(nullable = false)
	    private int edad;

	    @Column(nullable = false, length = 1)
	    private char sexo;

	    @Column(name = "FECHANACIMIENTO" ,nullable = false)
	    private LocalDate fechaNacimiento;

	    @Column(nullable = false, length = 100)
	    private String puesto;
}
