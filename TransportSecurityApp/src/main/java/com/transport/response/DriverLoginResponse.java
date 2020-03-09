package com.transport.response;

public class DriverLoginResponse {

	private TransportErrorResp error;
	private TransportResult result;
	private LoginResponse loginResponse;
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
	public LoginResponse getLoginResponse() {
		return loginResponse;
	}
	public void setLoginResponse(LoginResponse loginResponse) {
		this.loginResponse = loginResponse;
	}
}
