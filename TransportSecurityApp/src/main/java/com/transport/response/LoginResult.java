package com.transport.response;

public class LoginResult {

	private TransportErrorResp errorResp;
	private LoginResponse result;

	public TransportErrorResp getErrorResp() {
		return errorResp;
	}
	public void setErrorResp(TransportErrorResp errorResp) {
		this.errorResp = errorResp;
	}
	public LoginResponse getResult() {
		return result;
	}
	public void setResult(LoginResponse result) {
		this.result = result;
	}
}
