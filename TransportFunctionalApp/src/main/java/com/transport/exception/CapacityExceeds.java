package com.transport.exception;

public class CapacityExceeds extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CapacityExceeds(String errorMessage) {
		super(errorMessage);
	}
	public CapacityExceeds(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
