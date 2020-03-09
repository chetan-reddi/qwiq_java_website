package com.transport.response;

import java.util.Date;

public class ConfirmedOrder {
 private String orderId;
 private String fromAddress;
 private String toAddres;
 private Date moveDate;
 private String bidAmount;
 private String pickUpTime;
 private String phoneNumber;
 private String status;
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getPickUpTime() {
	return pickUpTime;
}
public void setPickUpTime(String pickUpTime) {
	this.pickUpTime = pickUpTime;
}
public Date getMoveDate() {
	return moveDate;
}
public void setMoveDate(Date moveDate) {
	this.moveDate = moveDate;
}
public String getOrderId() {
	return orderId;
}
public void setOrderId(String orderId) {
	this.orderId = orderId;
}
public String getFromAddress() {
	return fromAddress;
}
public void setFromAddress(String fromAddress) {
	this.fromAddress = fromAddress;
}
public String getToAddres() {
	return toAddres;
}
public void setToAddres(String toAddres) {
	this.toAddres = toAddres;
}

public String getBidAmount() {
	return bidAmount;
}
public void setBidAmount(String bidAmount) {
	this.bidAmount = bidAmount;
}
}
