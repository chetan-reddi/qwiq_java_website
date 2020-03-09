package com.transport.constant;

public enum EnumBidStatus {

	ACTIVE(1, "Active"),
	ACCEPT(2, "Accept"),
	COMPLETED(3,"COMPLETED"),
	CANCELL(3,"Cancell");
	private Integer statusId;
	private String status;

	EnumBidStatus(String status) {
		this.status = status;
	}
	
	EnumBidStatus(Integer statusId, String status) {
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
		for (EnumBidStatus pStatus : EnumBidStatus.values()) {
			if (pStatus.getStatusId().equals(statusId) ) {
				status = pStatus.getStatus();
				break;
			}
		}
		return status;
	}

	public static boolean isStatusAvailable(Integer statusId) {
		for (EnumBidStatus pStatus : EnumBidStatus.values()) {
			if (pStatus.getStatusId().equals(statusId)) {
				return true;
			}
		}
		return false;
	}
}
