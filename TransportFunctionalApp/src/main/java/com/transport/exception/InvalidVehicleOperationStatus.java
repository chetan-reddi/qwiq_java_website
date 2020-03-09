/**
 * 
 */
package com.transport.exception;

/**
 * @author CST
 *
 */
public class InvalidVehicleOperationStatus extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidVehicleOperationStatus(String errorMessage) {
		super(errorMessage);
	}
	public InvalidVehicleOperationStatus(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}

}
