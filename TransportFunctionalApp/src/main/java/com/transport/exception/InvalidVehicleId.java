package com.transport.exception;

public class InvalidVehicleId extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidVehicleId(String errorMessage) {
		super(errorMessage);
	}
	public InvalidVehicleId(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}

}
