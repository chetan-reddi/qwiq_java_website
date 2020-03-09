package com.transport.constant;

public enum EnumVehicleDocStatus {

	PENDING(1, "Pending"),
	APPROVED(2, "Approved"),
	REJECTED(3, "Rejected");
	private Integer statusId;
	private String status;

	EnumVehicleDocStatus(String status) {
		this.status = status;
	}
	
	EnumVehicleDocStatus(Integer statusId, String status) {
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
		for (EnumVehicleDocStatus pStatus : EnumVehicleDocStatus.values()) {
			if (pStatus.getStatusId().equals(statusId) ) {
				status = pStatus.getStatus();
				break;
			}
		}
		return status;
	}

	public static boolean isStatusAvailable(Integer statusId) {
		for (EnumVehicleDocStatus pStatus : EnumVehicleDocStatus.values()) {
			if (pStatus.getStatusId().equals(statusId)) {
				return true;
			}
		}
		return false;
	}
}
