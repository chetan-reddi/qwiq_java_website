package com.transport.response;

public class OTPResponse {

	private TransportErrorResp errorResp;
	private LoginResponse loginResponse;
	private TransportResult result;
	public TransportErrorResp getErrorResp() {
		return errorResp;
	}
	public void setErrorResp(TransportErrorResp errorResp) {
		this.errorResp = errorResp;
	}
	public LoginResponse getLoginResponse() {
		return loginResponse;
	}
	public void setLoginResponse(LoginResponse loginResponse) {
		this.loginResponse = loginResponse;
	}
	public TransportResult getResult() {
		return result;
	}
	public void setResult(TransportResult result) {
		this.result = result;
	}
}
