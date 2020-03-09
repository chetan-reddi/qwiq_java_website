package com.transport.constant;

public enum EnumPaymentStatus {

	ALL(0,"All"),
	PENDING(1, "Pending"),
	CLEARED(2, "Cleared");
	private Integer statusId;
	private String status;

	EnumPaymentStatus(String status) {
		this.status = status;
	}
	
	EnumPaymentStatus(Integer statusId, String status) {
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
		for (EnumPaymentStatus pStatus : EnumPaymentStatus.values()) {
			if (pStatus.getStatusId().equals(statusId) ) {
				status = pStatus.getStatus();
				break;
			}
		}
		return status;
	}

	public static boolean isStatusAvailable(Integer statusId) {
		for (EnumPaymentStatus pStatus : EnumPaymentStatus.values()) {
			if (pStatus.getStatusId().equals(statusId)) {
				return true;
			}
		}
		return false;
	}
}
