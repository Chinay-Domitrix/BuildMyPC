package com.example.buildmypc.ui.parts.parts;

import java.util.ArrayList;

public class GPU extends InternalPart { // the only important part for 12 year old me buying a gaming PC

	private int boostClockSpeed; // in mhz
	private String chipset;
	private String cooling;
	private int coreClockSpeed; // in mhz
	private int effectiveMemClockSpeed; // also in mhz; unrelated to boostClockSpeed and coreClockSpeed
	private int expansionSlotWidth;
	private String externalPower;
	private String frameSync;
	private String gpuInterface;
	private int length; // in millimeters
	private int intMemory; // internal gpu memory; in gb, usually
	private String intMemoryType;
	private int tdpW; // thermal design power wattage (how much of a beating the computer is usually able to take, measured in watts)
	private ArrayList<String> videoPorts; // I don't wanna make a custom object for this if I need to implement parcelable eventually

	public GPU(String model, String manufacturer) {
		super(model, manufacturer);
	}

	public int getBoostClockSpeed() {
		return boostClockSpeed;
	}

	public void setBoostClockSpeed(int boostClockSpeed) {
		this.boostClockSpeed = boostClockSpeed;
	}

	public String getChipset() {
		return chipset;
	}

	public void setChipset(String chipset) {
		this.chipset = chipset;
	}

	public String getCooling() {
		return cooling;
	}

	public void setCooling(String cooling) {
		this.cooling = cooling;
	}

	public int getCoreClockSpeed() {
		return coreClockSpeed;
	}

	public void setCoreClockSpeed(int coreClockSpeed) {
		this.coreClockSpeed = coreClockSpeed;
	}

	public int getEffectiveMemClockSpeed() {
		return effectiveMemClockSpeed;
	}

	public void setEffectiveMemClockSpeed(int effectiveMemClockSpeed) {
		this.effectiveMemClockSpeed = effectiveMemClockSpeed;
	}

	public int getExpansionSlotWidth() {
		return expansionSlotWidth;
	}

	public void setExpansionSlotWidth(int expansionSlotWidth) {
		this.expansionSlotWidth = expansionSlotWidth;
	}

	public String getExternalPower() {
		return externalPower;
	}

	public void setExternalPower(String externalPower) {
		this.externalPower = externalPower;
	}

	public String getFrameSync() {
		return frameSync;
	}

	public void setFrameSync(String frameSync) {
		this.frameSync = frameSync;
	}

	public String getGpuInterface() {
		return gpuInterface;
	}

	public void setGpuInterface(String gpuInterface) {
		this.gpuInterface = gpuInterface;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getIntMemory() {
		return intMemory;
	}

	public void setIntMemory(int intMemory) {
		this.intMemory = intMemory;
	}

	public String getIntMemoryType() {
		return intMemoryType;
	}

	public void setIntMemoryType(String intMemoryType) {
		this.intMemoryType = intMemoryType;
	}

	public int getTdpW() {
		return tdpW;
	}

	public void setTdpW(int tdpW) {
		this.tdpW = tdpW;
	}

	public ArrayList<String> getVideoPorts() {
		return videoPorts;
	}

	public void setVideoPorts(ArrayList<String> videoPorts) {
		this.videoPorts = videoPorts;
	}

}
