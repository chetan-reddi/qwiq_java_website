package com.transport.exception;

public class InvalidItemId extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidItemId(String errorMessage) {
		super(errorMessage);
	}
	public InvalidItemId(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
