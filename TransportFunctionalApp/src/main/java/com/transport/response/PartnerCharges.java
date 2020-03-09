package com.transport.response;

public class PartnerCharges {

	private double distance;
	private String vehicelHoursNeeded;
	private double driverChargePerHour;
	private double chargePerMile;
	private double additionalStopCharges;
	private double additionalTimeCharge;
	private double helperChargePerHour;
	private double outOfWorkingChargesPerHour;
	private double platformDiscount;
	private double platformBookingFee;
	public double getPlatformDiscount() {
		return platformDiscount;
	}
	public void setPlatformDiscount(double platformDiscount) {
		this.platformDiscount = platformDiscount;
	}
	public double getPlatformBookingFee() {
		return platformBookingFee;
	}
	public void setPlatformBookingFee(double platformBookingFee) {
		this.platformBookingFee = platformBookingFee;
	}
	public double getAdditionalStopCharges() {
		return additionalStopCharges;
	}
	public void setAdditionalStopCharges(double additionalStopCharges) {
		this.additionalStopCharges = additionalStopCharges;
	}
	public double getAdditionalTimeCharge() {
		return additionalTimeCharge;
	}
	public void setAdditionalTimeCharge(double additionalTimeCharge) {
		this.additionalTimeCharge = additionalTimeCharge;
	}
	public double getHelperChargePerHour() {
		return helperChargePerHour;
	}
	public void setHelperChargePerHour(double helperChargePerHour) {
		this.helperChargePerHour = helperChargePerHour;
	}
	public double getOutOfWorkingChargesPerHour() {
		return outOfWorkingChargesPerHour;
	}
	public void setOutOfWorkingChargesPerHour(double outOfWorkingChargesPerHour) {
		this.outOfWorkingChargesPerHour = outOfWorkingChargesPerHour;
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
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getVehicelHoursNeeded() {
		return vehicelHoursNeeded;
	}
	public void setVehicelHoursNeeded(String vehicelHoursNeeded) {
		this.vehicelHoursNeeded = vehicelHoursNeeded;
	}
	
}
