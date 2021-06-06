package com.example.buildmypc.ui.parts.parts;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Storage extends Part implements Parcelable {

	double formFactor;
	private int cacheSizeMB; // in mb
	private int capacity; // in MB -> 2TB is 2000, 1.5TB is 1500, 512 GB is 512
	private String sataInterface; // which SATA does this part connect to
	private boolean nvme; // NMVe, or non-volatile memory express
	private int rpm; // only applies for HDDs
	private String type; // either HDD or SSD

	public Storage(String model, String manufacturer) {
		super(model, manufacturer);
	}

	public int getCacheSizeMB() {
		return cacheSizeMB;
	}

	public void setCacheSizeMB(int cacheSizeMB) {
		this.cacheSizeMB = cacheSizeMB;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getFormFactor() {
		return formFactor;
	}

	public void setFormFactor(double formFactor) {
		this.formFactor = formFactor;
	}

	public String getSataInterface() {
		return sataInterface;
	}

	public void setSataInterface(String sataInterface) {
		this.sataInterface = sataInterface;
	}

	public boolean getNvme() {
		return nvme;
	}

	public void setNvme(boolean nvme) {
		this.nvme = nvme;
	}

	public int getRpm() {
		return rpm;
	}

	public void setRpm(int rpm) {
		this.rpm = rpm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Storage storage = (Storage) o;

		if (Double.compare(storage.getFormFactor(), getFormFactor()) != 0) return false;
		if (getCacheSizeMB() != storage.getCacheSizeMB()) return false;
		if (getCapacity() != storage.getCapacity()) return false;
		if (getNvme() != storage.getNvme()) return false;
		if (getRpm() != storage.getRpm()) return false;
		if (getSataInterface() != null ? !getSataInterface().equals(storage.getSataInterface()) : storage.getSataInterface() != null)
			return false;
		return getType() != null ? getType().equals(storage.getType()) : storage.getType() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(getFormFactor());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + getCacheSizeMB();
		result = 31 * result + getCapacity();
		result = 31 * result + (getSataInterface() != null ? getSataInterface().hashCode() : 0);
		result = 31 * result + (getNvme() ? 1 : 0);
		result = 31 * result + getRpm();
		result = 31 * result + (getType() != null ? getType().hashCode() : 0);
		return result;
	}

	@RequiresApi(api = Build.VERSION_CODES.Q)
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeDouble(formFactor);
		dest.writeInt(cacheSizeMB);
		dest.writeInt(capacity);
		dest.writeString(sataInterface);
		dest.writeBoolean(nvme);
		dest.writeInt(rpm);
		dest.writeString(type);
	}

	@RequiresApi(api = Build.VERSION_CODES.Q)
	public Storage(Parcel in){
		super(in.toString(), in.toString());
		formFactor = in.readDouble();
		cacheSizeMB = in.readInt();
		capacity = in.readInt();
		sataInterface = in.readString();
		nvme = in.readBoolean();
		rpm = in.readInt();
		type = in.readString();
	}
}
