package com.acme.exception;

public class RegisterNoExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4163768100431778271L;
	
	public RegisterNoExistException(String mensaje) {
		super(mensaje);
	}
}
