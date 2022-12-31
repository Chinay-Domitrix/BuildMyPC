package com.example.buildmypc.ui.parts.parts

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

open class Accessory : Part, Parcelable {
	constructor(model: String?, manufacturer: String?) : super(model, manufacturer)
	protected constructor(`in`: Parcel?) : super(`in`!!)

	override fun writeToParcel(dest: Parcel, flags: Int) = super.writeToParcel(dest, flags)

	override fun describeContents() = 0

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false
		if (!super.equals(other)) return false
		return true
	}

	override fun hashCode() = super.hashCode()

	companion object {
		@JvmField
		val CREATOR = object : Creator<Accessory> {
			override fun createFromParcel(`in`: Parcel) = Accessory(`in`)

			override fun newArray(size: Int): Array<Accessory?> = arrayOfNulls(size)
		}
	}
}