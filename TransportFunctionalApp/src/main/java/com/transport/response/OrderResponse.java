package com.transport.response;

import com.transport.bean.OrderDetails;

public class OrderResponse {
	private TransportResult result;
	private TransportErrorResp errorResp;
	private OrderDetails orderDetails;
	
	public OrderDetails getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}
	public TransportResult getResult() {
		return result;
	}
	public void setResult(TransportResult result) {
		this.result = result;
	}
	
	public TransportErrorResp getErrorResp() {
		return errorResp;
	}
	public void setErrorResp(TransportErrorResp errorResp) {
		this.errorResp = errorResp;
	}
	
}
