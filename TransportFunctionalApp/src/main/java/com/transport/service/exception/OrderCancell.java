package com.transport.service.exception;

public class OrderCancell extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OrderCancell(String errorMessage) {
		super(errorMessage);
	}
	public OrderCancell(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
