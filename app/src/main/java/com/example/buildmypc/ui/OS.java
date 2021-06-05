package com.example.buildmypc.ui;

public class OS extends Accessory {

	private String bitMode;
	private double maxMemSupport;
	private String type;
	private String edition;
	private String oem_retail;

	public OS(String model, String manufacturer) {
		super(model, manufacturer);
	}

	public String getBitMode() {
		return bitMode;
	}

	public void setBitMode(String bitMode) {
		this.bitMode = bitMode;
	}

	public double getMaxMemSupport() {
		return maxMemSupport;
	}

	public void setMaxMemSupport(double maxMemSupport) {
		this.maxMemSupport = maxMemSupport;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getOem_retail() {
		return oem_retail;
	}

	public void setOem_retail(String oem_retail) {
		this.oem_retail = oem_retail;
	}
}
