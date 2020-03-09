package com.transport.exception;

public class BidAlreadyPlaced extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BidAlreadyPlaced(String errorMessage) {
		super(errorMessage);
	}
	public BidAlreadyPlaced(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
