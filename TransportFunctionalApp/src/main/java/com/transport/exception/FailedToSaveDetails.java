package com.transport.exception;

public class FailedToSaveDetails extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FailedToSaveDetails(String errorMessage) {
		super(errorMessage);
	}
	public FailedToSaveDetails(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
