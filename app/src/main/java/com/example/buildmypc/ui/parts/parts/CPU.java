package com.example.buildmypc.ui.parts.parts;

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
}
