package com.example.buildmypc.ui;

import com.example.buildmypc.ui.ExternalPart;

import java.util.ArrayList;

public class Case extends ExternalPart {

	private String color;
	private int[] dimensionsMm; // 1-d int arraylist with 3 entries,
	private int fullExpansionSlotCount;
	private int halfExpansionSlotCount;
	private ArrayList<String> supportedFrontUSBs;
	private int internalDrive2_5Count;
	private int internalDrive3_5Count;
	private ArrayList<String> maxGPULength;
	private ArrayList<String> mbFormFactor;
	private String sidePanel;

	private String type;
	private double volume; // in liters because screw bri'ish units

	public Case(String model, String manufacturer) {
		super(model, manufacturer);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int[] getDimensionsMm() {
		return dimensionsMm;
	}

	public void setDimensionsMm(int[] dimensionsMm) {
		this.dimensionsMm = dimensionsMm;
	}

	public int getFullExpansionSlotCount() {
		return fullExpansionSlotCount;
	}

	public void setFullExpansionSlotCount(int fullExpansionSlotCount) {
		this.fullExpansionSlotCount = fullExpansionSlotCount;
	}

	public int getHalfExpansionSlotCount() {
		return halfExpansionSlotCount;
	}

	public void setHalfExpansionSlotCount(int halfExpansionSlotCount) {
		this.halfExpansionSlotCount = halfExpansionSlotCount;
	}

	public ArrayList<String> getSupportedFrontUSBs() {
		return supportedFrontUSBs;
	}

	public void setSupportedFrontUSBs(ArrayList<String> supportedFrontUSBs) {
		this.supportedFrontUSBs = supportedFrontUSBs;
	}

	public int getInternalDrive2_5Count() {
		return internalDrive2_5Count;
	}

	public void setInternalDrive2_5Count(int internalDrive2_5Count) {
		this.internalDrive2_5Count = internalDrive2_5Count;
	}

	public int getInternalDrive3_5Count() {
		return internalDrive3_5Count;
	}

	public void setInternalDrive3_5Count(int internalDrive3_5Count) {
		this.internalDrive3_5Count = internalDrive3_5Count;
	}

	public ArrayList<String> getMaxGPULength() {
		return maxGPULength;
	}

	public void setMaxGPULength(ArrayList<String> maxGPULength) {
		this.maxGPULength = maxGPULength;
	}

	public ArrayList<String> getMbFormFactor() {
		return mbFormFactor;
	}

	public void setMbFormFactor(ArrayList<String> mbFormFactor) {
		this.mbFormFactor = mbFormFactor;
	}

	public String getSidePanel() {
		return sidePanel;
	}

	public void setSidePanel(String sidePanel) {
		this.sidePanel = sidePanel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}
}
