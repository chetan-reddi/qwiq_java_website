package com.transport.response;

public class TransportResponse {

	private TransportErrorResp errorResp;
	private TransportResult result;
	public TransportErrorResp getErrorResp() {
		return errorResp;
	}
	public void setErrorResp(TransportErrorResp errorResp) {
		this.errorResp = errorResp;
	}
	public TransportResult getResult() {
		return result;
	}
	public void setResult(TransportResult result) {
		this.result = result;
	}
}
