package com.transport.exception;

public class FailedToChangeDriverOperationStatus extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FailedToChangeDriverOperationStatus(String errorMessage) {
		super(errorMessage);
	}
	public FailedToChangeDriverOperationStatus(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
