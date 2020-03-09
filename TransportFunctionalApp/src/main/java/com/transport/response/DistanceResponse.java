package com.transport.response;

public class DistanceResponse {

	private String error;
	private String time;
	private int timeInseconds;
	private double distance;
	private double mileageCharges;
	private double driverCharges;
	private double helpersCharges;
	private String hoursNeededVehicle;
	private double price;
	private double bookingFee; 
	private double discount;
	private double totalPrice;
	public int getTimeInseconds() {
		return timeInseconds;
	}
	public void setTimeInseconds(int timeInseconds) {
		this.timeInseconds = timeInseconds;
	}
	public double getMileageCharges() {
		return mileageCharges;
	}
	public void setMileageCharges(double mileageCharges) {
		this.mileageCharges = mileageCharges;
	}
	public double getDriverCharges() {
		return driverCharges;
	}
	public void setDriverCharges(double driverCharges) {
		this.driverCharges = driverCharges;
	}
	public double getHelpersCharges() {
		return helpersCharges;
	}
	public void setHelpersCharges(double helpersCharges) {
		this.helpersCharges = helpersCharges;
	}
	public String getHoursNeededVehicle() {
		return hoursNeededVehicle;
	}
	public void setHoursNeededVehicle(String hoursNeededVehicle) {
		this.hoursNeededVehicle = hoursNeededVehicle;
	}
	public double getBookingFee() {
		return bookingFee;
	}
	public void setBookingFee(double bookingFee) {
		this.bookingFee = bookingFee;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
