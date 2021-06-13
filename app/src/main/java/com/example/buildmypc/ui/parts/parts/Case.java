package com.example.buildmypc.ui.parts.parts;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Case extends Part implements Parcelable {
	public static final Creator<Case> CREATOR = new Creator<Case>() {
		@NotNull
		@Contract("_ -> new")
		@Override
		public Case createFromParcel(Parcel in) {
			return new Case(in);
		}

		@NotNull
		@Contract(value = "_ -> new", pure = true)
		@Override
		public Case[] newArray(int size) {
			return new Case[size];
		}
	};
	//hello
	private String color;
	private ArrayList<String> dimensionsMm; // 1-d int arraylist with 3 entries,
	private ArrayList<CountedString> expansionSlots;
	private ArrayList<String> supportedFrontUSBs;
	private ArrayList<CountedString> internalDriveBays;
	private ArrayList<String> maxGPULength;
	private ArrayList<String> mbFormFactor;
	private String sidePanel;
	private String type;
	private ArrayList<String> volume; // in liters because screw bri'ish units
	private boolean psuShroud;

	public Case(String model, String manufacturer) {
		super(model, manufacturer);
	}

	public Case(String model, String manufacturer, String color, ArrayList<String> dimensionsMm, ArrayList<CountedString> expansionSlots, ArrayList<String> supportedFrontUSBs, ArrayList<CountedString> internalDriveBays, ArrayList<String> maxGPULength, ArrayList<String> mbFormFactor, String sidePanel, String type, ArrayList<String> volume, boolean psuShroud) {
		super(model, manufacturer);
		this.color = color;
		this.dimensionsMm = dimensionsMm;
		this.expansionSlots = expansionSlots;
		this.supportedFrontUSBs = supportedFrontUSBs;
		this.internalDriveBays = internalDriveBays;
		this.maxGPULength = maxGPULength;
		this.mbFormFactor = mbFormFactor;
		this.sidePanel = sidePanel;
		this.type = type;
		this.volume = volume;
		this.psuShroud = psuShroud;
	}

	protected Case(@NotNull Parcel in) {
		super(in.readString(), in.readString());
		color = in.readString();
		dimensionsMm = in.createStringArrayList();
		expansionSlots = in.createTypedArrayList(CountedString.CREATOR);
		supportedFrontUSBs = in.createStringArrayList();
		internalDriveBays = in.createTypedArrayList(CountedString.CREATOR);
		maxGPULength = in.createStringArrayList();
		mbFormFactor = in.createStringArrayList();
		sidePanel = in.readString();
		type = in.readString();
		volume = in.createStringArrayList();
		psuShroud = in.readBoolean();
	}

	public Case(String model, String manufacturer, String color) {
		this(model, manufacturer, color, null, null, null, null, null, null, null, null, null, false);
	}

	@Override
	public void writeToParcel(@NotNull Parcel dest, int flags) {
		dest.writeString(super.getModel());
		dest.writeString(super.getManufacturer());
		dest.writeString(color);
		dest.writeStringList(dimensionsMm);
		dest.writeTypedList(expansionSlots);
		dest.writeStringList(supportedFrontUSBs);
		dest.writeTypedList(internalDriveBays);
		dest.writeStringList(maxGPULength);
		dest.writeStringList(mbFormFactor);
		dest.writeString(sidePanel);
		dest.writeString(type);
		dest.writeStringList(volume);
		dest.writeBoolean(psuShroud);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ArrayList<String> getDimensionsMm() {
		return dimensionsMm;
	}

	public void setDimensionsMm(ArrayList<String> dimensionsMm) {
		this.dimensionsMm = dimensionsMm;
	}

	public ArrayList<CountedString> getExpansionSlots() {
		return expansionSlots;
	}

	public void setExpansionSlots(ArrayList<CountedString> expansionSlots) {
		this.expansionSlots = expansionSlots;
	}

	public ArrayList<String> getSupportedFrontUSBs() {
		return supportedFrontUSBs;
	}

	public void setSupportedFrontUSBs(ArrayList<String> supportedFrontUSBs) {
		this.supportedFrontUSBs = supportedFrontUSBs;
	}

	public ArrayList<CountedString> getInternalDriveBays() {
		return internalDriveBays;
	}

	public void setInternalDriveBays(ArrayList<CountedString> internalDriveBays) {
		this.internalDriveBays = internalDriveBays;
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

	public ArrayList<String> getVolume() {
		return volume;
	}

	public void setVolume(ArrayList<String> volume) {
		this.volume = volume;
	}

	public boolean isPsuShroud() {
		return psuShroud;
	}

	public void setPsuShroud(boolean psuShroud) {
		this.psuShroud = psuShroud;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Case aCase = (Case) o;

		if (isPsuShroud() != aCase.isPsuShroud()) return false;
		if (getColor() != null ? !getColor().equals(aCase.getColor()) : aCase.getColor() != null)
			return false;
		if (getDimensionsMm() != null ? !getDimensionsMm().equals(aCase.getDimensionsMm()) : aCase.getDimensionsMm() != null)
			return false;
		if (getExpansionSlots() != null ? !getExpansionSlots().equals(aCase.getExpansionSlots()) : aCase.getExpansionSlots() != null)
			return false;
		if (getSupportedFrontUSBs() != null ? !getSupportedFrontUSBs().equals(aCase.getSupportedFrontUSBs()) : aCase.getSupportedFrontUSBs() != null)
			return false;
		if (getInternalDriveBays() != null ? !getInternalDriveBays().equals(aCase.getInternalDriveBays()) : aCase.getInternalDriveBays() != null)
			return false;
		if (getMaxGPULength() != null ? !getMaxGPULength().equals(aCase.getMaxGPULength()) : aCase.getMaxGPULength() != null)
			return false;
		if (getMbFormFactor() != null ? !getMbFormFactor().equals(aCase.getMbFormFactor()) : aCase.getMbFormFactor() != null)
			return false;
		if (getSidePanel() != null ? !getSidePanel().equals(aCase.getSidePanel()) : aCase.getSidePanel() != null)
			return false;
		if (getType() != null ? !getType().equals(aCase.getType()) : aCase.getType() != null)
			return false;
		return getVolume() != null ? getVolume().equals(aCase.getVolume()) : aCase.getVolume() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
		result = 31 * result + (getDimensionsMm() != null ? getDimensionsMm().hashCode() : 0);
		result = 31 * result + (getExpansionSlots() != null ? getExpansionSlots().hashCode() : 0);
		result = 31 * result + (getSupportedFrontUSBs() != null ? getSupportedFrontUSBs().hashCode() : 0);
		result = 31 * result + (getInternalDriveBays() != null ? getInternalDriveBays().hashCode() : 0);
		result = 31 * result + (getMaxGPULength() != null ? getMaxGPULength().hashCode() : 0);
		result = 31 * result + (getMbFormFactor() != null ? getMbFormFactor().hashCode() : 0);
		result = 31 * result + (getSidePanel() != null ? getSidePanel().hashCode() : 0);
		result = 31 * result + (getType() != null ? getType().hashCode() : 0);
		result = 31 * result + (getVolume() != null ? getVolume().hashCode() : 0);
		result = 31 * result + (isPsuShroud() ? 1 : 0);
		return result;
	}

	@NotNull
	@Override
	public String toString() {
		return getColor() + " " + super.toString() + " " + getType();
		/*  White NZXT H510 ATX Mid Tower
			Black NZXT H510 ATX Mid Tower
			Black Corsair 4000D Airflow ATX Mid Tower
			Black Corsair 275R Airflow AIX Mid Tower
			Black Lian Li PC-O11DX ATX Full Tower */

	}

	@Override
	public int getParamCount() {
		return 13;
	}
}