package com.transport.response;

public class VanDetailsList {

	private long vanId;
	private String vanTypeName;
	private double length;
	private double width;
	private double height;
	private int seats;
	private double minimumWeight;
	private double maximumWeight;
	
	public long getVanId() {
		return vanId;
	}
	public void setVanId(long vanId) {
		this.vanId = vanId;
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
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public double getMinimumWeight() {
		return minimumWeight;
	}
	public void setMinimumWeight(double minimumWeight) {
		this.minimumWeight = minimumWeight;
	}
	public double getMaximumWeight() {
		return maximumWeight;
	}
	public void setMaximumWeight(double maximumWeight) {
		this.maximumWeight = maximumWeight;
	}
}
