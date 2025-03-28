package com.babelgroup.employees.exceptions;

/**
 * Exception thrown when trying to insert an employee that already exists in the database.
 * <p>This exception is used when an employee with the same name or other identifying details 
 * already exists in the system. It prevents the insertion of duplicate employee records.</p>
 */
public class EmployeeAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * Constructs a new {@code EmployeeAlreadyExistsException} with the specified detail message.
     * 
     * @param message The detail message.
     */
	public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}
