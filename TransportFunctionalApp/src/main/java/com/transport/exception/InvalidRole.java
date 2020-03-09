package com.transport.exception;

public class InvalidRole extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidRole(String errorMessage) {
		super(errorMessage);
	}
	public InvalidRole(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
