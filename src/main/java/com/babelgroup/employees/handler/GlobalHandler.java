package com.babelgroup.employees.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.babelgroup.employees.dto.ResponseDto;
import com.babelgroup.employees.exceptions.ResourceNotFoundException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalHandler {
	
	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<ResponseDto<?>> handlerNoFound(ResourceNotFoundException ex){
		ResponseDto<String> response = new ResponseDto<>();
		response.setMsg(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ResponseDto<?>> handlerInternalServerError(Exception ex){
		ResponseDto<String> response = new ResponseDto<>();
		response.setMsg(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseDto<String>> handleValidationException(ConstraintViolationException ex) {
        ResponseDto<String> response = new ResponseDto<>();
        response.setMsg("Validation failed: " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
