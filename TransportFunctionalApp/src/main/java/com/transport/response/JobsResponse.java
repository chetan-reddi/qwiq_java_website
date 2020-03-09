package com.transport.response;

import java.util.List;

import com.transport.bean.OrderDetails;

public class JobsResponse {

	private TransportErrorResp error;
	private List<OrderDetails> result;
	public List<OrderDetails> getResult() {
		return result;
	}
	public void setResult(List<OrderDetails> result) {
		this.result = result;
	}
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
}
