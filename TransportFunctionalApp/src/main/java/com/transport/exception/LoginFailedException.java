package com.transport.exception;

public class LoginFailedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LoginFailedException(String errorMessage) {
		super(errorMessage);
	}
	public LoginFailedException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
