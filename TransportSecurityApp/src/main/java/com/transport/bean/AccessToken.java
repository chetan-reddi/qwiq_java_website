package com.transport.bean;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.transport.service.impl.AccessTokenPayload;

@Component
public class AccessToken {
	private Map<String,Object> headers;
	private AccessTokenPayload payload;
	private boolean valid = false;

	public Map<String, Object> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, Object> headers) {
		this.headers = headers;
	}
	public AccessTokenPayload getPayload() {
		return payload;
	}
	public void setPayload(AccessTokenPayload payload) {
		this.payload = payload;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccessToken [headers=");
		builder.append(headers);
		builder.append(", payload=");
		builder.append(payload);
		builder.append(", valid=");
		builder.append(valid);
		builder.append("]");
		return builder.toString();
	}
	
}
