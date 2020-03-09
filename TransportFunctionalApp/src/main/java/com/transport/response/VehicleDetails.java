package com.transport.response;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class VehicleDetails {

	private long baseRange;
	private String baseArea;
	private String vehicleRegNo;
	private String nationalInsuranceNumber;
	private String vanImage;
	private String motorInsurance;
	private String goodsTransitCover;
	private String liabilityInsurance;
	private String vehicleId;
	private String vehicleType;
	private String driverName;
	private String driverNumber;
	private String vehicleStatus;
	private String vehicleOperationStatus;
	private double driverChargesPerHour;
	private double chargePerMile;
	private String path;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public double getDriverChargesPerHour() {
		return driverChargesPerHour;
	}
	public void setDriverChargesPerHour(double driverChargesPerHour) {
		this.driverChargesPerHour = driverChargesPerHour;
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
	public String getVehicleStatus() {
		return vehicleStatus;
	}
	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}
	public String getVehicleOperationStatus() {
		return vehicleOperationStatus;
	}
	public void setVehicleOperationStatus(String vehicleOperationStatus) {
		this.vehicleOperationStatus = vehicleOperationStatus;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverNumber() {
		return driverNumber;
	}
	public void setDriverNumber(String driverNumber) {
		this.driverNumber = driverNumber;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	private String status;
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
	public String getNationalInsuranceNumber() {
		return nationalInsuranceNumber;
	}
	public void setNationalInsuranceNumber(String nationalInsuranceNumber) {
		this.nationalInsuranceNumber = nationalInsuranceNumber;
	}
	public String getVanImage() {
		return vanImage;
	}
	public void setVanImage(String vanImage) {
		this.vanImage = vanImage;
	}
	
	public String getMotorInsurance() {
		return motorInsurance;
	}
	public void setMotorInsurance(String motorInsurance) {
		this.motorInsurance = motorInsurance;
	}
	public String getGoodsTransitCover() {
		return goodsTransitCover;
	}
	public void setGoodsTransitCover(String goodsTransitCover) {
		this.goodsTransitCover = goodsTransitCover;
	}
	public String getLiabilityInsurance() {
		return liabilityInsurance;
	}
	public void setLiabilityInsurance(String liabilityInsurance) {
		this.liabilityInsurance = liabilityInsurance;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
