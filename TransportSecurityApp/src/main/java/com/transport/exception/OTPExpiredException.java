package com.transport.exception;

public class OTPExpiredException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OTPExpiredException(String errorMessage) {
		super(errorMessage);
	}

	public OTPExpiredException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}