package com.transport.exception;

public class QuoteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuoteException(String errorMessage) {
		super(errorMessage);
	}

	public QuoteException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
