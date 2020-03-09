package com.transport.exception;

public class InvalidOrderId extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidOrderId(String errorMessage) {
		super(errorMessage);
	}
	public InvalidOrderId(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
