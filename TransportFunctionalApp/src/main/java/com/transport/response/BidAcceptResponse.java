package com.transport.response;

public class BidAcceptResponse {
	private TransportErrorResp error;
	private TransportResult result;
	private String orderId;
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
	public TransportResult getResult() {
		return result;
	}
	public void setResult(TransportResult result) {
		this.result = result;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
