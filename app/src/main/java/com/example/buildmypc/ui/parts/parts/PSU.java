package com.example.buildmypc.ui.parts.parts;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PSU extends Part implements Parcelable { // stands for "Power Supply Unit"
	public static final Creator<PSU> CREATOR = new Creator<PSU>() {
		@NotNull
		@Contract("_ -> new")
		@Override
		public PSU createFromParcel(Parcel in) {
			return new PSU(in);
		}

		@NotNull
		@Contract(value = "_ -> new", pure = true)
		@Override
		public PSU[] newArray(int size) {
			return new PSU[size];
		}
	};
	private ArrayList<CountedString> connectorList;
	private String efficiencyRating;
	private boolean hasFan;
	private String formFactor;
	private int length; // in millimeters
	private String modular;
	private int wattage;

	public PSU(String model, String manufacturer) {
		super(model, manufacturer);
	}

	public PSU(@Nullable String model, @Nullable String manufacturer, ArrayList<CountedString> connectorList, String efficiencyRating, boolean hasFan, String formFactor, int length, String modular, int wattage) {
		super(model, manufacturer);
		this.connectorList = connectorList;
		this.efficiencyRating = efficiencyRating;
		this.hasFan = hasFan;
		this.formFactor = formFactor;
		this.length = length;
		this.modular = modular;
		this.wattage = wattage;
	}

	public PSU(Parcel in) {
		super(in.readString(), in.readString());
		in.readParcelableList(connectorList, connectorList.getClass().getClassLoader());
		efficiencyRating = in.readString();
		hasFan = in.readBoolean();
		formFactor = in.readString();
		length = in.readInt();
		modular = in.readString();
		wattage = in.readInt();
	}

	public ArrayList<CountedString> getConnectorList() {
		return connectorList;
	}

	public void setConnectorList(ArrayList<CountedString> connectorList) {
		this.connectorList = connectorList;
	}

	public String getEfficiencyRating() {
		return efficiencyRating;
	}

	public void setEfficiencyRating(String efficiencyRating) {
		this.efficiencyRating = efficiencyRating;
	}

	public boolean isHasFan() {
		return hasFan;
	}

	public void setHasFan(boolean hasFan) {
		this.hasFan = hasFan;
	}

	public String getFormFactor() {
		return formFactor;
	}

	public void setFormFactor(String formFactor) {
		this.formFactor = formFactor;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getModular() {
		return modular;
	}

	public void setModular(String modular) {
		this.modular = modular;
	}

	public int getWattage() {
		return wattage;
	}

	public void setWattage(int wattage) {
		this.wattage = wattage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		PSU psu = (PSU) o;

		if (isHasFan() != psu.isHasFan()) return false;
		if (getLength() != psu.getLength()) return false;
		if (getWattage() != psu.getWattage()) return false;
		if (getConnectorList() != null ? !getConnectorList().equals(psu.getConnectorList()) : psu.getConnectorList() != null)
			return false;
		if (getEfficiencyRating() != null ? !getEfficiencyRating().equals(psu.getEfficiencyRating()) : psu.getEfficiencyRating() != null)
			return false;
		if (getFormFactor() != null ? !getFormFactor().equals(psu.getFormFactor()) : psu.getFormFactor() != null)
			return false;
		return getModular() != null ? getModular().equals(psu.getModular()) : psu.getModular() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getConnectorList() != null ? getConnectorList().hashCode() : 0);
		result = 31 * result + (getEfficiencyRating() != null ? getEfficiencyRating().hashCode() : 0);
		result = 31 * result + (isHasFan() ? 1 : 0);
		result = 31 * result + (getFormFactor() != null ? getFormFactor().hashCode() : 0);
		result = 31 * result + getLength();
		result = 31 * result + (getModular() != null ? getModular().hashCode() : 0);
		result = 31 * result + getWattage();
		return result;
	}

	@Override
	public void writeToParcel(@NotNull Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeParcelableList(connectorList, flags);
		dest.writeString(efficiencyRating);
		dest.writeBoolean(hasFan);
		dest.writeString(formFactor);
		dest.writeInt(length);
		dest.writeString(modular);
		dest.writeInt(wattage);
	}
}