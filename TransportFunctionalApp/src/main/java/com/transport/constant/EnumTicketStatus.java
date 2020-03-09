package com.transport.constant;

public enum EnumTicketStatus {

	PLACED(1, "Placed"),
	ASSIGNED(2, "Assigned"),
	RESOLVED(5,"Expired");
	private Integer statusId;
	private String status;

	EnumTicketStatus(String status) {
		this.status = status;
	}
	
	EnumTicketStatus(Integer statusId, String status) {
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
		for (EnumTicketStatus pStatus : EnumTicketStatus.values()) {
			if (pStatus.getStatusId().equals(statusId) ) {
				status = pStatus.getStatus();
				break;
			}
		}
		return status;
	}

	public static boolean isStatusAvailable(Integer statusId) {
		for (EnumTicketStatus pStatus : EnumTicketStatus.values()) {
			if (pStatus.getStatusId().equals(statusId)) {
				return true;
			}
		}
		return false;
	}
}
