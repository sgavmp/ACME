package com.acme.exception;

public class UsernameExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5332796230363496032L;

	public UsernameExistException(String mensaje) {
		super(mensaje);
	}
}
