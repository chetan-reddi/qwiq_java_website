package com.transport.exception;

public class FaileToChangePassword extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FaileToChangePassword(String errorMessage) {
		super(errorMessage);
	}
	public FaileToChangePassword(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
