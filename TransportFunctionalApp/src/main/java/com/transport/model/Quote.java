package com.transport.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="quote")
public class Quote {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	@Column(name="user_ref_id")
	private String userRefId;
	@Column(name="quote_id")
	private String quoteId;
	@NotNull
	@NotEmpty
	@Column(name="from_address")
	private String formAddress;
	@NotNull
	@NotEmpty
	@Column(name="delivery_address")
	private String deliveryAddress;
	@Column(name="van_size")
	private int vanSize;
	@Column(name="helpers_count")
	private int helpersCount;
	@Column(name="distance")
	private double distance;
	@NotNull
	@NotEmpty
	@Column(name="collection_postcode")
	private String collectionPostCode;
	@NotNull
	@NotEmpty
	@Column(name="collection_streetAddress")
	private String collectionStreetAddres;
	@NotNull
	@NotEmpty
	@Column(name="collection_city")
	private String collectionCity;
	@NotNull
	@NotEmpty
	@Column(name="delivery_postCode")
	private String deliveryPostCode;
	@NotNull
	@NotEmpty
	@Column(name="delivery_streetAddress")
	private String deliveryStreetAddres;
	@NotNull
	@NotEmpty
	@Column(name="delivery_city")
	private String deliveryCity;
	@Column(name="hours_needed")
	private int hoursNeeded;
	@Column(name="pick_up_date")
	private Date pickUpDate;
	
	@NotNull
	@NotEmpty
	@Column(name="pick_up_time")
	private String pickUpTime;
	@NotNull
	@NotEmpty
	@Column(name="name")
	private String name;
	@NotNull
	@NotEmpty
	@Column(name="email")
	private String email;
	@NotNull
	@NotEmpty
	@Column(name="comment")
	private String comment;
	@NotNull
	@NotEmpty
	@Column(name="phone_number")
	private String phoneNumber;
	@Column(name="pick_up_lattitude")
	private String pickupLatitude;
	@Column(name="pick_up_longitude")
	private String pickupLongitude;
	@Column(name="drop_lattitude")
	private String dropLatitude;
	@Column(name="drop_longitude")
	private String dropLongitude;
	@Column(name="reason_for_cancellation")
	private String reason;
	@Column(name="price")
	private double price;
	@Column(name="booking_fee")
	private double bookingFee;
	@Column(name="discount_amount")
	private double discountAmount;
	@Column(name="total_price")
	private double totalPrice;
	@Column(name="status")
	private int status;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getBookingFee() {
		return bookingFee;
	}
	public void setBookingFee(double bookingFee) {
		this.bookingFee = bookingFee;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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
	public String getUserRefId() {
		return userRefId;
	}
	public void setUserRefId(String userRefId) {
		this.userRefId = userRefId;
	}
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public String getFormAddress() {
		return formAddress;
	}
	public void setFormAddress(String formAddress) {
		this.formAddress = formAddress;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public int getVanSize() {
		return vanSize;
	}
	public void setVanSize(int vanSize) {
		this.vanSize = vanSize;
	}
	public int getHelpersCount() {
		return helpersCount;
	}
	public void setHelpersCount(int helpersCount) {
		this.helpersCount = helpersCount;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getCollectionPostCode() {
		return collectionPostCode;
	}
	public void setCollectionPostCode(String collectionPostCode) {
		this.collectionPostCode = collectionPostCode;
	}
	public String getCollectionStreetAddres() {
		return collectionStreetAddres;
	}
	public void setCollectionStreetAddres(String collectionStreetAddres) {
		this.collectionStreetAddres = collectionStreetAddres;
	}
	public String getCollectionCity() {
		return collectionCity;
	}
	public void setCollectionCity(String collectionCity) {
		this.collectionCity = collectionCity;
	}
	public String getDeliveryPostCode() {
		return deliveryPostCode;
	}
	public void setDeliveryPostCode(String deliveryPostCode) {
		this.deliveryPostCode = deliveryPostCode;
	}
	public String getDeliveryStreetAddres() {
		return deliveryStreetAddres;
	}
	public void setDeliveryStreetAddres(String deliveryStreetAddres) {
		this.deliveryStreetAddres = deliveryStreetAddres;
	}
	public String getDeliveryCity() {
		return deliveryCity;
	}
	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}
	public int getHoursNeeded() {
		return hoursNeeded;
	}
	public void setHoursNeeded(int hoursNeeded) {
		this.hoursNeeded = hoursNeeded;
	}
	public Date getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}
	public String getPickUpTime() {
		return pickUpTime;
	}
	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPickupLatitude() {
		return pickupLatitude;
	}
	public void setPickupLatitude(String pickupLatitude) {
		this.pickupLatitude = pickupLatitude;
	}
	public String getPickupLongitude() {
		return pickupLongitude;
	}
	public void setPickupLongitude(String pickupLongitude) {
		this.pickupLongitude = pickupLongitude;
	}
	public String getDropLatitude() {
		return dropLatitude;
	}
	public void setDropLatitude(String dropLatitude) {
		this.dropLatitude = dropLatitude;
	}
	public String getDropLongitude() {
		return dropLongitude;
	}
	public void setDropLongitude(String dropLongitude) {
		this.dropLongitude = dropLongitude;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
