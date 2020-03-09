package com.transport.exception;

public class QuoteCancell extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public QuoteCancell(String errorMessage) {
		super(errorMessage);
	}
	public QuoteCancell(String errorMessage, Exception ex){
		super(errorMessage, ex);
	}
}
