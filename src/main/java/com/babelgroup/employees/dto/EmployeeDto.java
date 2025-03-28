package com.babelgroup.employees.dto;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	
    @NotBlank(message = "First name cannot be null or empty")
	@Size(max = 50, message = "First name cannot exceed 50 characters")
	private String firstName;

	@Size(max = 50, message = "Second name cannot exceed 50 characters")
	private String secondName;

	@NotBlank(message = "Last name cannot be null or empty")
	@Size(max = 50, message = "Last name cannot exceed 50 characters")
	private String lastName;

	@Size(max = 50, message = "Mother's last name cannot exceed 50 characters")
	private String mothersLastName;

	@Min(value = 0, message = "Age must be a positive number")
	@Max(value = 120, message = "Age cannot exceed 120 years")
	private int age;

	@NotBlank(message = "Gender cannot be null  or empty")
	@Pattern(regexp = "M|F", message = "Gender must be 'M' or 'F'")
	private String gender;

	@NotNull(message = "Date of birth cannot be null")
	@Past(message = "Date of birth must be a past date")
	private LocalDate dateOfBirth;

	@NotBlank(message = "Job position cannot be null or empty")
	@Size(max = 100, message = "Job position cannot exceed 100 characters")
	private String jobPosition;

}
