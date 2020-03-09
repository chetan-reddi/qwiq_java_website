package com.transport.response;

import org.springframework.stereotype.Component;

@Component
public class SampleResponse {

	private String code;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("AccessToken [headers=");
		builder.append(code);
		builder.append(", payload=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
}
