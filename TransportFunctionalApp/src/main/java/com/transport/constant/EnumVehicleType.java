package com.transport.constant;

public enum EnumVehicleType {
	SMALL(1, "small"),
	MEDIUM(2, "medium"),
	LARGE(3, "large"),
	LUTON(4, "luton");
	private Integer statusId;
	private String status;

	EnumVehicleType(String status) {
		this.status = status;
	}
	
	EnumVehicleType(Integer statusId, String status) {
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
		for (EnumVehicleType pStatus : EnumVehicleType.values()) {
			if (pStatus.getStatusId().equals(statusId) ) {
				status = pStatus.getStatus();
				break;
			}
		}
		return status;
	}

	public static boolean isStatusAvailable(Integer statusId) {
		for (EnumVehicleType pStatus : EnumVehicleType.values()) {
			if (pStatus.getStatusId().equals(statusId)) {
				return true;
			}
		}
		return false;
	}
}
