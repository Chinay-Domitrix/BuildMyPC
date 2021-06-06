package com.example.buildmypc.ui.parts.parts;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public final class Cooler extends Part implements Parcelable {
//	private final String manufacturer;
//	private final String model;
	private final String rpm;
	private final String noiseLevel;
	private final int height;
	private final ArrayList<String> socketSupport;
	private final boolean waterCooled;
	private final boolean fanless;

	public Cooler(String model, String manufacturer) {
		super(model, manufacturer);
		this.rpm = null;
		this.noiseLevel = null;
		this.height = -1;
		this.socketSupport = null;
		this.waterCooled = false;
		this.fanless = false;
	}

	public Cooler(String model, String manufacturer, String rpm, String noiseLevel, int height, ArrayList<String> socketSupport, boolean waterCooled, boolean fanless) {
		super(model, manufacturer);
		this.rpm = rpm;
		this.noiseLevel = noiseLevel;
		this.height = height;
		this.socketSupport = socketSupport;
		this.waterCooled = waterCooled;
		this.fanless = fanless;
	}

//	public String getManufacturer() {
//		return manufacturer;
//	}
//
//	public String getModel() {
//		return model;
//	}

	public String getRpm() {
		return rpm;
	}

	public String getNoiseLevel() {
		return noiseLevel;
	}

	public int getHeight() {
		return height;
	}

	public ArrayList<String> getSocketSupport() {
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
		if (getManufacturer() != null ? !getManufacturer().equals(cooler.getManufacturer()) : cooler.getManufacturer() != null)
			return false;
		if (getModel() != null ? !getModel().equals(cooler.getModel()) : cooler.getModel() != null)
			return false;
		if (getRpm() != null ? !getRpm().equals(cooler.getRpm()) : cooler.getRpm() != null)
			return false;
		if (getNoiseLevel() != null ? !getNoiseLevel().equals(cooler.getNoiseLevel()) : cooler.getNoiseLevel() != null)
			return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		return getSocketSupport().equals(cooler.getSocketSupport());
	}

	@Override
	public int hashCode() {
		int result = getManufacturer() != null ? getManufacturer().hashCode() : 0;
		result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
		result = 31 * result + (getRpm() != null ? getRpm().hashCode() : 0);
		result = 31 * result + (getNoiseLevel() != null ? getNoiseLevel().hashCode() : 0);
		result = 31 * result + getHeight();
		result = 31 * result + getSocketSupport().hashCode();
		result = 31 * result + (isWaterCooled() ? 1 : 0);
		result = 31 * result + (isFanless() ? 1 : 0);
		return result;
	}

	@RequiresApi(api = Build.VERSION_CODES.Q)
	public Cooler(Parcel in) {
		super(in.readString(), in.readString());
		rpm = in.readString();
		noiseLevel = in.readString();
		height = in.readInt();
		socketSupport = new ArrayList<>();
		in.readStringList(socketSupport);
		waterCooled = in.readBoolean();
		fanless = in.readBoolean();
	}

	@RequiresApi(api = Build.VERSION_CODES.Q)
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(rpm);
		dest.writeString(noiseLevel);
		dest.writeInt(height);
		dest.writeStringList(socketSupport);
		dest.writeBoolean(waterCooled);
		dest.writeBoolean(fanless);
	}


}
