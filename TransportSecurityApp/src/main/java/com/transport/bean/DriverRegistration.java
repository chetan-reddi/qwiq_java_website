package com.transport.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.transport.custom.annotations.ValidPassword;

public class DriverRegistration {
	
	private long baseRange;
	@NotNull
	@NotEmpty
	private String firstName;
	@NotNull
	@NotEmpty
	private String surName;
	@NotNull
	@NotEmpty
	@Email
	private String emailId;
	@NotNull
	@NotEmpty
	private String phoneNumber;
	@NotNull
	@NotEmpty
	private String streetAddress1;
	private String streetAddress2;
	@NotNull
	@NotEmpty
	private String city;
	@NotNull
	@NotEmpty
	private String region;
	@NotNull
	@NotEmpty
	private String postCode;
	@NotNull
	@NotEmpty
	private String country;
	@NotNull
	@NotEmpty
	private String vehicleRegNo;
	@NotNull
	@NotEmpty
	private String drivingLicenseNo;
	@NotNull
	@NotEmpty
	private String nationalInsuranceNumber;
	@NotNull
	@NotEmpty
	private String vanSize;
	public long getBaseRange() {
		return baseRange;
	}
	public void setBaseRange(long baseRange) {
		this.baseRange = baseRange;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getStreetAddress1() {
		return streetAddress1;
	}
	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getVehicleRegNo() {
		return vehicleRegNo;
	}
	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}
	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}
	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}
	public String getNationalInsuranceNumber() {
		return nationalInsuranceNumber;
	}
	public void setNationalInsuranceNumber(String nationalInsuranceNumber) {
		this.nationalInsuranceNumber = nationalInsuranceNumber;
	}
	public String getVanSize() {
		return vanSize;
	}
	public void setVanSize(String vanSize) {
		this.vanSize = vanSize;
	}
}
