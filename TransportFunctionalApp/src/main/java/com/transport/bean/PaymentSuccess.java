package com.transport.bean;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class PaymentSuccess {

	@NotNull
	@NotEmpty
	private String orderId;
	@NotNull
	@NotEmpty
	private String transactionId;
	@NotNull
	@NotEmpty
	private double amount;
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
}
 