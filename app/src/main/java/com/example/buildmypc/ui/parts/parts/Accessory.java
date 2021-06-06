package com.example.buildmypc.ui.parts.parts;

import android.os.Parcel;
import android.os.Parcelable;

public class Accessory extends Part implements Parcelable {
	public Accessory(String model, String manufacturer) {
		super(model, manufacturer);
	}

	protected Accessory(Parcel in) {
		super(in);
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Accessory> CREATOR = new Creator<Accessory>() {
		@Override
		public Accessory createFromParcel(Parcel in) {
			return new Accessory(in);
		}

		@Override
		public Accessory[] newArray(int size) {
			return new Accessory[size];
		}
	};
}
