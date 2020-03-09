package com.transport.constant;

public enum EnumVehicleOperationStatus {

	ACTIVE(1, "Active"),
	INACTIVE(2, "Inactive");
	private Integer statusId;
	private String status;

	EnumVehicleOperationStatus(String status) {
		this.status = status;
	}
	
	EnumVehicleOperationStatus(Integer statusId, String status) {
		this.statusId = statusId;
		this.status = status;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public String getStatus() {
		return status;
	}

	public static String getStatus(Integer statusId) {
		String status = "";
		for (EnumVehicleOperationStatus pStatus : EnumVehicleOperationStatus.values()) {
			if (pStatus.getStatusId().equals(statusId) ) {
				status = pStatus.getStatus();
				break;
			}
		}
		return status;
	}

	public static boolean isStatusAvailable(Integer statusId) {
		for (EnumVehicleOperationStatus pStatus : EnumVehicleOperationStatus.values()) {
			if (pStatus.getStatusId().equals(statusId)) {
				return true;
			}
		}
		return false;
	}
}
