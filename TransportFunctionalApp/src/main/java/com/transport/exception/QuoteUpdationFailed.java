package com.transport.exception;

public class QuoteUpdationFailed extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuoteUpdationFailed(String errorMessage) {
		super(errorMessage);
	}

	public QuoteUpdationFailed(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
