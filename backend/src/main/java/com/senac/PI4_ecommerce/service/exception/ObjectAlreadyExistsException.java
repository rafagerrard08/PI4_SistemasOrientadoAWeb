package com.senac.PI4_ecommerce.service.exception;

public class ObjectAlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ObjectAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ObjectAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}