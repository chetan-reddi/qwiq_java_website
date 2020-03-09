package com.transport.model;

import java.util.Comparator;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="van_details")
public class VanDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	@Column(name = "van_type")
	private String vanTypeName;
	@Column(name="length")
	private double length;
	@Column(name="width")
	private double width;
	@Column(name="height")
	private double height;
	@Column(name="minimum_weight")
	private int minimumWeight;
	@Column(name="maximum_weight")
	private int maximumWeight;
	@Column(name="number_seats")
	private int numberOfSeats;
	@Column(name="price_per_mile_normal_days")
	private double pricePerMileNormalDays;
	@Column(name="price_per_mile_week_days")
	private double pricePerMileWeekDays;
	@Column(name="volume")
	private double volume;
	@Column(name="driver_charge_per_hour_normal_days")
	private double driverChargePerHourNormalDays;
	@Column(name="driver_charge_per_hour_week_days")
	private double driverChargePerHourWeekDays;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated_date")
	private Date updatedDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVanTypeName() {
		return vanTypeName;
	}
	public void setVanTypeName(String vanTypeName) {
		this.vanTypeName = vanTypeName;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getMinimumWeight() {
		return minimumWeight;
	}
	public void setMinimumWeight(int minimumWeight) {
		this.minimumWeight = minimumWeight;
	}
	public int getMaximumWeight() {
		return maximumWeight;
	}
	public void setMaximumWeight(int maximumWeight) {
		this.maximumWeight = maximumWeight;
	}
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public double getPricePerMileNormalDays() {
		return pricePerMileNormalDays;
	}
	public void setPricePerMileNormalDays(double pricePerMileNormalDays) {
		this.pricePerMileNormalDays = pricePerMileNormalDays;
	}
	public double getPricePerMileWeekDays() {
		return pricePerMileWeekDays;
	}
	public void setPricePerMileWeekDays(double pricePerMileWeekDays) {
		this.pricePerMileWeekDays = pricePerMileWeekDays;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getDriverChargePerHourNormalDays() {
		return driverChargePerHourNormalDays;
	}
	public void setDriverChargePerHourNormalDays(double driverChargePerHourNormalDays) {
		this.driverChargePerHourNormalDays = driverChargePerHourNormalDays;
	}
	public double getDriverChargePerHourWeekDays() {
		return driverChargePerHourWeekDays;
	}
	public void setDriverChargePerHourWeekDays(double driverChargePerHourWeekDays) {
		this.driverChargePerHourWeekDays = driverChargePerHourWeekDays;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public static Comparator<VanDetails> vanvolume = new Comparator<VanDetails>() {

		public int compare(VanDetails s1, VanDetails s2) {

		   double volume1 = s1.getVolume();
		   double volume2 = s2.getVolume();
		   if(volume1>volume2)
		   {
			return 1;   
		   }
		return 1;

		   /*For descending order*/
		   //rollno2-rollno1;
	   }};

}
