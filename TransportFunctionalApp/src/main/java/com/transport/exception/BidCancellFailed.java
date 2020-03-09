package com.transport.exception;

public class BidCancellFailed extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BidCancellFailed(String errorMessage) {
		super(errorMessage);
	}
	public BidCancellFailed(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
