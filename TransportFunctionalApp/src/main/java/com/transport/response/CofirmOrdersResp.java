package com.transport.response;

import java.util.List;

public class CofirmOrdersResp {

	private TransportErrorResp error;
	private List<ConfirmedOrder> result;
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
	public List<ConfirmedOrder> getResult() {
		return result;
	}
	public void setResult(List<ConfirmedOrder> result) {
		this.result = result;
	}
}
