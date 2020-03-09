package com.transport.exception;

public class BidUpdationFailed extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BidUpdationFailed(String errorMessage) {
		super(errorMessage);
	}
	public BidUpdationFailed(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
