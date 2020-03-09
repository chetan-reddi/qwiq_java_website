package com.transport.exception;

public class PasswordMismatchException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PasswordMismatchException(String errorMessage) {
		super(errorMessage);
	}
	public PasswordMismatchException(String errorMessage, Exception ex){
		super(errorMessage, ex);
	}
}
