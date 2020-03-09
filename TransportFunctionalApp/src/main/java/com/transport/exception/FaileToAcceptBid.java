package com.transport.exception;

public class FaileToAcceptBid extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FaileToAcceptBid(String errorMessage) {
		super(errorMessage);
	}
	public FaileToAcceptBid(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
