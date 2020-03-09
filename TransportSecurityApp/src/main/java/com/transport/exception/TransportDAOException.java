package com.transport.exception;

public class TransportDAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TransportDAOException(String errorMessage) {
		super(errorMessage);
	}
	public TransportDAOException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}

}
