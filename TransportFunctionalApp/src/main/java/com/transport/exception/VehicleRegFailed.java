package com.transport.exception;

public class VehicleRegFailed extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VehicleRegFailed(String errorMessage) {
		super(errorMessage);
	}
	public VehicleRegFailed(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}
