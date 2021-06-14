package com.example.buildmypc.ui.parts.parts

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import org.jetbrains.annotations.Contract
import java.util.*

open class Case : Part, Parcelable {
	//hello
	var color: String? = null
	var dimensionsMm // 1-d int arraylist with 3 entries,
			: ArrayList<String>? = null
	var expansionSlots: ArrayList<CountedString?>? = null
	var supportedFrontUSBs: ArrayList<String>? = null
	var internalDriveBays: ArrayList<CountedString?>? = null
	var maxGPULength: ArrayList<String>? = null
	var mbFormFactor // motherboard form factor
			: ArrayList<String>? = null
	var sidePanel: String? = null
	var type: String? = null
	var volume // in liters because screw bri'ish units
			: ArrayList<String>? = null
	var isPsuShroud = false

	constructor(model: String?, manufacturer: String?) : super(model, manufacturer)

	@JvmOverloads
	constructor(
		model: String?,
		manufacturer: String?,
		color: String?,
		dimensionsMm: ArrayList<String>? = null,
		expansionSlots: ArrayList<CountedString?>? = null,
		supportedFrontUSBs: ArrayList<String>? = null,
		internalDriveBays: ArrayList<CountedString?>? = null,
		maxGPULength: ArrayList<String>? = null,
		mbFormFactor: ArrayList<String>? = null,
		sidePanel: String? = null,
		type: String? = null,
		volume: ArrayList<String>? = null,
		psuShroud: Boolean = false
	) : super(model, manufacturer) {
		this.color = color
		this.dimensionsMm = dimensionsMm
		this.expansionSlots = expansionSlots
		this.supportedFrontUSBs = supportedFrontUSBs
		this.internalDriveBays = internalDriveBays
		this.maxGPULength = maxGPULength
		this.mbFormFactor = mbFormFactor
		this.sidePanel = sidePanel
		this.type = type
		this.volume = volume
		isPsuShroud = psuShroud
	}

	protected constructor(`in`: Parcel) : super(`in`.readString(), `in`.readString()) {
		color = `in`.readString()
		dimensionsMm = `in`.createStringArrayList()
		expansionSlots = `in`.createTypedArrayList(CountedString.CREATOR)
		supportedFrontUSBs = `in`.createStringArrayList()
		internalDriveBays = `in`.createTypedArrayList(CountedString.CREATOR)
		maxGPULength = `in`.createStringArrayList()
		mbFormFactor = `in`.createStringArrayList()
		sidePanel = `in`.readString()
		type = `in`.readString()
		volume = `in`.createStringArrayList()
		isPsuShroud = `in`.readBoolean()
	}

	override fun writeToParcel(dest: Parcel, flags: Int) {
		dest.writeString(super.model)
		dest.writeString(super.manufacturer)
		dest.writeString(color)
		dest.writeStringList(dimensionsMm)
		dest.writeTypedList(expansionSlots)
		dest.writeStringList(supportedFrontUSBs)
		dest.writeTypedList(internalDriveBays)
		dest.writeStringList(maxGPULength)
		dest.writeStringList(mbFormFactor)
		dest.writeString(sidePanel)
		dest.writeString(type)
		dest.writeStringList(volume)
		dest.writeBoolean(isPsuShroud)
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		if (!super.equals(other)) return false
		val aCase = other as Case
		if (isPsuShroud != aCase.isPsuShroud) return false
		if (if (color != null) color != aCase.color else aCase.color != null) return false
		if (if (dimensionsMm != null) dimensionsMm != aCase.dimensionsMm else aCase.dimensionsMm != null) return false
		if (if (expansionSlots != null) expansionSlots != aCase.expansionSlots else aCase.expansionSlots != null) return false
		if (if (supportedFrontUSBs != null) supportedFrontUSBs != aCase.supportedFrontUSBs else aCase.supportedFrontUSBs != null) return false
		if (if (internalDriveBays != null) internalDriveBays != aCase.internalDriveBays else aCase.internalDriveBays != null) return false
		if (if (maxGPULength != null) maxGPULength != aCase.maxGPULength else aCase.maxGPULength != null) return false
		if (if (mbFormFactor != null) mbFormFactor != aCase.mbFormFactor else aCase.mbFormFactor != null) return false
		if (if (sidePanel != null) sidePanel != aCase.sidePanel else aCase.sidePanel != null) return false
		if (if (type != null) type != aCase.type else aCase.type != null) return false
		return if (volume != null) volume == aCase.volume else aCase.volume == null
	}

	override fun hashCode(): Int {
		var result = super.hashCode()
		result = 31 * result + if (color != null) color.hashCode() else 0
		result = 31 * result + if (dimensionsMm != null) dimensionsMm.hashCode() else 0
		result = 31 * result + if (expansionSlots != null) expansionSlots.hashCode() else 0
		result = 31 * result + if (supportedFrontUSBs != null) supportedFrontUSBs.hashCode() else 0
		result = 31 * result + if (internalDriveBays != null) internalDriveBays.hashCode() else 0
		result = 31 * result + if (maxGPULength != null) maxGPULength.hashCode() else 0
		result = 31 * result + if (mbFormFactor != null) mbFormFactor.hashCode() else 0
		result = 31 * result + if (sidePanel != null) sidePanel.hashCode() else 0
		result = 31 * result + if (type != null) type.hashCode() else 0
		result = 31 * result + if (volume != null) volume.hashCode() else 0
		result = 31 * result + if (isPsuShroud) 1 else 0
		return result
	}

	override fun toString() = color + " " + super.toString() + " " + type

	override val paramCount: Int
		get() = 13

	companion object {
		@JvmField
		val CREATOR: Creator<Case> = object : Creator<Case> {
			@Contract("_ -> new")
			override fun createFromParcel(`in`: Parcel) = Case(`in`)

			@Contract(value = "_ -> new", pure = true)
			override fun newArray(size: Int): Array<Case?> = arrayOfNulls(size)
		}
	}
}