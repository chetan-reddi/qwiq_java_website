package com.transport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="settings")
public class Settings {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	@Column(name="price_per_helper")
	private double pricePerDay;
	@Column(name="promotional_discount")
	private double promotionalDiscount;
	@Column(name="booking_fee")
	private double bookingFee;
	@Column(name="booking_commission")
	private double bookingCommission;
	@Column(name="minimum_distance")
	private double miniumDistance;
	@Column(name="minimum_hours")
	private double miniumHours;
	@Column(name="type")
	private String type;
	public double getBookingCommission() {
		return bookingCommission;
	}
	public void setBookingCommission(double bookingCommission) {
		this.bookingCommission = bookingCommission;
	}
	public double getMiniumDistance() {
		return miniumDistance;
	}
	public void setMiniumDistance(double miniumDistance) {
		this.miniumDistance = miniumDistance;
	}
	public double getMiniumHours() {
		return miniumHours;
	}
	public void setMiniumHours(double miniumHours) {
		this.miniumHours = miniumHours;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
