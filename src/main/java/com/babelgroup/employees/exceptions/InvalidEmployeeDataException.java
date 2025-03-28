package com.babelgroup.employees.exceptions;

/**
 * Exception thrown when an employee's data is invalid.
 * <p>This exception is used when the provided employee information is missing required fields, 
 * such as the first name or last name, during the validation process.</p>
 */
public class InvalidEmployeeDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * Constructs a new {@code InvalidEmployeeDataException} with the specified detail message.
     * 
     * @param message The detail message.
     */
	public InvalidEmployeeDataException(String message) {
        super(message);
    }

}
