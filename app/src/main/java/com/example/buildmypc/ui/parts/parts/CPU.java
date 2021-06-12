package com.example.buildmypc.ui.parts.parts;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class CPU extends Part implements Parcelable {
	public static final Creator<CPU> CREATOR = new Creator<CPU>() {
		@NotNull
		@Contract("_ -> new")
		@Override
		public CPU createFromParcel(Parcel in) {
			return new CPU(in);
		}

		@NotNull
		@Contract(value = "_ -> new", pure = true)
		@Override
		public CPU[] newArray(int size) {
			return new CPU[size];
		}
	};
	private final int coreCount;
	private final double coreClock;
	private final double boostClock;
	private final int tdp;
	private final String series;
	private final String microarchitecture;
	private final String coreFamily;
	private final String socket;
	private final boolean iGPU;
	private final int maxMemory;
	private final boolean ecc;
	private final boolean cooler;
	private final boolean smt;

	public CPU(String model, String manufacturer) {
		super(model, manufacturer);
		this.coreCount = -1;
		this.coreClock = -1;
		this.boostClock = -1;
		this.tdp = -1;
		this.series = null;
		this.microarchitecture = null;
		this.coreFamily = null;
		this.socket = null;
		this.iGPU = false;
		this.maxMemory = -1;
		this.ecc = false;
		this.cooler = false;
		this.smt = false;
	}

	public CPU(String model, String manufacturer, int coreCount, double coreClock, double boostClock, int tdp, String series, String microarchitecture, String coreFamily, String socket, boolean iGPU, int maxMemory, boolean ecc, boolean cooler, boolean smt) {
		super(model, manufacturer);
		this.coreCount = coreCount;
		this.coreClock = coreClock;
		this.boostClock = boostClock;
		this.tdp = tdp;
		this.series = series;
		this.microarchitecture = microarchitecture;
		this.coreFamily = coreFamily;
		this.socket = socket;
		this.iGPU = iGPU;
		this.maxMemory = maxMemory;
		this.ecc = ecc;
		this.cooler = cooler;
		this.smt = smt;
	}

	public CPU(@NotNull Parcel in) {
		super(in.readString(), in.readString());
		coreCount = in.readInt();
		coreClock = in.readDouble();
		boostClock = in.readDouble();
		tdp = in.readInt();
		series = in.readString();
		microarchitecture = in.readString();
		coreFamily = in.readString();
		socket = in.readString();
		iGPU = in.readBoolean();
		maxMemory = in.readInt();
		ecc = in.readBoolean();
		cooler = in.readBoolean();
		smt = in.readBoolean();
	}

	public int getCoreCount() {
		return coreCount;
	}

	public double getCoreClock() {
		return coreClock;
	}

	public double getBoostClock() {
		return boostClock;
	}

	public int getTdp() {
		return tdp;
	}

	public String getSeries() {
		return series;
	}

	public String getMicroarchitecture() {
		return microarchitecture;
	}

	public String getCoreFamily() {
		return coreFamily;
	}

	public String getSocket() {
		return socket;
	}

	public boolean isiGPU() {
		return iGPU;
	}

	public int getMaxMemory() {
		return maxMemory;
	}

	public boolean isEcc() {
		return ecc;
	}

	public boolean isCooler() {
		return cooler;
	}

	public boolean isSmt() {
		return smt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CPU cpu = (CPU) o;

		if (getCoreCount() != cpu.getCoreCount()) return false;
		if (Double.compare(cpu.getCoreClock(), getCoreClock()) != 0) return false;
		if (Double.compare(cpu.getBoostClock(), getBoostClock()) != 0) return false;
		if (getTdp() != cpu.getTdp()) return false;
		if (isiGPU() != cpu.isiGPU()) return false;
		if (getMaxMemory() != cpu.getMaxMemory()) return false;
		if (isEcc() != cpu.isEcc()) return false;
		if (isCooler() != cpu.isCooler()) return false;
		if (isSmt() != cpu.isSmt()) return false;
		if (getManufacturer() != null ? !getManufacturer().equals(cpu.getManufacturer()) : cpu.getManufacturer() != null)
			return false;
		if (getModel() != null ? !getModel().equals(cpu.getModel()) : cpu.getModel() != null)
			return false;
		if (getSeries() != null ? !getSeries().equals(cpu.getSeries()) : cpu.getSeries() != null)
			return false;
		if (getMicroarchitecture() != null ? !getMicroarchitecture().equals(cpu.getMicroarchitecture()) : cpu.getMicroarchitecture() != null)
			return false;
		if (getCoreFamily() != null ? !getCoreFamily().equals(cpu.getCoreFamily()) : cpu.getCoreFamily() != null)
			return false;
		return getSocket() != null ? getSocket().equals(cpu.getSocket()) : cpu.getSocket() == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = getManufacturer() != null ? getManufacturer().hashCode() : 0;
		result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
		result = 31 * result + getCoreCount();
		temp = Double.doubleToLongBits(getCoreClock());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(getBoostClock());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + getTdp();
		result = 31 * result + (getSeries() != null ? getSeries().hashCode() : 0);
		result = 31 * result + (getMicroarchitecture() != null ? getMicroarchitecture().hashCode() : 0);
		result = 31 * result + (getCoreFamily() != null ? getCoreFamily().hashCode() : 0);
		result = 31 * result + (getSocket() != null ? getSocket().hashCode() : 0);
		result = 31 * result + (isiGPU() ? 1 : 0);
		result = 31 * result + getMaxMemory();
		result = 31 * result + (isEcc() ? 1 : 0);
		result = 31 * result + (isCooler() ? 1 : 0);
		result = 31 * result + (isSmt() ? 1 : 0);
		return result;
	}

	@Override
	public void writeToParcel(@NotNull Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeInt(coreCount);
		dest.writeDouble(coreClock);
		dest.writeDouble(boostClock);
		dest.writeInt(tdp);
		dest.writeString(series);
		dest.writeString(microarchitecture);
		dest.writeString(coreFamily);
		dest.writeString(socket);
		dest.writeBoolean(iGPU);
		dest.writeInt(maxMemory);
		dest.writeBoolean(ecc);
		dest.writeBoolean(cooler);
		dest.writeBoolean(smt);
	}
}