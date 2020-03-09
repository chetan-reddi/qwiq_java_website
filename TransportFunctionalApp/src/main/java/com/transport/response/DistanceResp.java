package com.transport.response;

public class DistanceResp {

	private TransportErrorResp error;
	private DistanceAndHours result;
	public DistanceAndHours getResult() {
		return result;
	}
	public void setResult(DistanceAndHours result) {
		this.result = result;
	}
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}

	
}
