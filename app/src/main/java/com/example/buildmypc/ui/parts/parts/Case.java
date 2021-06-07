package com.example.buildmypc.ui.parts.parts;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

public class Case extends Part implements Parcelable {
//hello
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

	protected Case(Parcel in) {
		super(in.readString(), in.readString());
		color = in.readString();
		dimensionsMm = in.createIntArray();
		fullExpansionSlotCount = in.readInt();
		halfExpansionSlotCount = in.readInt();
		supportedFrontUSBs = in.createStringArrayList();
		internalDrive2_5Count = in.readInt();
		internalDrive3_5Count = in.readInt();
		maxGPULength = in.createStringArrayList();
		mbFormFactor = in.createStringArrayList();
		sidePanel = in.readString();
		type = in.readString();
		volume = in.readDouble();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(super.getModel());
		dest.writeString(super.getManufacturer());
		dest.writeString(color);
		dest.writeIntArray(dimensionsMm);
		dest.writeInt(fullExpansionSlotCount);
		dest.writeInt(halfExpansionSlotCount);
		dest.writeStringList(supportedFrontUSBs);
		dest.writeInt(internalDrive2_5Count);
		dest.writeInt(internalDrive3_5Count);
		dest.writeStringList(maxGPULength);
		dest.writeStringList(mbFormFactor);
		dest.writeString(sidePanel);
		dest.writeString(type);
		dest.writeDouble(volume);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Case> CREATOR = new Creator<Case>() {
		@Override
		public Case createFromParcel(Parcel in) {
			return new Case(in);
		}

		@Override
		public Case[] newArray(int size) {
			return new Case[size];
		}
	};

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Case aCase = (Case) o;

		if (getFullExpansionSlotCount() != aCase.getFullExpansionSlotCount()) return false;
		if (getHalfExpansionSlotCount() != aCase.getHalfExpansionSlotCount()) return false;
		if (getInternalDrive2_5Count() != aCase.getInternalDrive2_5Count()) return false;
		if (getInternalDrive3_5Count() != aCase.getInternalDrive3_5Count()) return false;
		if (Double.compare(aCase.getVolume(), getVolume()) != 0) return false;
		if (getColor() != null ? !getColor().equals(aCase.getColor()) : aCase.getColor() != null)
			return false;
		if (!Arrays.equals(getDimensionsMm(), aCase.getDimensionsMm())) return false;
		if (getSupportedFrontUSBs() != null ? !getSupportedFrontUSBs().equals(aCase.getSupportedFrontUSBs()) : aCase.getSupportedFrontUSBs() != null)
			return false;
		if (getMaxGPULength() != null ? !getMaxGPULength().equals(aCase.getMaxGPULength()) : aCase.getMaxGPULength() != null)
			return false;
		if (getMbFormFactor() != null ? !getMbFormFactor().equals(aCase.getMbFormFactor()) : aCase.getMbFormFactor() != null)
			return false;
		if (getSidePanel() != null ? !getSidePanel().equals(aCase.getSidePanel()) : aCase.getSidePanel() != null)
			return false;
		return getType() != null ? getType().equals(aCase.getType()) : aCase.getType() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		long temp;
		result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
		result = 31 * result + Arrays.hashCode(getDimensionsMm());
		result = 31 * result + getFullExpansionSlotCount();
		result = 31 * result + getHalfExpansionSlotCount();
		result = 31 * result + (getSupportedFrontUSBs() != null ? getSupportedFrontUSBs().hashCode() : 0);
		result = 31 * result + getInternalDrive2_5Count();
		result = 31 * result + getInternalDrive3_5Count();
		result = 31 * result + (getMaxGPULength() != null ? getMaxGPULength().hashCode() : 0);
		result = 31 * result + (getMbFormFactor() != null ? getMbFormFactor().hashCode() : 0);
		result = 31 * result + (getSidePanel() != null ? getSidePanel().hashCode() : 0);
		result = 31 * result + (getType() != null ? getType().hashCode() : 0);
		temp = Double.doubleToLongBits(getVolume());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
