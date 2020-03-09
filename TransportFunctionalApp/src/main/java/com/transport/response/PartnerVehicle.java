package com.transport.response;

public class PartnerVehicle {

	private String vehicleId;
	private int vanId;
	private String vanType;
	private String vehicleRegNo;
	private String status;
	private String operationalStatus;
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public int getVanId() {
		return vanId;
	}
	public void setVanId(int vanId) {
		this.vanId = vanId;
	}
	public String getVanType() {
		return vanType;
	}
	public void setVanType(String vanType) {
		this.vanType = vanType;
	}
	public String getVehicleRegNo() {
		return vehicleRegNo;
	}
	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOperationalStatus() {
		return operationalStatus;
	}
	public void setOperationalStatus(String operationalStatus) {
		this.operationalStatus = operationalStatus;
	}
}
