package com.transport.constant;

public enum EnumQuoteStatus {

	PLACED(1, "Placed"),
	ASSIGNED(2, "Assigned"),
	CANCELLED_BY_CUSTOMER(3,"Cancelled_by_customer"),
	CANCELLED_BY_ADMIN(4,"Cancelled_by_admin"),
	EXPIRED(5,"Expired");
	private Integer statusId;
	private String status;

	EnumQuoteStatus(String status) {
		this.status = status;
	}
	
	EnumQuoteStatus(Integer statusId, String status) {
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
		for (EnumQuoteStatus pStatus : EnumQuoteStatus.values()) {
			if (pStatus.getStatusId().equals(statusId) ) {
				status = pStatus.getStatus();
				break;
			}
		}
		return status;
	}

	public static boolean isStatusAvailable(Integer statusId) {
		for (EnumQuoteStatus pStatus : EnumQuoteStatus.values()) {
			if (pStatus.getStatusId().equals(statusId)) {
				return true;
			}
		}
		return false;
	}
}
