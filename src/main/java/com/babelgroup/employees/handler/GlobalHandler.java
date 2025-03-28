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

@RestControllerAdvice
public class GlobalHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalHandler.class);

	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<ResponseDto<?>> handlerNoFound(ResourceNotFoundException ex){
		ResponseDto<String> response = new ResponseDto<>();
		response.setMsg(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ResponseDto<?>> handlerInternalServerError(Exception ex){
		ResponseDto<String> response = new ResponseDto<>();
		response.setMsg("Unexpected error, please validate later. If the error persists, please contact the systems.");
		logger.error("Error in ." + ex.getMessage() );
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
	    String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
	    return ResponseEntity.badRequest().body(errorMessage);
	}
}
