package com.transport.exception;

public class FailedToUpdateDetails extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FailedToUpdateDetails(String errorMessage) {
		super(errorMessage);
	}
	public FailedToUpdateDetails(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
