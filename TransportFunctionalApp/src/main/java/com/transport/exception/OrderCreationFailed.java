package com.transport.exception;

public class OrderCreationFailed extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OrderCreationFailed(String errorMessage) {
		super(errorMessage);
	}
	public OrderCreationFailed(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
