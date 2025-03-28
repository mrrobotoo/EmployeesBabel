package com.babelgroup.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Employees Babel application.
 * <p>
 * This class serves as the entry point for the Spring Boot application,
 * initializing and running the application context.
 * </p>
 */
@SpringBootApplication
public class EmployeesBabelApplication {

    /**
     * Main method to launch the Spring Boot application.
     *
     * @param args Command-line arguments passed during application startup.
     */
    public static void main(String[] args) {
        SpringApplication.run(EmployeesBabelApplication.class, args);
    }
}
