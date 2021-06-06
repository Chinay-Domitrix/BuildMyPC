package com.example.buildmypc.ui.parts.parts;

import java.util.ArrayList;
import java.util.Arrays;

public class Monitor extends Part {

	private String aspectRatio;
	private String brightness; // in candla per square meter
	private boolean isCurved;
	private ArrayList<String> frameSync;
	private String hdrTier;
	private boolean builtInSpeakers;
	private MonitorInterface monitorInterfaces;
	private String panelType;
	private double refreshRate; // in hertz
	private int[] resolution; // 2-element integer array; e.g. {1920, 1080}
	private int responseTimeMs; // in milliseconds
	private double screenSizeIn; // in square inches
	private double viewingAngle;
	private boolean isWidescreen;

	public Monitor(String model, String manufacturer) {
		super(model, manufacturer);
	}

	public String getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(String aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public String getBrightness() {
		return brightness;
	}

	public void setBrightness(String brightness) {
		this.brightness = brightness;
	}

	public boolean isCurved() {
		return isCurved;
	}

	public void setCurved(boolean curved) {
		isCurved = curved;
	}

	public ArrayList<String> getFrameSync() {
		return frameSync;
	}

	public void setFrameSync(ArrayList<String> frameSync) {
		this.frameSync = frameSync;
	}

	public String getHdrTier() {
		return hdrTier;
	}

	public void setHdrTier(String hdrTier) {
		this.hdrTier = hdrTier;
	}

	public boolean isBuiltInSpeakers() {
		return builtInSpeakers;
	}

	public void setBuiltInSpeakers(boolean builtInSpeakers) {
		this.builtInSpeakers = builtInSpeakers;
	}

	public MonitorInterface getMonitorInterfaces() {
		return monitorInterfaces;
	}

	public void setMonitorInterfaces(MonitorInterface monitorInterfaces) {
		this.monitorInterfaces = monitorInterfaces;
	}

	public String getPanelType() {
		return panelType;
	}

	public void setPanelType(String panelType) {
		this.panelType = panelType;
	}

	public double getRefreshRate() {
		return refreshRate;
	}

	public void setRefreshRate(double refreshRate) {
		this.refreshRate = refreshRate;
	}

	public int[] getResolution() {
		return resolution;
	}

	public void setResolution(int[] resolution) {
		this.resolution = resolution;
	}

	public int getResponseTimeMs() {
		return responseTimeMs;
	}

	public void setResponseTimeMs(int responseTimeMs) {
		this.responseTimeMs = responseTimeMs;
	}

	public double getScreenSizeIn() {
		return screenSizeIn;
	}

	public void setScreenSizeIn(double screenSizeIn) {
		this.screenSizeIn = screenSizeIn;
	}

	public double getViewingAngle() {
		return viewingAngle;
	}

	public void setViewingAngle(double viewingAngle) {
		this.viewingAngle = viewingAngle;
	}

	public boolean isWidescreen() {
		return isWidescreen;
	}

	public void setWidescreen(boolean widescreen) {
		isWidescreen = widescreen;
	}

	public class MonitorInterface {
		private String name;
		private String count;

		public MonitorInterface(String name, String count) {
			this.name = name;
			this.count = count;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Monitor monitor = (Monitor) o;

		if (isCurved() != monitor.isCurved()) return false;
		if (isBuiltInSpeakers() != monitor.isBuiltInSpeakers()) return false;
		if (Double.compare(monitor.getRefreshRate(), getRefreshRate()) != 0) return false;
		if (getResponseTimeMs() != monitor.getResponseTimeMs()) return false;
		if (Double.compare(monitor.getScreenSizeIn(), getScreenSizeIn()) != 0) return false;
		if (Double.compare(monitor.getViewingAngle(), getViewingAngle()) != 0) return false;
		if (isWidescreen() != monitor.isWidescreen()) return false;
		if (getAspectRatio() != null ? !getAspectRatio().equals(monitor.getAspectRatio()) : monitor.getAspectRatio() != null)
			return false;
		if (getBrightness() != null ? !getBrightness().equals(monitor.getBrightness()) : monitor.getBrightness() != null)
			return false;
		if (getFrameSync() != null ? !getFrameSync().equals(monitor.getFrameSync()) : monitor.getFrameSync() != null)
			return false;
		if (getHdrTier() != null ? !getHdrTier().equals(monitor.getHdrTier()) : monitor.getHdrTier() != null)
			return false;
		if (getMonitorInterfaces() != null ? !getMonitorInterfaces().equals(monitor.getMonitorInterfaces()) : monitor.getMonitorInterfaces() != null)
			return false;
		if (getPanelType() != null ? !getPanelType().equals(monitor.getPanelType()) : monitor.getPanelType() != null)
			return false;
		return Arrays.equals(getResolution(), monitor.getResolution());
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		long temp;
		result = 31 * result + (getAspectRatio() != null ? getAspectRatio().hashCode() : 0);
		result = 31 * result + (getBrightness() != null ? getBrightness().hashCode() : 0);
		result = 31 * result + (isCurved() ? 1 : 0);
		result = 31 * result + (getFrameSync() != null ? getFrameSync().hashCode() : 0);
		result = 31 * result + (getHdrTier() != null ? getHdrTier().hashCode() : 0);
		result = 31 * result + (isBuiltInSpeakers() ? 1 : 0);
		result = 31 * result + (getMonitorInterfaces() != null ? getMonitorInterfaces().hashCode() : 0);
		result = 31 * result + (getPanelType() != null ? getPanelType().hashCode() : 0);
		temp = Double.doubleToLongBits(getRefreshRate());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + Arrays.hashCode(getResolution());
		result = 31 * result + getResponseTimeMs();
		temp = Double.doubleToLongBits(getScreenSizeIn());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(getViewingAngle());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (isWidescreen() ? 1 : 0);
		return result;
	}
}
