package com.transport.exception;

public class InvalidMaildId extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidMaildId(String errorMessage) {
		super(errorMessage);
	}
	public InvalidMaildId(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}

}
