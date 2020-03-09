package com.transport.response;

import com.transport.bean.KYCDetails;

public class KYCDocsResponse {

	private TransportErrorResp error;
	private KYCDetails result;
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
	public KYCDetails getResult() {
		return result;
	}
	public void setResult(KYCDetails result) {
		this.result = result;
	}
}
