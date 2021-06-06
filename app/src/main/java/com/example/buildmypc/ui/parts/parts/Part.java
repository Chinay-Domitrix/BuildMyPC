package com.example.buildmypc.ui.parts.parts;

import android.os.Parcel;
import android.os.Parcelable;

// the start of the part hierarchy
public class Part implements Parcelable {

	private String model;
	private String manufacturer;

	public Part(String model, String manufacturer) {
		this.model = model;
		this.manufacturer = manufacturer;
	}

	public Part(Parcel in){
		model = in.readString();
		manufacturer = in.readString();
	}

	public static final Creator<Part> CREATOR = new Creator<Part>() {
		@Override
		public Part createFromParcel(Parcel in) {
			return new Part(in);
		}

		@Override
		public Part[] newArray(int size) {
			return new Part[size];
		}
	};

	public String getName() {
		return manufacturer + " " + model;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Part part = (Part) o;

		if (getModel() != null ? !getModel().equals(part.getModel()) : part.getModel() != null)
			return false;
		return getManufacturer() != null ? getManufacturer().equals(part.getManufacturer()) : part.getManufacturer() == null;
	}

	@Override
	public int hashCode() {
		int result = getModel() != null ? getModel().hashCode() : 0;
		result = 31 * result + (getManufacturer() != null ? getManufacturer().hashCode() : 0);
		return result;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(model);
		dest.writeString(manufacturer);
	}
}
