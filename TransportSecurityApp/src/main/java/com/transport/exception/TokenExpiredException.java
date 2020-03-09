package com.transport.exception;

public class TokenExpiredException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenExpiredException(String errorMessage) {
		super(errorMessage);
	}

	public TokenExpiredException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}