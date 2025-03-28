package com.babelgroup.employees.constants;

/**
 * Class containing constant messages used throughout the application.
 * These messages are typically used for status updates or error reporting
 * related to employee operations such as insertion, deletion, and updates.
 * This class does not require instantiation as it only holds constant values.
 */
public class MessageConstants {

    /**
     * Message indicating a successful operation.
     */
    public static final String SUCCESS = "ok";
    
    /**
     * Message shown when no employees are found.
     */
    public static final String NO_EMPLOYEES_FOUND = "No employees found";
    
    /**
     * Message indicating that employees were inserted successfully.
     */
    public static final String EMPLOYEES_INSERTED = "Employees were inserted successfully.";
    
    /**
     * Message indicating that an employee was deleted successfully.
     */
    public static final String EMPLOYEE_DELETED = "Successfully removed";
    
    /**
     * Message shown when an employee with a given ID is not found.
     * The %d is a placeholder for the employee's ID.
     */
    public static final String EMPLOYEE_NOT_FOUND = "The user with ID %d does not exist, please validate.";
    
    /**
     * Message indicating that the employee's data was updated successfully.
     */
    public static final String EMPLOYEE_UPDATED = "The user was updated successfully.";
    
    /**
     * Message indicating that no changes were made to the employee's data.
     */
    public static final String EMPLOYEE_NO_CHANGES = "No changes were made to the user.";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private MessageConstants() {
        // Empty private constructor
    }
}

