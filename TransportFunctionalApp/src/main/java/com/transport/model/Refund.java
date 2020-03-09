package com.transport.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="refund")
public class Refund {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	@Column(name = "order_id")
    private String orderId;
	@Column(name = "cancellation_id")
    private String cancellationId;
	@Column(name = "amount")
    private double amount;
	@Column(name = "created_date")
    private Date cancelledDate;
	@Column(name = "refund_date")
    private Date refundDate;
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCancellationId() {
		return cancellationId;
	}
	public void setCancellationId(String cancellationId) {
		this.cancellationId = cancellationId;
	}
	public Date getCancelledDate() {
		return cancelledDate;
	}
	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
	}
	public Date getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
	
}
