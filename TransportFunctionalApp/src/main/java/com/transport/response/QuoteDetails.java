package com.transport.response;

import java.util.Date;

public class QuoteDetails {
	private String quoteId;
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
	private String pickUpTime;
	private String name;
	private String phoneNumber;
	private double estimateValue;
	private String quoteStatus;
	private double price;
	private double discountAmount;
	private double bookingFee;
	private double totalPrice;
	
	public String getPickUpTime() {
		return pickUpTime;
	}
	public void setPickUpTime(String pickUpTime) {
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
	public int getVanId() {
		return vanId;
	}
	public void setVanId(int vanId) {
		this.vanId = vanId;
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
	public double getEstimateValue() {
		return estimateValue;
	}
	public void setEstimateValue(double estimateValue) {
		this.estimateValue = estimateValue;
	}
	
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public String getQuoteStatus() {
		return quoteStatus;
	}
	public void setQuoteStatus(String quoteStatus) {
		this.quoteStatus = quoteStatus;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public double getBookingFee() {
		return bookingFee;
	}
	public void setBookingFee(double bookingFee) {
		this.bookingFee = bookingFee;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
