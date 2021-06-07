package com.example.buildmypc.ui.parts.parts;

import android.os.Parcel;
import android.os.Parcelable;

public class CountedString implements Parcelable {
	private String name;
	private int amount;

	public CountedString(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}

	public static final Creator<CountedString> CREATOR = new Creator<CountedString>() {
		@Override
		public CountedString createFromParcel(Parcel in) {
			return new CountedString(in);
		}

		@Override
		public CountedString[] newArray(int size) {
			return new CountedString[size];
		}
	};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(amount);
	}

	public CountedString(Parcel in){
		name = in.readString();
		amount = in.readInt();
	}
}