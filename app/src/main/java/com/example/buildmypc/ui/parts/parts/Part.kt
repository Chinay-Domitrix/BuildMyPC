package com.example.buildmypc.ui.parts.parts

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

// the start of the part hierarchy
open class Part : Parcelable {
	var model: String?
	var manufacturer: String?

	constructor(model: String?, manufacturer: String?) {
		this.model = model
		this.manufacturer = manufacturer
	}

	constructor(`in`: Parcel) {
		model = `in`.readString()
		manufacturer = `in`.readString()
	}

	val name
		get() = "$manufacturer $model"

	// returns the base number of params -- will be overridden for every subclass of Part
	open val paramCount = 2

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		val part = other as Part
		if (if (model != null) model != part.model else part.model != null) return false
		return if (manufacturer != null) manufacturer == part.manufacturer else part.manufacturer == null
	}

	override fun hashCode() =
		31 * (if (model != null) model.hashCode() else 0) + if (manufacturer != null) manufacturer.hashCode() else 0

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) {
		dest.writeString(model)
		dest.writeString(manufacturer)
	}

	companion object {
		@JvmField
		val CREATOR = object : Creator<Part> {
			override fun createFromParcel(`in`: Parcel) = Part(`in`)

			override fun newArray(size: Int): Array<Part?> = arrayOfNulls(size)
		}
	}

	override fun toString() = "$manufacturer $model"
}