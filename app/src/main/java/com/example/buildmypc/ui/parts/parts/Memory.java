package com.example.buildmypc.ui.parts.parts;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Memory extends Part implements Parcelable {

	private boolean hasECC;
	private int latencyCAS; // backwards to respect carrot case
	private int ddrGen; // pretty much always 4
	private int firstWordLatency; // in nanoseconds
	private String formFactor;
	private double heatSpreader;
	private int moduleSize; // in gb
	private int moduleCount; // regular old integer
	private int speed; // in mhz
	private String timing;
	private double voltage;

	public Memory(String model, String manufacturer) {
		super(model, manufacturer);
	}

	public boolean getHasECC() {
		return hasECC;
	}

	public void setHasECC(boolean hasECC) {
		this.hasECC = hasECC;
	}

	public int getLatencyCAS() {
		return latencyCAS;
	}

	public void setLatencyCAS(int latencyCAS) {
		this.latencyCAS = latencyCAS;
	}

	public int getDdrGen() {
		return ddrGen;
	}

	public void setDdrGen(int ddrGen) {
		this.ddrGen = ddrGen;
	}

	public int getFirstWordLatency() {
		return firstWordLatency;
	}

	public void setFirstWordLatency(int firstWordLatency) {
		this.firstWordLatency = firstWordLatency;
	}

	public String getFormFactor() {
		return formFactor;
	}

	public void setFormFactor(String formFactor) {
		this.formFactor = formFactor;
	}

	public double getHeatSpreader() {
		return heatSpreader;
	}

	public void setHeatSpreader(double heatSpreader) {
		this.heatSpreader = heatSpreader;
	}

	public int getModuleSize() {
		return moduleSize;
	}

	public void setModuleSize(int moduleSize) {
		this.moduleSize = moduleSize;
	}

	public int getModuleCount() {
		return moduleCount;
	}

	public void setModuleCount(int moduleCount) {
		this.moduleCount = moduleCount;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public double getVoltage() {
		return voltage;
	}

	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Memory memory = (Memory) o;

		if (Boolean.compare(memory.getHasECC(), getHasECC()) != 0) return false;
		if (getLatencyCAS() != memory.getLatencyCAS()) return false;
		if (getDdrGen() != memory.getDdrGen()) return false;
		if (getFirstWordLatency() != memory.getFirstWordLatency()) return false;
		if (Double.compare(memory.getHeatSpreader(), getHeatSpreader()) != 0) return false;
		if (getModuleSize() != memory.getModuleSize()) return false;
		if (getModuleCount() != memory.getModuleCount()) return false;
		if (getSpeed() != memory.getSpeed()) return false;
		if (Double.compare(memory.getVoltage(), getVoltage()) != 0) return false;
		if (getFormFactor() != null ? !getFormFactor().equals(memory.getFormFactor()) : memory.getFormFactor() != null)
			return false;
		return getTiming() != null ? getTiming().equals(memory.getTiming()) : memory.getTiming() == null;
	}

	@Override // TODO fix this
	public int hashCode() {
		int result = super.hashCode();
		long temp;
		result = 31 * result + (getHasECC() ? 1 : 0);
		result = 31 * result + getLatencyCAS();
		result = 31 * result + getDdrGen();
		result = 31 * result + getFirstWordLatency();
		result = 31 * result + (getFormFactor() != null ? getFormFactor().hashCode() : 0);
		temp = Double.doubleToLongBits(getHeatSpreader());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + getModuleSize();
		result = 31 * result + getModuleCount();
		result = 31 * result + getSpeed();
		result = 31 * result + (getTiming() != null ? getTiming().hashCode() : 0);
		temp = Double.doubleToLongBits(getVoltage());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@RequiresApi(api = Build.VERSION_CODES.Q)
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeBoolean(hasECC);
		dest.writeInt(latencyCAS);
		dest.writeInt(ddrGen);
		dest.writeInt(firstWordLatency);
		dest.writeString(formFactor);
		dest.writeDouble(heatSpreader);
		dest.writeInt(moduleSize);
		dest.writeInt(moduleCount);
		dest.writeInt(speed);
		dest.writeString(timing);
		dest.writeDouble(voltage);
	}

	@RequiresApi(api = Build.VERSION_CODES.Q)
	public Memory(Parcel in){
		super(in.readString(), in.readString());
		hasECC = in.readBoolean();
		latencyCAS = in.readInt();
		ddrGen = in.readInt();
		firstWordLatency = in.readInt();
		formFactor = in.readString();
		heatSpreader = in.readDouble();
		moduleSize = in.readInt();
		moduleCount = in.readInt();
		speed = in.readInt();
		timing = in.readString();
		voltage = in.readDouble();
	}
}
