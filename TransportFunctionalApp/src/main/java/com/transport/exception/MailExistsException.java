package com.transport.exception;

public class MailExistsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MailExistsException(String errorMessage) {
		super(errorMessage);
	}
	public MailExistsException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}