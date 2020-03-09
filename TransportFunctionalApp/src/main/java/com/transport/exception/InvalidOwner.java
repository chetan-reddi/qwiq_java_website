package com.transport.exception;

public class InvalidOwner extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidOwner(String errorMessage) {
		super(errorMessage);
	}
	public InvalidOwner(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
