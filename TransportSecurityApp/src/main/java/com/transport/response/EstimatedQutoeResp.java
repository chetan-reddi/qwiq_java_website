package com.transport.response;

import java.util.Date;

public class EstimatedQutoeResp {

	private String error;
	private String estimatedPrice;
	private String distance;
	private String mileageCharges;
	private String driverCharges;
	private String helpersCharges;
	private String hoursNeededVehicle;
	private Date pickUpDate;
	private String pickUpTime;
	private String bookingFee;
	private String discount;
	private String totalPrice;

	public String getMileageCharges() {
		return mileageCharges;
	}
	public void setMileageCharges(String mileageCharges) {
		this.mileageCharges = mileageCharges;
	}
	public String getDriverCharges() {
		return driverCharges;
	}
	public void setDriverCharges(String driverCharges) {
		this.driverCharges = driverCharges;
	}
	public String getHelpersCharges() {
		return helpersCharges;
	}
	public void setHelpersCharges(String helpersCharges) {
		this.helpersCharges = helpersCharges;
	}
	public String getHoursNeededVehicle() {
		return hoursNeededVehicle;
	}
	public void setHoursNeededVehicle(String hoursNeededVehicle) {
		this.hoursNeededVehicle = hoursNeededVehicle;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getEstimatedPrice() {
		return estimatedPrice;
	}
	public void setEstimatedPrice(String estimatedPrice) {
		this.estimatedPrice = estimatedPrice;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public Date getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}
	public String getPickUpTime() {
		return pickUpTime;
	}
	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	public String getBookingFee() {
		return bookingFee;
	}
	public void setBookingFee(String bookingFee) {
		this.bookingFee = bookingFee;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

}
