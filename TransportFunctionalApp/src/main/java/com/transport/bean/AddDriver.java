package com.transport.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="drivers")
public class AddDriver {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	@Column(name="owner_id")
	private String ownerId;
	@Column(name="driver_id")
	private String driverId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "sur_name")
	private String surName;
	@Column(name = "email")
	private String email;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "driving_license_number")
	private String drivingLicenseNumber;
	@Column(name = "street_address1")
	private String streetAddress;
	@Column(name = "street_address2")
	private String streetAddress2;
	@Column(name = "city")
	private String city;
	@Column(name = "region")
	private String region;
	@Column(name = "post_code")
	private String postCode;
	@Column(name = "country")
	private String country;
	@Column(name = "driving_license_image")
	private String drivingLicenseImage;
	@Column(name="status")
	private int status;
	@Column(name="operation_status")
	private int operationStatus;
	public int getOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(int operationStatus) {
		this.operationStatus = operationStatus;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}
	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}
	public String getDrivingLicenseImage() {
		return drivingLicenseImage;
	}
	public void setDrivingLicenseImage(String drivingLicenseImage) {
		this.drivingLicenseImage = drivingLicenseImage;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
}
