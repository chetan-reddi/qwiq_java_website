package com.transport.exception;

public class InvalidQuoteId extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidQuoteId(String errorMessage) {
		super(errorMessage);
	}
	public InvalidQuoteId(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}

}
