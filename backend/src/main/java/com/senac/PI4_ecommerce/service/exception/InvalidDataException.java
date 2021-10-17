package com.senac.PI4_ecommerce.service.exception;

public class InvalidDataException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}