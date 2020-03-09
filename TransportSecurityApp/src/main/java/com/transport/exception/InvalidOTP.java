package com.transport.exception;

public class InvalidOTP extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidOTP(String errorMessage) {
		super(errorMessage);
	}

	public InvalidOTP(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}