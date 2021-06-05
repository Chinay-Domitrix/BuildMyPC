package com.example.buildmypc.ui;

import java.util.ArrayList;

public class Cooler extends InternalPart {

	private boolean hasFan;
	private int fanHeight; // in millimeters
	private int fanRPM;

	private boolean isWaterCooled; // the better kind of cooler
	private int waterCoolerSize; // in millimeters

	private int[] noiseRangeDb; // in decibles
	private ArrayList<String> supportedSockets; // there are so many for each one so I might as well make it iterable

	public Cooler(String model, String manufacturer) {
		super(model, manufacturer, null);
	}

	public boolean isHasFan() {
		return hasFan;
	}
	public void setHasFan(boolean hasFan) {
		this.hasFan = hasFan;
	}

	public int getFanHeight() {
		return fanHeight;
	}
	public void setFanHeight(int fanHeight) {
		this.fanHeight = fanHeight;
	}

	public int getFanRPM() {
		return fanRPM;
	}
	public void setFanRPM(int fanRPM) {
		this.fanRPM = fanRPM;
	}

	public boolean isWaterCooled() {
		return isWaterCooled;
	}
	public void setWaterCooled(boolean waterCooled) {
		isWaterCooled = waterCooled;
	}

	public int getWaterCoolerSize() {
		return waterCoolerSize;
	}
	public void setWaterCoolerSize(int waterCoolerSize) {
		this.waterCoolerSize = waterCoolerSize;
	}

	public int[] getNoiseRangeDb() {
		return noiseRangeDb;
	}
	public void setNoiseRangeDb(int[] noiseRangeDb) {
		this.noiseRangeDb = noiseRangeDb;
	}

	public ArrayList<String> getSupportedSockets() {
		return supportedSockets;
	}
	public void setSupportedSockets(ArrayList<String> supportedSockets) {
		this.supportedSockets = supportedSockets;
	}
}
