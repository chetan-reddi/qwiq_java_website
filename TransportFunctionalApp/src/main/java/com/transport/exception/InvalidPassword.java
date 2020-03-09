package com.transport.exception;

public class InvalidPassword extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidPassword(String errorMessage) {
		super(errorMessage);
	}
	public InvalidPassword(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}

}
