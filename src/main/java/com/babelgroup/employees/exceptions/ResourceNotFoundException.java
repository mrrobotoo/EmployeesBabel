package com.babelgroup.employees.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Custom exception class to represent resource not found errors.
 * <p>
 * This exception is thrown when a requested resource is not found. It is annotated with {@link ResponseStatus}
 * to automatically set the HTTP status code to {@link HttpStatus#NOT_FOUND} (404) when the exception is thrown.
 * </p>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new {@link ResourceNotFoundException} with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}