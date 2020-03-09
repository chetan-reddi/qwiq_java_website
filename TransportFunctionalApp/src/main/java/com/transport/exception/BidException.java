package com.transport.exception;

public class BidException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BidException(String errorMessage) {
		super(errorMessage);
	}
	public BidException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
