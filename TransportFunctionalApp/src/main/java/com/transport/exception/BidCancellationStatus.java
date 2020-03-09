package com.transport.exception;

public class BidCancellationStatus extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BidCancellationStatus(String errorMessage) {
		super(errorMessage);
	}
	public BidCancellationStatus(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
