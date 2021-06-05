package com.example.buildmypc.ui.parts.parts;

import java.util.Arrays;

public final class Cooler {
	private final String manufacturer;
	private final String model;
	private final String rpm;
	private final String noiseLevel;
	private final int height;
	private final String[] socketSupport;
	private final boolean waterCooled;
	private final boolean fanless;

	public Cooler(String manufacturer, String model, String rpm, String noiseLevel, int height, String[] socketSupport, boolean waterCooled, boolean fanless) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.rpm = rpm;
		this.noiseLevel = noiseLevel;
		this.height = height;
		this.socketSupport = socketSupport;
		this.waterCooled = waterCooled;
		this.fanless = fanless;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getModel() {
		return model;
	}

	public String getRpm() {
		return rpm;
	}

	public String getNoiseLevel() {
		return noiseLevel;
	}

	public int getHeight() {
		return height;
	}

	public String[] getSocketSupport() {
		return socketSupport;
	}

	public boolean isWaterCooled() {
		return waterCooled;
	}

	public boolean isFanless() {
		return fanless;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Cooler cooler = (Cooler) o;

		if (getHeight() != cooler.getHeight()) return false;
		if (isWaterCooled() != cooler.isWaterCooled()) return false;
		if (isFanless() != cooler.isFanless()) return false;
		if (!getManufacturer().equals(cooler.getManufacturer())) return false;
		if (!getModel().equals(cooler.getModel())) return false;
		if (getRpm() != null ? !getRpm().equals(cooler.getRpm()) : cooler.getRpm() != null)
			return false;
		if (!getNoiseLevel().equals(cooler.getNoiseLevel())) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		return Arrays.equals(getSocketSupport(), cooler.getSocketSupport());
	}

	@Override
	public int hashCode() {
		int result = getManufacturer().hashCode();
		result = 31 * result + getModel().hashCode();
		result = 31 * result + (getRpm() != null ? getRpm().hashCode() : 0);
		result = 31 * result + getNoiseLevel().hashCode();
		result = 31 * result + getHeight();
		result = 31 * result + Arrays.hashCode(getSocketSupport());
		result = 31 * result + (isWaterCooled() ? 1 : 0);
		result = 31 * result + (isFanless() ? 1 : 0);
		return result;
	}
}
