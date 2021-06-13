package com.example.buildmypc.ui.parts.parts;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Monitor extends Part implements Parcelable {
	public static final Creator<Monitor> CREATOR = new Creator<Monitor>() {
		@NotNull
		@Contract("_ -> new")
		@Override
		public Monitor createFromParcel(Parcel in) {
			return new Monitor(in);
		}

		@NotNull
		@Contract(value = "_ -> new", pure = true)
		@Override
		public Monitor[] newArray(int size) {
			return new Monitor[size];
		}
	};
	private String aspectRatio;
	private int brightness; // in candla per square meter
	private boolean isCurved;
	private ArrayList<String> frameSync;
	private String hdrTier;
	private boolean builtInSpeakers;
	private ArrayList<CountedString> monitorInterfaces;
	private String panelType;
	private double refreshRate; // in hertz
	private int[] resolution; // 2-element integer array; e.g. {1920, 1080}
	private int responseTimeMs; // in milliseconds
	private double screenSizeIn; // in square inches
	private String viewingAngle;
	private boolean isWidescreen;

	public Monitor(String model, String manufacturer) {
		super(model, manufacturer);
	}

	public Monitor(@Nullable String model, @Nullable String manufacturer, String aspectRatio, int brightness, boolean isCurved, ArrayList<String> frameSync, String hdrTier, boolean builtInSpeakers, ArrayList<CountedString> monitorInterfaces, String panelType, double refreshRate, ArrayList<?> resolution, int responseTimeMs, double screenSizeIn, String viewingAngle, boolean isWidescreen) {
		super(model, manufacturer);
		this.aspectRatio = aspectRatio;
		this.brightness = brightness;
		this.isCurved = isCurved;
		this.frameSync = frameSync;
		this.hdrTier = hdrTier;
		this.builtInSpeakers = builtInSpeakers;
		this.monitorInterfaces = monitorInterfaces;
		this.panelType = panelType;
		this.refreshRate = refreshRate;
		// this is a jank solution to Kotlin arrays not being translated well into Java arrays
		this.resolution = new int[2];
		for(int i = 0; i < 2; i++){
			this.resolution[i] = (int) resolution.get(i);
		}
		this.responseTimeMs = responseTimeMs;
		this.screenSizeIn = screenSizeIn;
		this.viewingAngle = viewingAngle;
		this.isWidescreen = isWidescreen;
	}

	public Monitor(@NotNull Parcel in) {
		super(in.readString(), in.readString());
		aspectRatio = in.readString();
		brightness = in.readInt();
		isCurved = in.readBoolean();
		frameSync = in.createStringArrayList();
		hdrTier = in.readString();
		builtInSpeakers = in.readBoolean();
		monitorInterfaces = in.createTypedArrayList(CountedString.CREATOR);
		panelType = in.readString();
		refreshRate = in.readDouble();
		resolution = in.createIntArray();
		responseTimeMs = in.readInt();
		screenSizeIn = in.readDouble();
		viewingAngle = in.readString();
		isWidescreen = in.readBoolean();
	}

	public ArrayList<CountedString> getMonitorInterfaces() {
		return monitorInterfaces;
	}

	public void setMonitorInterfaces(ArrayList<CountedString> monitorInterfaces) {
		this.monitorInterfaces = monitorInterfaces;
	}

	public String getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(String aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public int getBrightness() {
		return brightness;
	}

	public void setBrightness(int brightness) {
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

	public String getViewingAngle() {
		return viewingAngle;
	}

	public void setViewingAngle(String viewingAngle) {
		this.viewingAngle = viewingAngle;
	}

	public boolean isWidescreen() {
		return isWidescreen;
	}

	public void setWidescreen(boolean widescreen) {
		isWidescreen = widescreen;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Monitor monitor = (Monitor) o;

		if (getBrightness() != monitor.getBrightness()) return false;
		if (isCurved() != monitor.isCurved()) return false;
		if (isBuiltInSpeakers() != monitor.isBuiltInSpeakers()) return false;
		if (Double.compare(monitor.getRefreshRate(), getRefreshRate()) != 0) return false;
		if (getResponseTimeMs() != monitor.getResponseTimeMs()) return false;
		if (Double.compare(monitor.getScreenSizeIn(), getScreenSizeIn()) != 0) return false;
		if (isWidescreen() != monitor.isWidescreen()) return false;
		if (getAspectRatio() != null ? !getAspectRatio().equals(monitor.getAspectRatio()) : monitor.getAspectRatio() != null)
			return false;
		if (getFrameSync() != null ? !getFrameSync().equals(monitor.getFrameSync()) : monitor.getFrameSync() != null)
			return false;
		if (getHdrTier() != null ? !getHdrTier().equals(monitor.getHdrTier()) : monitor.getHdrTier() != null)
			return false;
		if (getMonitorInterfaces() != null ? !getMonitorInterfaces().equals(monitor.getMonitorInterfaces()) : monitor.getMonitorInterfaces() != null)
			return false;
		if (getPanelType() != null ? !getPanelType().equals(monitor.getPanelType()) : monitor.getPanelType() != null)
			return false;
		if (!Arrays.equals(getResolution(), monitor.getResolution())) return false;
		return getViewingAngle() != null ? getViewingAngle().equals(monitor.getViewingAngle()) : monitor.getViewingAngle() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		long temp;
		result = 31 * result + (getAspectRatio() != null ? getAspectRatio().hashCode() : 0);
		result = 31 * result + getBrightness();
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
		result = 31 * result + (getViewingAngle() != null ? getViewingAngle().hashCode() : 0);
		result = 31 * result + (isWidescreen() ? 1 : 0);
		return result;
	}

	@Override
	public void writeToParcel(@NotNull Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(aspectRatio);
		dest.writeInt(brightness);
		dest.writeBoolean(isCurved);
		dest.writeStringList(frameSync);
		dest.writeString(hdrTier);
		dest.writeBoolean(builtInSpeakers);
		dest.writeParcelableList(monitorInterfaces, flags);
		dest.writeString(panelType);
		dest.writeDouble(refreshRate);
		dest.writeIntArray(resolution); // 2 elements
		dest.writeInt(responseTimeMs);
		dest.writeDouble(screenSizeIn);
		dest.writeString(viewingAngle);
		dest.writeBoolean(isWidescreen);

	}
}