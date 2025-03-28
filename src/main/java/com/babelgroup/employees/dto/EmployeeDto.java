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

/**
 * Data Transfer Object (DTO) for Employee information. This class is used to
 * transfer employee data between different layers of the application.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto implements Serializable {

	/** Serial version UID for ensuring compatibility during serialization. */
	private static final long serialVersionUID = 1L;

	/** Employee unique identifier. */
	private Long id;

	/** First name of the employee (mandatory, max 50 characters). */
	@NotBlank(message = "First name cannot be null or empty")
	@Size(max = 50, message = "First name cannot exceed 50 characters")
	private String firstName;

	/** Second name of the employee (optional, max 50 characters). */
	@Size(max = 50, message = "Second name cannot exceed 50 characters")
	private String secondName;

	/** Last name of the employee (mandatory, max 50 characters). */
	@NotBlank(message = "Last name cannot be null or empty")
	@Size(max = 50, message = "Last name cannot exceed 50 characters")
	private String lastName;

	/** Mother's last name of the employee (optional, max 50 characters). */
	@Size(max = 50, message = "Mother's last name cannot exceed 50 characters")
	private String mothersLastName;

	/** Age of the employee (must be between 0 and 120 years). */
	@Min(value = 0, message = "Age must be a positive number")
	@Max(value = 120, message = "Age cannot exceed 120 years")
	private int age;

	/** Gender of the employee (mandatory, must be 'M' or 'F'). */
	@NotBlank(message = "Gender cannot be null or empty")
	@Pattern(regexp = "M|F", message = "Gender must be 'M' or 'F'")
	private String gender;

	/** Date of birth of the employee (mandatory, must be a past date). */
	@NotNull(message = "Date of birth cannot be null")
	@Past(message = "Date of birth must be a past date")
	private LocalDate dateOfBirth;

	/** Job position of the employee (mandatory, max 100 characters). */
	@NotBlank(message = "Job position cannot be null or empty")
	@Size(max = 100, message = "Job position cannot exceed 100 characters")
	private String jobPosition;
}
