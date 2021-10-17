package com.senac.PI4_ecommerce.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.senac.PI4_ecommerce.service.exception.InvalidDataException;
import com.senac.PI4_ecommerce.service.exception.ObjectAlreadyExistsException;
import com.senac.PI4_ecommerce.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StdError> objectNotFoundException(ObjectNotFoundException e){
		StdError erro = new StdError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	};
	
	@ExceptionHandler(ObjectAlreadyExistsException.class)
	public ResponseEntity<StdError> objectAlreadyExistsException(ObjectAlreadyExistsException e){
		StdError erro = new StdError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	};
	
	
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<StdError> InvalidDataException(InvalidDataException e){
		StdError erro = new StdError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(erro);
	};
}
