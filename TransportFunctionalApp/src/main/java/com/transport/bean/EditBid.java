package com.transport.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class EditBid {

	private String bidId;
    private String driverUserId;
    private String quoteId;
	private String bidAmount;
	private String comment;
	private int status;
	private double driverCharges;
	private double mileageCharges;
	private double additonalTimeChargesPerHour;
	private double additionalStopCharges;
	private double outOfHourCharges;
	private double additionalCharges;
	private String additionalChargesDesc;
	private double promotionalDiscount;
	private double bookingFee;
	private double totalCost;
	private String vehicleId;
	public String getBidId() {
		return bidId;
	}
	public void setBidId(String bidId) {
		this.bidId = bidId;
	}
	public String getDriverUserId() {
		return driverUserId;
	}
	public void setDriverUserId(String driverUserId) {
		this.driverUserId = driverUserId;
	}
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public String getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(String bidAmount) {
		this.bidAmount = bidAmount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getDriverCharges() {
		return driverCharges;
	}
	public void setDriverCharges(double driverCharges) {
		this.driverCharges = driverCharges;
	}
	public double getMileageCharges() {
		return mileageCharges;
	}
	public void setMileageCharges(double mileageCharges) {
		this.mileageCharges = mileageCharges;
	}
	public double getAdditonalTimeChargesPerHour() {
		return additonalTimeChargesPerHour;
	}
	public void setAdditonalTimeChargesPerHour(double additonalTimeChargesPerHour) {
		this.additonalTimeChargesPerHour = additonalTimeChargesPerHour;
	}
	public double getAdditionalStopCharges() {
		return additionalStopCharges;
	}
	public void setAdditionalStopCharges(double additionalStopCharges) {
		this.additionalStopCharges = additionalStopCharges;
	}
	public double getOutOfHourCharges() {
		return outOfHourCharges;
	}
	public void setOutOfHourCharges(double outOfHourCharges) {
		this.outOfHourCharges = outOfHourCharges;
	}
	public double getAdditionalCharges() {
		return additionalCharges;
	}
	public void setAdditionalCharges(double additionalCharges) {
		this.additionalCharges = additionalCharges;
	}
	public String getAdditionalChargesDesc() {
		return additionalChargesDesc;
	}
	public void setAdditionalChargesDesc(String additionalChargesDesc) {
		this.additionalChargesDesc = additionalChargesDesc;
	}
	public double getPromotionalDiscount() {
		return promotionalDiscount;
	}
	public void setPromotionalDiscount(double promotionalDiscount) {
		this.promotionalDiscount = promotionalDiscount;
	}
	public double getBookingFee() {
		return bookingFee;
	}
	public void setBookingFee(double bookingFee) {
		this.bookingFee = bookingFee;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
}
