package com.transport.response;

import com.transport.model.AddItem;

public class ItemResponse {
private TransportErrorResp response;
private Iterable<AddItem> result;
public TransportErrorResp getResponse() {
	return response;
}
public void setResponse(TransportErrorResp response) {
	this.response = response;
}
public Iterable<AddItem> getResult() {
	return result;
}
public void setResult(Iterable<AddItem> result) {
	this.result = result;
}
}
