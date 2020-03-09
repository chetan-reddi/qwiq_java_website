package com.transport.constant;

public enum EnumAccountStatus {
	ACTIVATION_PENDING(1, "Activation_peding"),
	ACTIVE(2, "Active"),
	SUSPEND(3, "Suspend");
	private Integer statusId;
	private String status;

	EnumAccountStatus(String status) {
		this.status = status;
	}
	
	EnumAccountStatus(Integer statusId, String status) {
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
		for (EnumAccountStatus pStatus : EnumAccountStatus.values()) {
			if (pStatus.getStatusId().equals(statusId) ) {
				status = pStatus.getStatus();
				break;
			}
		}
		return status;
	}

	public static boolean isStatusAvailable(Integer statusId) {
		for (EnumAccountStatus pStatus : EnumAccountStatus.values()) {
			if (pStatus.getStatusId().equals(statusId)) {
				return true;
			}
		}
		return false;
	}
}
