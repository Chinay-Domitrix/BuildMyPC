package com.example.buildmypc.ui;

public class CPU extends InternalPart {
	private double boostClockSpeed; // in ghz
	private boolean hasCooler;
	private double regClockSpeed; // in ghz
	private double coreCount;
	private String family;
//	private boolean incEcc; // boolean for "includes error-correction code"
	private boolean incIntGPU; // boolean for "includes internal gpu"
	private int maxMemSupport; // in gb
	private String microarchitecture;
	private String series;
	private boolean hasSMT; // boolean for "has simultaneous multithreading"
	private String socket;
	private int tdpW; // thermal design power wattage (how much of a beating the computer is usually able to take, measured in watts)

//	public CPU(String model, String manufacturer, double boostClockSpeed, boolean hasCooler, double regClockSpeed, double coreCount, String family, boolean incEcc, boolean incIntGPU, int maxMemSupport, String microarchitecture, String series, boolean hasSMT, String socket, int tdpW) {
//		super(model, manufacturer);
//		this.boostClockSpeed = boostClockSpeed;
//		this.hasCooler = hasCooler;
//		this.regClockSpeed = regClockSpeed;
//		this.coreCount = coreCount;
//		this.family = family;
//		this.incEcc = incEcc;
//		this.incIntGPU = incIntGPU;
//		this.maxMemSupport = maxMemSupport;
//		this.microarchitecture = microarchitecture;
//		this.series = series;
//		this.hasSMT = hasSMT;
//		this.socket = socket;
//		this.tdpW = tdpW;
//	}


	public CPU(String model, String manufacturer, boolean hasEcc) {
		super(model, manufacturer, hasEcc);
	}

	public double getBoostClockSpeed() {
		return boostClockSpeed;
	}

	public void setBoostClockSpeed(double boostClockSpeed) {
		this.boostClockSpeed = boostClockSpeed;
	}

	public boolean isHasCooler() {
		return hasCooler;
	}

	public void setHasCooler(boolean hasCooler) {
		this.hasCooler = hasCooler;
	}

	public double getRegClockSpeed() {
		return regClockSpeed;
	}

	public void setRegClockSpeed(double regClockSpeed) {
		this.regClockSpeed = regClockSpeed;
	}

	public double getCoreCount() {
		return coreCount;
	}

	public void setCoreCount(double coreCount) {
		this.coreCount = coreCount;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public boolean isIncIntGPU() {
		return incIntGPU;
	}

	public void setIncIntGPU(boolean incIntGPU) {
		this.incIntGPU = incIntGPU;
	}

	public int getMaxMemSupport() {
		return maxMemSupport;
	}

	public void setMaxMemSupport(int maxMemSupport) {
		this.maxMemSupport = maxMemSupport;
	}

	public String getMicroarchitecture() {
		return microarchitecture;
	}

	public void setMicroarchitecture(String microarchitecture) {
		this.microarchitecture = microarchitecture;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public boolean isHasSMT() {
		return hasSMT;
	}

	public void setHasSMT(boolean hasSMT) {
		this.hasSMT = hasSMT;
	}

	public String getSocket() {
		return socket;
	}

	public void setSocket(String socket) {
		this.socket = socket;
	}

	public int getTdpW() {
		return tdpW;
	}

	public void setTdpW(int tdpW) {
		this.tdpW = tdpW;
	}
}
