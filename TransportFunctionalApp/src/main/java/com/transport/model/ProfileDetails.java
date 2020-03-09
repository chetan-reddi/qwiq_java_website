package com.transport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="driver_profile")
public class ProfileDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "helper_charge_per_hour")
	private double helperChargePerHour;
	@Column(name = "additional_stop_charge")
	private double additionalStopCharge;
	@Column(name = "additional_time_charge_per_hour")
	private double additionalTimeChargePerHour;
	@Column(name = "out_of_working_hours_charges_per_hour")
	private double outOfWorkingChargePperhour;
	@Column(name="rating")
	private String rating;
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public double getHelperChargePerHour() {
		return helperChargePerHour;
	}
	public void setHelperChargePerHour(double helperChargePerHour) {
		this.helperChargePerHour = helperChargePerHour;
	}
	public double getAdditionalStopCharge() {
		return additionalStopCharge;
	}
	public void setAdditionalStopCharge(double additionalStopCharge) {
		this.additionalStopCharge = additionalStopCharge;
	}
	public double getAdditionalTimeChargePerHour() {
		return additionalTimeChargePerHour;
	}
	public void setAdditionalTimeChargePerHour(double additionalTimeChargePerHour) {
		this.additionalTimeChargePerHour = additionalTimeChargePerHour;
	}
	public double getOutOfWorkingChargePperhour() {
		return outOfWorkingChargePperhour;
	}
	public void setOutOfWorkingChargePperhour(double outOfWorkingChargePperhour) {
		this.outOfWorkingChargePperhour = outOfWorkingChargePperhour;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
