package com.transport.exception;

public class VehicleUploadFailed extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VehicleUploadFailed(String errorMessage) {
		super(errorMessage);
	}
	public VehicleUploadFailed(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
