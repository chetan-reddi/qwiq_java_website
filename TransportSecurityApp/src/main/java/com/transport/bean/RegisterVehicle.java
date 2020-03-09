package com.transport.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
public class RegisterVehicle {
	
	private long baseRange;
	@NotNull
	@NotEmpty
	private String vehicleRegNo;
	private String baseArea;
	private double latittude;
	private double longitude;
	private String vehicleId;
	private String nationalInsuranceNumber;
	private int vanSize;
	private double driverChargePerHour;
	private double chargePerMile;
	public String getNationalInsuranceNumber() {
		return nationalInsuranceNumber;
	}
	public void setNationalInsuranceNumber(String nationalInsuranceNumber) {
		this.nationalInsuranceNumber = nationalInsuranceNumber;
	}
	public double getDriverChargePerHour() {
		return driverChargePerHour;
	}
	public void setDriverChargePerHour(double driverChargePerHour) {
		this.driverChargePerHour = driverChargePerHour;
	}
	public double getChargePerMile() {
		return chargePerMile;
	}
	public void setChargePerMile(double chargePerMile) {
		this.chargePerMile = chargePerMile;
	}
	public String getBaseArea() {
		return baseArea;
	}
	public void setBaseArea(String baseArea) {
		this.baseArea = baseArea;
	}
	public double getLatittude() {
		return latittude;
	}
	public void setLatittude(double latittude) {
		this.latittude = latittude;
	}
	public long getBaseRange() {
		return baseRange;
	}
	public void setBaseRange(long baseRange) {
		this.baseRange = baseRange;
	}
	public String getVehicleRegNo() {
		return vehicleRegNo;
	}
	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public int getVanSize() {
		return vanSize;
	}
	public void setVanSize(int vanSize) {
		this.vanSize = vanSize;
	}
	
}
