package com.midhun.crudoperations.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.midhun.crudoperations.entity.CustomError;

@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<CustomError> MovieNotFoundException(MovieNotFoundException ex){
		CustomError errorResponse = new CustomError(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
}
