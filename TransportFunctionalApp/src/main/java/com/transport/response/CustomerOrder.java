package com.transport.response;

public class CustomerOrder {
	private TransportErrorResp response;
	private Orders result;
	public TransportErrorResp getResponse() {
		return response;
	}
	public void setResponse(TransportErrorResp response) {
		this.response = response;
	}
	public Orders getResult() {
		return result;
	}
	public void setResult(Orders result) {
		this.result = result;
	}
}
