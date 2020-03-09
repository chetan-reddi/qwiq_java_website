package com.transport.response;

import java.util.List;

import com.transport.model.VanDetails;

public class VanDetailsResp {

	private TransportErrorResp error;
	private List<VanDetailsList> result;
	public List<VanDetailsList> getResult() {
		return result;
	}
	public void setResult(List<VanDetailsList> result) {
		this.result = result;
	}
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
}
