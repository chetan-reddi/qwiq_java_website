package com.transport.response;

import java.util.List;

import com.transport.model.Bid;

public class ActiveBidsResponse {

	private TransportErrorResp error;
	private List<MyBid> result;
	public List<MyBid> getResult() {
		return result;
	}
	public void setResult(List<MyBid> result) {
		this.result = result;
	}
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
}
