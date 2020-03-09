package com.transport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="vehicle_registration")
public class VehicleRegistration {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	@Column(name="user_ref_id")
	private String userId;
	@Column(name="base_range")
	private long baseRange;
	@Column(name="base_area")
	private String baseArea;
	@Column(name="latittude")
	private double latittude;
	@Column(name="longitude")
	private double longitude;
	@Column(name="vehicle_reg_no")
	private String vehicleRegNo;
	@Column(name="national_insurance_number")
	private String nationalInsuranceNumber;
	@Column(name="van_size")
	private int vanSize;
	@Column(name="vehicle_id")
	private String vehicleId;
	@Column(name = "van_image")
	private String vanImage;
	@Column(name = "motor_insurance")
	private String motorInsurance;
	@Column(name = "goods_transit_cover")
	private String goodsTransitCover;
	@Column(name="liability_insurance")
	private String liabilityInsurance;
	@Column(name="path")
	private String path;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getVanImage() {
		return vanImage;
	}
	public void setVanImage(String vanImage) {
		this.vanImage = vanImage;
	}
	public String getMotorInsurance() {
		return motorInsurance;
	}
	public void setMotorInsurance(String motorInsurance) {
		this.motorInsurance = motorInsurance;
	}
	public String getGoodsTransitCover() {
		return goodsTransitCover;
	}
	public void setGoodsTransitCover(String goodsTransitCover) {
		this.goodsTransitCover = goodsTransitCover;
	}
	public String getLiabilityInsurance() {
		return liabilityInsurance;
	}
	public void setLiabilityInsurance(String liabilityInsurance) {
		this.liabilityInsurance = liabilityInsurance;
	}
	@Column(name="status")
	private int status;
	@Column(name="vehicle_status")
	private int vehicleStatus;
	@Column(name="driver_id")
	private String driverId;
	@Column(name="driver_charge_per_hour")
	private double driverChargesPerHour;
	@Column(name="charge_per_mile")
	private double chargePerMile;
	
	public double getDriverChargesPerHour() {
		return driverChargesPerHour;
	}
	public void setDriverChargesPerHour(double driverChargesPerHour) {
		this.driverChargesPerHour = driverChargesPerHour;
	}
	public double getChargePerMile() {
		return chargePerMile;
	}
	public void setChargePerMile(double chargePerMile) {
		this.chargePerMile = chargePerMile;
	}
	public int getVehicleStatus() {
		return vehicleStatus;
	}
	public void setVehicleStatus(int vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getBaseArea() {
		return baseArea;
	}
	public void setBaseArea(String baseArea) {
		this.baseArea = baseArea;
	}
	public double getLatittude() {
		return latittude;
	}
	public void setLatittude(double latittude) {
		this.latittude = latittude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getBaseRange() {
		return baseRange;
	}
	public void setBaseRange(long baseRange) {
		this.baseRange = baseRange;
	}
	public String getVehicleRegNo() {
		return vehicleRegNo;
	}
	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}
	public String getNationalInsuranceNumber() {
		return nationalInsuranceNumber;
	}
	public void setNationalInsuranceNumber(String nationalInsuranceNumber) {
		this.nationalInsuranceNumber = nationalInsuranceNumber;
	}
	public int getVanSize() {
		return vanSize;
	}
	public void setVanSize(int vanSize) {
		this.vanSize = vanSize;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
