package com.transport.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class VehicleStatus {
@NotNull
@NotEmpty
private String vehilceId;
private int status;
public String getVehilceId() {
	return vehilceId;
}
public void setVehilceId(String vehilceId) {
	this.vehilceId = vehilceId;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
}
