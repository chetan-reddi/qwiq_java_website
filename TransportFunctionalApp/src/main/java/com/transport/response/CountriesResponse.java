package com.transport.response;

import java.util.List;

import com.transport.model.Countries;

public class CountriesResponse {

	private TransportErrorResp error;
	private Iterable<Countries> result;
	public Iterable<Countries> getResult() {
		return result;
	}
	public void setResult(Iterable<Countries> result) {
		this.result = result;
	}
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
	
}
