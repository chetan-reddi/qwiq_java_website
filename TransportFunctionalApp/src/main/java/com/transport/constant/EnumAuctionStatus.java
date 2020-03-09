package com.transport.constant;

public enum EnumAuctionStatus {
	OPEN(1, "open"),
	CLOSED(2, "closed");
	private Integer statusId;
	private String status;

	EnumAuctionStatus(String status) {
		this.status = status;
	}
	
	EnumAuctionStatus(Integer statusId, String status) {
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
		for (EnumAuctionStatus pStatus : EnumAuctionStatus.values()) {
			if (pStatus.getStatusId().equals(statusId) ) {
				status = pStatus.getStatus();
				break;
			}
		}
		return status;
	}

	public static boolean isStatusAvailable(Integer statusId) {
		for (EnumAuctionStatus pStatus : EnumAuctionStatus.values()) {
			if (pStatus.getStatusId().equals(statusId)) {
				return true;
			}
		}
		return false;
	}
}
