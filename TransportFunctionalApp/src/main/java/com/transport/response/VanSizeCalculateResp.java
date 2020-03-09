package com.transport.response;

public class VanSizeCalculateResp {
	private TransportErrorResp error;
private CalculateResponse result;
public TransportErrorResp getError() {
	return error;
}
public void setError(TransportErrorResp error) {
	this.error = error;
}
public CalculateResponse getResult() {
	return result;
}
public void setResult(CalculateResponse result) {
	this.result = result;
}
}
