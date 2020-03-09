package com.transport.exception;

public class FailedUpdateDocs extends Exception {

	
	private static final long serialVersionUID = 1L;

	public FailedUpdateDocs(String message) {
		super(message);
	}

	public FailedUpdateDocs(String message, Throwable cause) {
		super(message, cause);
	}
}
