package com.transport.response;

public class CalculateResponse {

	private double totalItemsVolume;
	private String estimatedVanSize;
	private double vanCapacity;
	public double getTotalItemsVolume() {
		return totalItemsVolume;
	}
	public void setTotalItemsVolume(double totalItemsVolume) {
		this.totalItemsVolume = totalItemsVolume;
	}
	public String getEstimatedVanSize() {
		return estimatedVanSize;
	}
	public void setEstimatedVanSize(String estimatedVanSize) {
		this.estimatedVanSize = estimatedVanSize;
	}
	public double getVanCapacity() {
		return vanCapacity;
	}
	public void setVanCapacity(double vanCapacity) {
		this.vanCapacity = vanCapacity;
	}
}
