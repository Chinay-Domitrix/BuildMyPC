package com.example.buildmypc.ui.parts.parts;

public class StorageComp extends InternalPart {

	double formFactor;
	private int cacheSizeMB; // in mb
	private int capacity; // in MB -> 2TB is 2000, 1.5TB is 1500, 512 GB is 512
	private String sataInterface; // which SATA does this part connect to
	private boolean nvme; // NMVe, or non-volatile memory express
	private int rpm; // only applies for HDDs
	private String type; // either HDD or SSD

	public StorageComp(String model, String manufacturer, Boolean hasEcc) {
		super(model, manufacturer, hasEcc);
	}

	public int getCacheSizeMB() {
		return cacheSizeMB;
	}

	public void setCacheSizeMB(int cacheSizeMB) {
		this.cacheSizeMB = cacheSizeMB;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getFormFactor() {
		return formFactor;
	}

	public void setFormFactor(double formFactor) {
		this.formFactor = formFactor;
	}

	public String getSataInterface() {
		return sataInterface;
	}

	public void setSataInterface(String sataInterface) {
		this.sataInterface = sataInterface;
	}

	public double getNvme() {
		return nvme;
	}

	public void setNvme(double nvme) {
		this.nvme = nvme;
	}

	public int getRpm() {
		return rpm;
	}

	public void setRpm(int rpm) {
		this.rpm = rpm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
