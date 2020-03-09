package com.transport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="driver_profile")
public class DriverProfileDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	@Column(name="user_ref_id")
	private String userId;
	@Column(name="base_range")
	private long baseRange;
	@Column(name="first_name")
	private String firstName;
	@Column(name="sur_name")
	private String surName;
	@Column(name="phone_number")
	private String phoneNumber;
	@Column(name="street_address1")
	private String streetAddress1;
	@Column(name="street_address2")
	private String streetAddress2;
	@Column(name="city")
	private String city;
	@Column(name="region")
	private String region;
	@Column(name="post_code")
	private String postCode;
	@Column(name="country")
	private String country;
	@Column(name="vehicle_reg_no")
	private String vehicleRegNo;
	@Column(name="driving_license_no")
	private String drivingLicenseNo;
	@Column(name="national_insurance_number")
	private String nationalInsuranceNumber;
	@Column(name="van_size")
	private String vanSize;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
