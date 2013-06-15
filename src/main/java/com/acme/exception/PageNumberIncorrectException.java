package com.acme.exception;

public class PageNumberIncorrectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1855524905707517730L;
	
	public PageNumberIncorrectException(String mensaje) {
		super(mensaje);
	}
}
