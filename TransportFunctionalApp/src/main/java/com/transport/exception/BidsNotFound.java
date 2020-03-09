package com.transport.exception;

public class BidsNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BidsNotFound(String errorMessage) {
		super(errorMessage);
	}
	public BidsNotFound(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
