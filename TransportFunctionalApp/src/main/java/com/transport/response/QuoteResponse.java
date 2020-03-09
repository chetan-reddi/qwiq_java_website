package com.transport.response;

import java.util.List;

public class QuoteResponse {

	private TransportErrorResp error;
	private List<QuoteDetails> result;
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
	public List<QuoteDetails> getResult() {
		return result;
	}
	public void setResult(List<QuoteDetails> result) {
		this.result = result;
	}
}
