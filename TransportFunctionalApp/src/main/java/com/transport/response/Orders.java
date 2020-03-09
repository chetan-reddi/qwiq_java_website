package com.transport.response;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Orders {

	private String orderId;
	private String formAddress;
	private String deliveryAddress;
	private int vanId;
	private String vanSize;
	private int helpersCount;
	private double distance;
	private String collectionPostCode;
	private String collectionStreetAddres;
	private String collectionCity;
	private String deliveryPostCode;
	private String deliveryStreetAddres;
	private String deliveryCity;
	private int hoursNeeded;
	private Date pickUpDate;
	private Date pickUpTime;
	private String name;
	private String phoneNumber;
	private String orderStatus;
	private double driverCharges;
	private double mileageCharges;
	private double additonalTimeChargesPerHour;
	private double additionalStopCharges;
	private double outOfHourCharges;
	private double additionalCharges;
	private String additionalChargesDesc;
	private double promotionalDiscount;
	private double bookingFee;
	private double totalCost;
	private String vehicleId;
	public double getDriverCharges() {
		return driverCharges;
	}
	public void setDriverCharges(double driverCharges) {
		this.driverCharges = driverCharges;
	}
	public double getMileageCharges() {
		return mileageCharges;
	}
	public void setMileageCharges(double mileageCharges) {
		this.mileageCharges = mileageCharges;
	}
	public double getAdditonalTimeChargesPerHour() {
		return additonalTimeChargesPerHour;
	}
	public void setAdditonalTimeChargesPerHour(double additonalTimeChargesPerHour) {
		this.additonalTimeChargesPerHour = additonalTimeChargesPerHour;
	}
	public double getAdditionalStopCharges() {
		return additionalStopCharges;
	}
	public void setAdditionalStopCharges(double additionalStopCharges) {
		this.additionalStopCharges = additionalStopCharges;
	}
	public double getOutOfHourCharges() {
		return outOfHourCharges;
	}
	public void setOutOfHourCharges(double outOfHourCharges) {
		this.outOfHourCharges = outOfHourCharges;
	}
	public double getAdditionalCharges() {
		return additionalCharges;
	}
	public void setAdditionalCharges(double additionalCharges) {
		this.additionalCharges = additionalCharges;
	}
	public String getAdditionalChargesDesc() {
		return additionalChargesDesc;
	}
	public void setAdditionalChargesDesc(String additionalChargesDesc) {
		this.additionalChargesDesc = additionalChargesDesc;
	}
	public double getPromotionalDiscount() {
		return promotionalDiscount;
	}
	public void setPromotionalDiscount(double promotionalDiscount) {
		this.promotionalDiscount = promotionalDiscount;
	}
	public double getBookingFee() {
		return bookingFee;
	}
	public void setBookingFee(double bookingFee) {
		this.bookingFee = bookingFee;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getPickUpTime() {
		return pickUpTime;
	}
	public void setPickUpTime(Date pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	public String getFormAddress() {
		return formAddress;
	}
	public void setFormAddress(String formAddress) {
		this.formAddress = formAddress;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getVanSize() {
		return vanSize;
	}
	public void setVanSize(String vanSize) {
		this.vanSize = vanSize;
	}
	public int getHelpersCount() {
		return helpersCount;
	}
	public void setHelpersCount(int helpersCount) {
		this.helpersCount = helpersCount;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getCollectionPostCode() {
		return collectionPostCode;
	}
	public void setCollectionPostCode(String collectionPostCode) {
		this.collectionPostCode = collectionPostCode;
	}
	public String getCollectionStreetAddres() {
		return collectionStreetAddres;
	}
	public void setCollectionStreetAddres(String collectionStreetAddres) {
		this.collectionStreetAddres = collectionStreetAddres;
	}
	public String getCollectionCity() {
		return collectionCity;
	}
	public void setCollectionCity(String collectionCity) {
		this.collectionCity = collectionCity;
	}
	public String getDeliveryPostCode() {
		return deliveryPostCode;
	}
	public void setDeliveryPostCode(String deliveryPostCode) {
		this.deliveryPostCode = deliveryPostCode;
	}
	public String getDeliveryStreetAddres() {
		return deliveryStreetAddres;
	}
	public void setDeliveryStreetAddres(String deliveryStreetAddres) {
		this.deliveryStreetAddres = deliveryStreetAddres;
	}
	public String getDeliveryCity() {
		return deliveryCity;
	}
	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}
	public int getHoursNeeded() {
		return hoursNeeded;
	}
	public void setHoursNeeded(int hoursNeeded) {
		this.hoursNeeded = hoursNeeded;
	}
	public Date getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
