package com.acme.exception;

public class CertificationNoExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1352517902491558584L;
	
	public CertificationNoExistException(String mensaje) {
		super(mensaje);
	}
}
