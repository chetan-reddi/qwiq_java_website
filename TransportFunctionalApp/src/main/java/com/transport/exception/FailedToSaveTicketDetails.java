package com.transport.exception;

public class FailedToSaveTicketDetails extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FailedToSaveTicketDetails(String errorMessage) {
		super(errorMessage);
	}
	public FailedToSaveTicketDetails(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
