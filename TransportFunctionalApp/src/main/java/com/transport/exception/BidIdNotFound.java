package com.transport.exception;

public class BidIdNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BidIdNotFound(String errorMessage) {
		super(errorMessage);
	}
	public BidIdNotFound(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
