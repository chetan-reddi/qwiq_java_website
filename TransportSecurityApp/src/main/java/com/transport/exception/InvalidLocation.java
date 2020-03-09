package com.transport.exception;

public class InvalidLocation extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidLocation(String errorMessage) {
		super(errorMessage);
	}
	public InvalidLocation(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}

}
