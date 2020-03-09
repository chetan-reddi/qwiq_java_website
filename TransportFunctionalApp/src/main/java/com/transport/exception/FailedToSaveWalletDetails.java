package com.transport.exception;

public class FailedToSaveWalletDetails extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FailedToSaveWalletDetails(String errorMessage) {
		super(errorMessage);
	}
	public FailedToSaveWalletDetails(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
