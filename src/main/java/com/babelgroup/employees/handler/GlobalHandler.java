package com.babelgroup.employees.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.babelgroup.employees.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalHandler {
	
	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<Object> handlerNoFound(ResourceNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handlerInternalServerError(Exception ex){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
}
