package com.transport.exception;

public class InvalidDriverId extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidDriverId(String errorMessage) {
		super(errorMessage);
	}
	public InvalidDriverId(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}

}
