package com.transport.exception;

public class UserNameExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserNameExistsException(String errorMessage) {
		super(errorMessage);
	}
	public UserNameExistsException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
