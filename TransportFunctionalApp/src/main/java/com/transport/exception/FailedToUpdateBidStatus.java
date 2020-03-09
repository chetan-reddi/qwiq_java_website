package com.transport.exception;

public class FailedToUpdateBidStatus extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FailedToUpdateBidStatus(String errorMessage) {
		super(errorMessage);
	}
	public FailedToUpdateBidStatus(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
