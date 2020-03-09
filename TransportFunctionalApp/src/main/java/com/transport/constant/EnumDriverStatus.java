package com.transport.constant;

public enum EnumDriverStatus {

	NOT_APPROVED(1, "Not Approved"),
	APPROVED(2, "Approved"),
	REJECTED(3, "Rejected");
	private Integer statusId;
	private String status;

	EnumDriverStatus(String status) {
		this.status = status;
	}
	
	EnumDriverStatus(Integer statusId, String status) {
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
		for (EnumDriverStatus pStatus : EnumDriverStatus.values()) {
			if (pStatus.getStatusId().equals(statusId) ) {
				status = pStatus.getStatus();
				break;
			}
		}
		return status;
	}

	public static boolean isStatusAvailable(Integer statusId) {
		for (EnumDriverStatus pStatus : EnumDriverStatus.values()) {
			if (pStatus.getStatusId().equals(statusId)) {
				return true;
			}
		}
		return false;
	}
}
