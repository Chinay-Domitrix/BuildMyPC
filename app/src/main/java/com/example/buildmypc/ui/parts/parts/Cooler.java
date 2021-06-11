package com.example.buildmypc.ui.parts.parts;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.Q;

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

	@RequiresApi(Q)
	public Cooler(@NotNull Parcel in) {
		super(in.readString(), in.readString());
		rpm = in.readString();
		noiseLevel = in.readString();
		height = in.readInt();
		socketSupport = in.createStringArrayList();
		waterCooled = in.readBoolean();
		fanless = in.readBoolean();
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
		if (!super.equals(o)) return false;

		Cooler cooler = (Cooler) o;

		if (getHeight() != cooler.getHeight()) return false;
		if (isWaterCooled() != cooler.isWaterCooled()) return false;
		if (isFanless() != cooler.isFanless()) return false;
		if (getRpm() != null ? !getRpm().equals(cooler.getRpm()) : cooler.getRpm() != null)
			return false;
		if (getNoiseLevel() != null ? !getNoiseLevel().equals(cooler.getNoiseLevel()) : cooler.getNoiseLevel() != null)
			return false;
		return getSocketSupport() != null ? getSocketSupport().equals(cooler.getSocketSupport()) : cooler.getSocketSupport() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getRpm() != null ? getRpm().hashCode() : 0);
		result = 31 * result + (getNoiseLevel() != null ? getNoiseLevel().hashCode() : 0);
		result = 31 * result + getHeight();
		result = 31 * result + (getSocketSupport() != null ? getSocketSupport().hashCode() : 0);
		result = 31 * result + (isWaterCooled() ? 1 : 0);
		result = 31 * result + (isFanless() ? 1 : 0);
		return result;
	}

	@RequiresApi(Q)
	@Override
	public void writeToParcel(@NotNull Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(rpm);
		dest.writeString(noiseLevel);
		dest.writeInt(height);
		dest.writeStringList(socketSupport);
		dest.writeBoolean(waterCooled);
		dest.writeBoolean(fanless);
	}

	@NonNull
	@NotNull
	@Override
	public String toString() {
		return "Cooler " + getModel() + " " + getManufacturer();
	}
}
