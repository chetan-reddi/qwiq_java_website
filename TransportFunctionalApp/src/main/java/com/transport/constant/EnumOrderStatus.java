package com.transport.constant;

public enum EnumOrderStatus {

	INITIATED(0,"Initiated"),
	CONFIRMED(1, "Confirmed"),
	CANCELLED_BY_USER(2, "Cancelled_By_User"),
	CANCELLED_BY_ADMIN(6, "Cancelled_By_Admin"),
	TRANSIT(3,"Transit"),
	COMPLETED(4,"Completed"),
	CONFLICT(5,"Conflict");
	private Integer statusId;
	private String status;

	EnumOrderStatus(String status) {
		this.status = status;
	}
	
	EnumOrderStatus(Integer statusId, String status) {
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
		for (EnumOrderStatus pStatus : EnumOrderStatus.values()) {
			if (pStatus.getStatusId().equals(statusId) ) {
				status = pStatus.getStatus();
				break;
			}
		}
		return status;
	}

	public static boolean isStatusAvailable(Integer statusId) {
		for (EnumOrderStatus pStatus : EnumOrderStatus.values()) {
			if (pStatus.getStatusId().equals(statusId)) {
				return true;
			}
		}
		return false;
	}
}
