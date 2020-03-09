package com.transport.constant;

public enum EnumUserRole {

	
	CUSTOMER(1, "Customer"),
	DRIVER(2, "Driver");
	private Integer statusId;
	private String status;

	EnumUserRole(String status) {
		this.status = status;
	}
	
	EnumUserRole(Integer statusId, String status) {
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
		for (EnumUserRole pStatus : EnumUserRole.values()) {
			if (pStatus.getStatusId().equals(statusId) ) {
				status = pStatus.getStatus();
				break;
			}
		}
		return status;
	}

	public static boolean isStatusAvailable(Integer statusId) {
		for (EnumUserRole pStatus : EnumUserRole.values()) {
			if (pStatus.getStatusId().equals(statusId)) {
				return true;
			}
		}
		return false;
	}
}
