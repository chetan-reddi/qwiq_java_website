package com.transport.exception;

public class ResendOTPFailed extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ResendOTPFailed(String errorMessage) {
		super(errorMessage);
	}
	public ResendOTPFailed(String errorMessage, Exception ex){
		super(errorMessage, ex);
	}

}
