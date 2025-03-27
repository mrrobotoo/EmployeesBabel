package com.babelgroup.employees.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private String primerNombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int edad;
    private char sexo;
    private LocalDate fechaNacimiento;
    private String puesto;
}
