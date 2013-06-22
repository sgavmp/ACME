package com.acme.exception;

public class DateIncorrectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2549616657344489999L;

	public DateIncorrectException(String mensaje) {
		super(mensaje);
	}
}
