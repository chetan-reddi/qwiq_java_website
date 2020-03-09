package com.transport.exception;

public class FailedToRegDriver extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FailedToRegDriver(String errorMessage) {
		super(errorMessage);
	}
	public FailedToRegDriver(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
