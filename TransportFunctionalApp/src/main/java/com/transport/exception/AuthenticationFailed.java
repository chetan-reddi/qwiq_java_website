package com.transport.exception;

public class AuthenticationFailed extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AuthenticationFailed(String errorMessage) {
		super(errorMessage);
	}
	public AuthenticationFailed(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
