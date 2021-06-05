package com.example.buildmypc.ui.parts.parts;

import java.util.Objects;

import static java.lang.Double.compare;
import static java.lang.Double.doubleToLongBits;
import static java.util.Arrays.asList;

public final class CPU {
	private final String manufacturer;
	private final String model;
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

	public CPU(String manufacturer, String model) {
		this.manufacturer = manufacturer;
		this.model = model;
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

	public CPU(String manufacturer, String model, int coreCount, double coreClock, double boostClock, int tdp, String series, String microarchitecture, String coreFamily, String socket, boolean iGPU, int maxMemory, boolean ecc, boolean cooler, boolean smt) {
		this.manufacturer = manufacturer;
		this.model = model;
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

	public String getManufacturer() {
		return manufacturer;
	}

	public String getModel() {
		return model;
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
		if ((o == null) || (getClass() != o.getClass())) return false;
		CPU cpu = (CPU) o;
		if ((getCoreCount() != cpu.getCoreCount()) ||
				(compare(cpu.getCoreClock(), getCoreClock()) != 0) ||
				(compare(cpu.getBoostClock(), getBoostClock()) != 0) ||
				(getTdp() != cpu.getTdp()) ||
				(isiGPU() != cpu.isiGPU()) ||
				(getMaxMemory() != cpu.getMaxMemory()) ||
				(isEcc() != cpu.isEcc()) ||
				(isCooler() != cpu.isCooler()) ||
				(isSmt() != cpu.isSmt()) ||
				!getManufacturer().equals(cpu.getManufacturer()) ||
				!getModel().equals(cpu.getModel()) ||
				!Objects.equals(getSeries(), cpu.getSeries()) ||
				!Objects.equals(getMicroarchitecture(), cpu.getMicroarchitecture()) ||
				!Objects.equals(getCoreFamily(), cpu.getCoreFamily())) return false;
		assert getSocket() != null;
		return getSocket().equals(cpu.getSocket());
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = getManufacturer().hashCode();
		for (int i : new int[]{getModel().hashCode(), getCoreCount()}) result = (31 * result) + i;
		temp = doubleToLongBits(getCoreClock());
		result = (31 * result) + (int) (temp ^ (temp >>> 32));
		temp = doubleToLongBits(getBoostClock());
		result = (31 * result) + (int) (temp ^ (temp >>> 32));
		result = (31 * result) + getTdp();
		for (String s : asList(getSeries(), getMicroarchitecture(), getCoreFamily(), getSocket()))
			result = (31 * result) + s.hashCode();
		for (int i : new int[]{isiGPU() ? 1 : 0, getMaxMemory(), isEcc() ? 1 : 0, isCooler() ? 1 : 0, isSmt() ? 1 : 0})
			result = (31 * result) + i;
		return result;
	}
}
