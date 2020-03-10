package com.transport.response;

import java.util.List;

import com.transport.model.SaveTicket;

public class TicketResponse {
	private String error;
	private List<SaveTicket> result;
	private String message;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<SaveTicket> getResult() {
		return result;
	}
	public void setResult(List<SaveTicket> result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
