package com.transport.exception;

public class FaileToAssignDriver extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FaileToAssignDriver(String errorMessage) {
		super(errorMessage);
	}
	public FaileToAssignDriver(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
