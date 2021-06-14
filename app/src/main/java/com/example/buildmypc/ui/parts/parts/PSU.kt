package com.example.buildmypc.ui.parts.parts

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import org.jetbrains.annotations.Contract
import java.util.*

class PSU : Part, Parcelable {
	var connectorList: ArrayList<CountedString?>? = null
	var efficiencyRating: String? = null
	var isHasFan = false
	var formFactor: String? = null
	var length // in millimeters
			= 0
	var modular: String? = null
	var wattage = 0

	constructor(model: String?, manufacturer: String?) : super(model, manufacturer)
	constructor(
		model: String?,
		manufacturer: String?,
		connectorList: ArrayList<CountedString?>?,
		efficiencyRating: String?,
		hasFan: Boolean,
		formFactor: String?,
		length: Int,
		modular: String?,
		wattage: Int
	) : super(model, manufacturer) {
		this.connectorList = connectorList
		this.efficiencyRating = efficiencyRating
		this.isHasFan = hasFan
		this.formFactor = formFactor
		this.length = length
		this.modular = modular
		this.wattage = wattage
	}

	constructor(`in`: Parcel) : super(`in`.readString(), `in`.readString()) {
		connectorList = `in`.createTypedArrayList(CountedString.CREATOR)
		efficiencyRating = `in`.readString()
		isHasFan = `in`.readBoolean()
		formFactor = `in`.readString()
		length = `in`.readInt()
		modular = `in`.readString()
		wattage = `in`.readInt()
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		if (!super.equals(other)) return false
		val psu = other as PSU
		if (isHasFan != psu.isHasFan) return false
		if (length != psu.length) return false
		if (wattage != psu.wattage) return false
		if (if (connectorList != null) connectorList != psu.connectorList else psu.connectorList != null) return false
		if (if (efficiencyRating != null) efficiencyRating != psu.efficiencyRating else psu.efficiencyRating != null) return false
		if (if (formFactor != null) formFactor != psu.formFactor else psu.formFactor != null) return false
		return if (modular != null) modular == psu.modular else psu.modular == null
	}

	override fun hashCode(): Int {
		var result = super.hashCode()
		result = 31 * result + if (connectorList != null) connectorList.hashCode() else 0
		result = 31 * result + if (efficiencyRating != null) efficiencyRating.hashCode() else 0
		result = 31 * result + if (isHasFan) 1 else 0
		result = 31 * result + if (formFactor != null) formFactor.hashCode() else 0
		result = 31 * result + length
		result = 31 * result + if (modular != null) modular.hashCode() else 0
		result = 31 * result + wattage
		return result
	}

	override fun writeToParcel(dest: Parcel, flags: Int) {
		super.writeToParcel(dest, flags)
		dest.writeParcelableList(connectorList, flags)
		dest.writeString(efficiencyRating)
		dest.writeBoolean(isHasFan)
		dest.writeString(formFactor)
		dest.writeInt(length)
		dest.writeString(modular)
		dest.writeInt(wattage)
	}

	override val paramCount: Int
		get() = 9

	companion object {
		// stands for "Power Supply Unit"
		@JvmField
		val CREATOR: Creator<PSU> = object : Creator<PSU> {
			@Contract("_ -> new")
			override fun createFromParcel(`in`: Parcel) = PSU(`in`)

			@Contract(value = "_ -> new", pure = true)
			override fun newArray(size: Int): Array<PSU?> = arrayOfNulls(size)
		}
	}
}