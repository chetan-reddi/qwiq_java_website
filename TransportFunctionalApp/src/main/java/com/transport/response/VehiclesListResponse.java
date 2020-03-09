package com.transport.response;

import java.util.List;

import com.transport.model.VehicleRegistration;

public class VehiclesListResponse {

	private TransportErrorResp error;
	private List<VehicleDetails> result;
	public List<VehicleDetails> getResult() {
		return result;
	}
	public void setResult(List<VehicleDetails> result) {
		this.result = result;
	}
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
}
