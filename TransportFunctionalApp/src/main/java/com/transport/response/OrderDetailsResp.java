package com.transport.response;

import java.util.List;

public class OrderDetailsResp {

	private TransportErrorResp error;
	private List<Orders> result;
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
	public List<Orders> getResult() {
		return result;
	}
	public void setResult(List<Orders> result) {
		this.result = result;
	}
}
