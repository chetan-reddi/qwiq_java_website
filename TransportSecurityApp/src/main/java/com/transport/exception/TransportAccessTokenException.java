package com.transport.exception;

public class TransportAccessTokenException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TransportAccessTokenException(String errorMessage) {
		super(errorMessage);
	}
	public TransportAccessTokenException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
