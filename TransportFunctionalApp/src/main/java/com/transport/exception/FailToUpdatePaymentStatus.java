package com.transport.exception;

public class FailToUpdatePaymentStatus extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FailToUpdatePaymentStatus(String errorMessage) {
		super(errorMessage);
	}
	public FailToUpdatePaymentStatus(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
