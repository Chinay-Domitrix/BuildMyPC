package com.example.buildmypc.ui.parts.parts

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class CountedString : Parcelable {
	var name: String?
	private var amount: Int

	constructor(name: String?, amount: Int) {
		this.name = name
		this.amount = amount
	}

	constructor(`in`: Parcel) {
		name = `in`.readString()
		amount = `in`.readInt()
	}

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) {
		dest.writeString(name)
		dest.writeInt(amount)
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		val that = other as CountedString
		if (amount != that.amount) return false
		return if (name != null) name == that.name else that.name == null
	}

	override fun hashCode(): Int {
		var result = if (name != null) name.hashCode() else 0
		result = 31 * result + amount
		return result
	}

	companion object {
		@JvmField
		val CREATOR = object : Creator<CountedString?> {
			override fun createFromParcel(`in`: Parcel) = CountedString(`in`)

			override fun newArray(size: Int): Array<CountedString?> = arrayOfNulls(size)
		}
	}
}