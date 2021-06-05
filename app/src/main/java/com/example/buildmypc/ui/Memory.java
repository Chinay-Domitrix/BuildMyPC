package com.example.buildmypc.ui;

public class Memory extends InternalPart {

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

	public Memory(String model, String manufacturer, boolean hasEcc) {
		super(model, manufacturer, hasEcc);
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
}
