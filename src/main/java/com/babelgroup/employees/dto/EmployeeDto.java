package com.babelgroup.employees.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
    private String primerNombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int edad;
    private char sexo;
    private LocalDate fechaNacimiento;
    private String puesto;
}
