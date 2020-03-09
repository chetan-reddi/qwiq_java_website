package com.transport.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name="orders")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	@Column(name="user_ref_id")
	private String userRefId;
	@Column(name="partner_id")
	private String partnerId;
	@Column(name="order_id")
	private String orderId;
	@Column(name="total_amount")
	private double totalAmount;
	@Column(name="bid_id")
	private String bidId;
	@Column(name="status")
	private int status;
	@Column(name="paid_amount")
	private double paidAmount;
	@Column(name="paid_transaction_date")
	private Date padiTransactionDate;
	@Column(name="advance_amount")
	private double advanceAmount;
	@Column(name="reason")
	private String reason;
	@Column(name="cancellation_id")
	private String cancellationId;
	
	public String getCancellationId() {
		return cancellationId;
	}
	public void setCancellationId(String cancellationId) {
		this.cancellationId = cancellationId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public double getAdvanceAmount() {
		return advanceAmount;
	}
	public void setAdvanceAmount(double advanceAmount) {
		this.advanceAmount = advanceAmount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Date getPadiTransactionDate() {
		return padiTransactionDate;
	}
	public void setPadiTransactionDate(Date padiTransactionDate) {
		this.padiTransactionDate = padiTransactionDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserRefId() {
		return userRefId;
	}
	public void setUserRefId(String userRefId) {
		this.userRefId = userRefId;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBidId() {
		return bidId;
	}
	public void setBidId(String bidId) {
		this.bidId = bidId;
	}
	
	
}
