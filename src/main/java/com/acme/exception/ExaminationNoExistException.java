package com.acme.exception;

public class ExaminationNoExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -52643669675505308L;

	public ExaminationNoExistException(String mensaje) {
		super(mensaje);
	}
}
