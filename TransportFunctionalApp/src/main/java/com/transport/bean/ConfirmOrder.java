package com.transport.bean;

public class ConfirmOrder {

	private String orderId;
	private String otp;
	private double additionalCharges;
	private String description;
	private double totalAmount;
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public double getAdditionalCharges() {
		return additionalCharges;
	}
	public void setAdditionalCharges(double additionalCharges) {
		this.additionalCharges = additionalCharges;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
