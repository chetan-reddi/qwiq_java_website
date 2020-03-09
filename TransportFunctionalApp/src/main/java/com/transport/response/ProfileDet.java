package com.transport.response;

import javax.persistence.Column;

public class ProfileDet {
private String firstName;
private String lastName;
private String phoneNumber;
private double helperChargePerHour;
private double additionalStopCharge;
private double additionalTimeChargePerHour;
private double outOfWorkingChargePperhour;

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
