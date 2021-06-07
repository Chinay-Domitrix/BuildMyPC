package com.example.buildmypc.ui.parts.parts;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class GPU extends Part implements Parcelable { // the only important part for 12 year old me buying a gaming PC

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		GPU gpu = (GPU) o;

		if (getBoostClockSpeed() != gpu.getBoostClockSpeed()) return false;
		if (getCoreClockSpeed() != gpu.getCoreClockSpeed()) return false;
		if (getEffectiveMemClockSpeed() != gpu.getEffectiveMemClockSpeed()) return false;
		if (getExpansionSlotWidth() != gpu.getExpansionSlotWidth()) return false;
		if (getLength() != gpu.getLength()) return false;
		if (getIntMemory() != gpu.getIntMemory()) return false;
		if (getTdpW() != gpu.getTdpW()) return false;
		if (getChipset() != null ? !getChipset().equals(gpu.getChipset()) : gpu.getChipset() != null)
			return false;
		if (getCooling() != null ? !getCooling().equals(gpu.getCooling()) : gpu.getCooling() != null)
			return false;
		if (getExternalPower() != null ? !getExternalPower().equals(gpu.getExternalPower()) : gpu.getExternalPower() != null)
			return false;
		if (getFrameSync() != null ? !getFrameSync().equals(gpu.getFrameSync()) : gpu.getFrameSync() != null)
			return false;
		if (getGpuInterface() != null ? !getGpuInterface().equals(gpu.getGpuInterface()) : gpu.getGpuInterface() != null)
			return false;
		if (getIntMemoryType() != null ? !getIntMemoryType().equals(gpu.getIntMemoryType()) : gpu.getIntMemoryType() != null)
			return false;
		return getVideoPorts() != null ? getVideoPorts().equals(gpu.getVideoPorts()) : gpu.getVideoPorts() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + getBoostClockSpeed();
		result = 31 * result + (getChipset() != null ? getChipset().hashCode() : 0);
		result = 31 * result + (getCooling() != null ? getCooling().hashCode() : 0);
		result = 31 * result + getCoreClockSpeed();
		result = 31 * result + getEffectiveMemClockSpeed();
		result = 31 * result + getExpansionSlotWidth();
		result = 31 * result + (getExternalPower() != null ? getExternalPower().hashCode() : 0);
		result = 31 * result + (getFrameSync() != null ? getFrameSync().hashCode() : 0);
		result = 31 * result + (getGpuInterface() != null ? getGpuInterface().hashCode() : 0);
		result = 31 * result + getLength();
		result = 31 * result + getIntMemory();
		result = 31 * result + (getIntMemoryType() != null ? getIntMemoryType().hashCode() : 0);
		result = 31 * result + getTdpW();
		result = 31 * result + (getVideoPorts() != null ? getVideoPorts().hashCode() : 0);
		return result;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeInt(boostClockSpeed);
		dest.writeString(chipset);
		dest.writeString(cooling);
		dest.writeInt(coreClockSpeed);
		dest.writeInt(effectiveMemClockSpeed);
		dest.writeInt(expansionSlotWidth);
		dest.writeString(externalPower);
		dest.writeString(frameSync);
		dest.writeString(gpuInterface);
		dest.writeInt(length);
		dest.writeInt(intMemory);
		dest.writeString(intMemoryType);
		dest.writeInt(tdpW);
		dest.writeStringList(videoPorts);
	}

	public GPU(Parcel in){
		super(in.readString(), in.readString());
		boostClockSpeed = in.readInt();
		chipset = in.readString();
		cooling = in.readString();
		coreClockSpeed = in.readInt();
		effectiveMemClockSpeed = in.readInt();
		expansionSlotWidth = in.readInt();
		externalPower = in.readString();
		frameSync = in.readString();
		gpuInterface = in.readString();
		length = in.readInt();
		intMemory = in.readInt();
		intMemoryType = in.readString();
		tdpW = in.readInt();
		in.readStringList(videoPorts);
	}
}
