package com.transport.response;

import com.transport.model.AddBlog;

public class BlogResponse {

	private TransportErrorResp error;
	private Iterable<AddBlog> result;
	public TransportErrorResp getError() {
		return error;
	}
	public void setError(TransportErrorResp error) {
		this.error = error;
	}
	public Iterable<AddBlog> getResult() {
		return result;
	}
	public void setResult(Iterable<AddBlog> result) {
		this.result = result;
	}
}
