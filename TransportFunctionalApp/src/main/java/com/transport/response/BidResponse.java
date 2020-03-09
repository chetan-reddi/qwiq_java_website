package com.transport.response;

import java.util.List;

import com.transport.model.Bid;

public class BidResponse {

	private TransportErrorResp error;
	private List<Bid> result;
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
	public List<Bid> getResult() {
		return result;
	}
	public void setResult(List<Bid> result) {
		this.result = result;
	}
}
