package com.transport.exception;

public class OrderUpdationFailed extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OrderUpdationFailed(String errorMessage) {
		super(errorMessage);
	}
	public OrderUpdationFailed(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
