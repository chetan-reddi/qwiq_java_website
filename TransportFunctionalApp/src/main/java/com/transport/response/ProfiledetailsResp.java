package com.transport.response;

import com.transport.model.ProfileDetails;

public class ProfiledetailsResp {

	private TransportErrorResp error;
	private ProfileDet result;
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
	public ProfileDet getResult() {
		return result;
	}
	public void setResult(ProfileDet result) {
		this.result = result;
	}
	
}
