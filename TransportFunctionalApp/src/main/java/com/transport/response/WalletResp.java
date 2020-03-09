package com.transport.response;

import java.util.List;

import com.transport.model.PartnerWallet;

public class WalletResp {

	private TransportErrorResp error;
	private List<PartnerWallet> result;
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
	public List<PartnerWallet> getResult() {
		return result;
	}
	public void setResult(List<PartnerWallet> result) {
		this.result = result;
	}
}
