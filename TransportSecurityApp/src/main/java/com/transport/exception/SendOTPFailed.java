package com.transport.exception;

public class SendOTPFailed extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SendOTPFailed(String errorMessage) {
		super(errorMessage);
	}
	public SendOTPFailed(String errorMessage, Exception ex){
		super(errorMessage, ex);
	}
}
