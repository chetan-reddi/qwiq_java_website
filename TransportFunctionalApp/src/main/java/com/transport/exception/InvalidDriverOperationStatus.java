package com.transport.exception;

public class InvalidDriverOperationStatus extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidDriverOperationStatus(String errorMessage) {
		super(errorMessage);
	}
	public InvalidDriverOperationStatus(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
