package com.transport.exception;

public class InvalidUserId extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidUserId(String errorMessage) {
		super(errorMessage);
	}
	public InvalidUserId(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}

}
