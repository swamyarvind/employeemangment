package com.example.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
	        ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now(), null);
	        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	    }
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getAllErrors().forEach((error) -> {
	            String fieldName = ((FieldError) error).getField();
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	        });
	        
	        ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation Failed", LocalDateTime.now(), errors);
	        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	    }
	  @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
	        ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), LocalDateTime.now(), null);
	        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
