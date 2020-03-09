package com.transport.exception;

public class KYCDocsNotUpload extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public KYCDocsNotUpload(String errorMessage) {
		super(errorMessage);
	}
	public KYCDocsNotUpload(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}

}
