package com.transport.exception;

public class InvalidAccount extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidAccount(String errorMessage) {
		super(errorMessage);
	}
	public InvalidAccount(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
