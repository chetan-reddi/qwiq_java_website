package com.transport.exception;

public class UnableToCompleteOrder extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UnableToCompleteOrder(String errorMessage) {
		super(errorMessage);
	}
	public UnableToCompleteOrder(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}

}
