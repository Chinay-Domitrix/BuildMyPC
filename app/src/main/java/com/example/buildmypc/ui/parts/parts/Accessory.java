package com.example.buildmypc.ui.parts.parts;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Accessory extends Part implements Parcelable {
	public static final Creator<Accessory> CREATOR = new Creator<Accessory>() {
		@NotNull
		@Contract("_ -> new")
		@Override
		public Accessory createFromParcel(Parcel in) {
			return new Accessory(in);
		}

		@NotNull
		@Contract(value = "_ -> new", pure = true)
		@Override
		public Accessory[] newArray(int size) {
			return new Accessory[size];
		}
	};

	public Accessory(String model, String manufacturer) {
		super(model, manufacturer);
	}

	protected Accessory(Parcel in) {
		super(in);
	}

	@Override
	public void writeToParcel(@NotNull Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
	}

	@Override
	public int describeContents() {
		return 0;
	}
}
