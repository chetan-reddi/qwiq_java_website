package com.transport.model;

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
@Table(name="bid")
public class Bid {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	@Column(name = "bid_id")
	private String bidId;
	@Column(name = "driver_user_id")
    private String driverUserId;
	@NotNull
	@NotEmpty
	@Column(name = "quote_id")
    private String quoteId;
	@NotNull
	@NotEmpty
	@Column(name="bid_amount")
	private String bidAmount;
	
	@Column(name="comment")
	private String comment;
	@Column(name="created_date")
	private Date created_date;
	@Column(name="status")
	private int status;
	@Column(name="driver_charges")
	private double driverCharges;
	@Column(name="mileage_charges")
	private double mileageCharges;
	@Column(name="additional_time_charges_per_hour")
	private double additonalTimeChargesPerHour;
	@Column(name="additional_stop_charges")
	private double additionalStopCharges;
	@Column(name="out_of_hours_charges")
	private double outOfHourCharges;
	@Column(name="additional_charges")
	private double additionalCharges;
	@Column(name="additional_charges_description")
	private String additionalChargesDesc;
	@Column(name="promotional_discount")
	private double promotionalDiscount;
	@Column(name="booking_fee")
	private double bookingFee;
	@Column(name="total_cost")
	private double totalCost;
	@NotNull
	@NotEmpty
	@Column(name="vehicle_id")
	private String vehicleId;
	
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public String getAdditionalChargesDesc() {
		return additionalChargesDesc;
	}
	public void setAdditionalChargesDesc(String additionalChargesDesc) {
		this.additionalChargesDesc = additionalChargesDesc;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(String bidAmount) {
		this.bidAmount = bidAmount;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
}
