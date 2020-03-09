package com.transport.exception;

public class NoProfileDetails extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NoProfileDetails(String errorMessage) {
		super(errorMessage);
	}
	public NoProfileDetails(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
