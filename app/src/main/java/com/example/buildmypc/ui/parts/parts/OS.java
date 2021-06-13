package com.example.buildmypc.ui.parts.parts;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class OS extends Accessory implements Parcelable {
	public static final Creator<OS> CREATOR = new Creator<OS>() {
		@NotNull
		@Contract("_ -> new")
		@Override
		public OS createFromParcel(Parcel in) {
			return new OS(in);
		}

		@NotNull
		@Contract(value = "_ -> new", pure = true)
		@Override
		public OS[] newArray(int size) {
			return new OS[size];
		}
	};
	private String bitMode;
	private int maxMemSupport;
	private String type;
//	private String edition;
	private String oem_retail;

	public OS(String model, String manufacturer) {
		super(model, manufacturer);
	}

	public OS(Parcel in) {
		super(in.readString(), in.readString());
		bitMode = in.readString();
		maxMemSupport = in.readInt();
		type = in.readString();
//		edition = in.readString();
		oem_retail = in.readString();
	}

	public OS(String model, String manufacturer, String bitMode, int maxMemSupport, String type, String oem_retail) {
		super(model, manufacturer);
		this.bitMode = bitMode;
		this.maxMemSupport = maxMemSupport;
		this.type = type;
		this.oem_retail = oem_retail;
	}

	public String getBitMode() {
		return bitMode;
	}

	public void setBitMode(String bitMode) {
		this.bitMode = bitMode;
	}

	public int getMaxMemSupport() {
		return maxMemSupport;
	}

	public void setMaxMemSupport(int maxMemSupport) {
		this.maxMemSupport = maxMemSupport;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//	public String getEdition() {
//		return edition;
//	}
//
//	public void setEdition(String edition) {
//		this.edition = edition;
//	}

	public String getOem_retail() {
		return oem_retail;
	}

	public void setOem_retail(String oem_retail) {
		this.oem_retail = oem_retail;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		OS os = (OS) o;

		if (getMaxMemSupport() != os.getMaxMemSupport()) return false;
		if (getBitMode() != null ? !getBitMode().equals(os.getBitMode()) : os.getBitMode() != null)
			return false;
		if (getType() != null ? !getType().equals(os.getType()) : os.getType() != null)
			return false;
		return getOem_retail() != null ? getOem_retail().equals(os.getOem_retail()) : os.getOem_retail() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getBitMode() != null ? getBitMode().hashCode() : 0);
		result = 31 * result + getMaxMemSupport();
		result = 31 * result + (getType() != null ? getType().hashCode() : 0);
		result = 31 * result + (getOem_retail() != null ? getOem_retail().hashCode() : 0);
		return result;
	}

	@Override
	public void writeToParcel(@NotNull Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(bitMode);
		dest.writeInt(maxMemSupport);
		dest.writeString(type);
//		dest.writeString(edition);
		dest.writeString(oem_retail);
	}
}