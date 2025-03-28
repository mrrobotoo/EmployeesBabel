package com.babelgroup.employees.handler;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.babelgroup.employees.dto.ResponseDto;
import com.babelgroup.employees.exceptions.ResourceNotFoundException;

import jakarta.validation.ConstraintViolationException;

/**
 * Global exception handler for the application.
 * <p>
 * This class handles various exceptions globally across all controllers in the application. 
 * It catches specific exceptions such as {@link ResourceNotFoundException}, {@link Exception}, 
 * {@link ConstraintViolationException}, and {@link MethodArgumentNotValidException} 
 * and returns appropriate responses to the client.
 * </p>
 */
@RestControllerAdvice
public class GlobalHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalHandler.class);

    /**
     * Handles {@link ResourceNotFoundException} and returns a {@link ResponseEntity} with a 
     * 404 NOT FOUND status and the exception message.
     *
     * @param ex The {@link ResourceNotFoundException} thrown.
     * @return A {@link ResponseEntity} containing the error message and HTTP status 404.
     */
    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<ResponseDto<?>> handlerNoFound(ResourceNotFoundException ex) {
        ResponseDto<String> response = new ResponseDto<>();
        response.setMsg(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles any general {@link Exception} and returns a {@link ResponseEntity} with a 
     * 500 INTERNAL SERVER ERROR status and a generic error message.
     *
     * @param ex The {@link Exception} thrown.
     * @return A {@link ResponseEntity} containing a generic error message and HTTP status 500.
     */
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ResponseDto<?>> handlerInternalServerError(Exception ex) {
        ResponseDto<String> response = new ResponseDto<>();
        response.setMsg("Unexpected error, please validate later. If the error persists, please contact the systems.");
        logger.error("Error in: " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles {@link ConstraintViolationException} and returns a {@link ResponseEntity} with a 
     * 403 FORBIDDEN status and a message containing the validation error details.
     *
     * @param ex The {@link ConstraintViolationException} thrown.
     * @return A {@link ResponseEntity} containing the validation errors and HTTP status 403.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseDto<String>> handleValidationException(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.joining(", "));

        ResponseDto<String> response = new ResponseDto<>();
        response.setMsg(message);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    /**
     * Handles {@link MethodArgumentNotValidException} and returns a {@link ResponseEntity} with a 
     * 400 BAD REQUEST status and the first validation error message.
     *
     * @param ex The {@link MethodArgumentNotValidException} thrown.
     * @return A {@link ResponseEntity} containing the first validation error message and HTTP status 400.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
