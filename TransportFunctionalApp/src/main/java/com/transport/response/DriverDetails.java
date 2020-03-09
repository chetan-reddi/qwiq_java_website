package com.transport.response;

import javax.persistence.Column;

public class DriverDetails {

	private String driverId;
	private String firstName;
	private String surName;
	private String email;
	private String phoneNumber;
	private String drivingLicenseNumber;
	private String streetAddress;
	private String streetAddress2;
	private String city;
	private String region;
	private String postCode;
	private String drivingLicenseImage;
	private String status;
	private String driverOperationStatus;
	public String getDriverOperationStatus() {
		return driverOperationStatus;
	}
	public void setDriverOperationStatus(String driverOperationStatus) {
		this.driverOperationStatus = driverOperationStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}
	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getStreetAddress2() {
		return streetAddress2;
	}
	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getDrivingLicenseImage() {
		return drivingLicenseImage;
	}
	public void setDrivingLicenseImage(String drivingLicenseImage) {
		this.drivingLicenseImage = drivingLicenseImage;
	}
}
