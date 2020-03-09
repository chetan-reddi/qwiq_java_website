package com.transport.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "driver_kyc_docs_details")
public class KYCDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "user_ref_id")
	private String userRefId;
	@Column(name = "van_image")
	private String vanImage;
	@Column(name = "driving_license")
	private String drivingLicense;
	@Column(name = "motor_insurance")
	private String motorInsurance;
	@Column(name = "goods_transit_cover")
	private String goodsTransitCover;
	@Column(name="liability_insurance")
	private String liabilityInsurance;
	@Column(name="status")
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	public String getVanImage() {
		return vanImage;
	}
	public void setVanImage(String vanImage) {
		this.vanImage = vanImage;
	}
	public String getDrivingLicense() {
		return drivingLicense;
	}
	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
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
	
}