package com.transport.response;

public class DriverDetailsListResp {
	private TransportErrorResp error;
	private DriverDetails result;
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
	public DriverDetails getResult() {
		return result;
	}
	public void setResult(DriverDetails result) {
		this.result = result;
	}
}
