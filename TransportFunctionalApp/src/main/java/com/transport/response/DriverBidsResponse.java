package com.transport.response;

import java.util.List;

public class DriverBidsResponse {

	private TransportErrorResp error;
	private List<DriverBids> result;
	public List<DriverBids> getResult() {
		return result;
	}
	public void setResult(List<DriverBids> result) {
		this.result = result;
	}
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
}
