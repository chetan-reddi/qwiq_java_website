package com.transport.response;

import java.util.List;

public class PartnerVehResp {
private TransportErrorResp error;
private List<PartnerVehicle> result;
public TransportErrorResp getError() {
	return error;
}
public void setError(TransportErrorResp error) {
	this.error = error;
}
public List<PartnerVehicle> getResult() {
	return result;
}
public void setResult(List<PartnerVehicle> result) {
	this.result = result;
}
}
