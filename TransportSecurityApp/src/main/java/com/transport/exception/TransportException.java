package com.transport.exception;

public class TransportException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TransportException(String errorMessage) {
		super(errorMessage);
	}
	public TransportException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
