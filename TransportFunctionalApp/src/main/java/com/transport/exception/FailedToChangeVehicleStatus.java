package com.transport.exception;

public class FailedToChangeVehicleStatus extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FailedToChangeVehicleStatus(String errorMessage) {
		super(errorMessage);
	}
	public FailedToChangeVehicleStatus(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
