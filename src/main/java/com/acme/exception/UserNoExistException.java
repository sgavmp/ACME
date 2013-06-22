package com.acme.exception;

public class UserNoExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9157939523047232978L;
	
	public UserNoExistException(String mensaje) {
		super(mensaje);
	}
}
