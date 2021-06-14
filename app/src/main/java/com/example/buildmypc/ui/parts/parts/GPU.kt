package com.example.buildmypc.ui.parts.parts

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import java.util.*

class GPU : Part, Parcelable {
	var boostClockSpeed // in megahertz
			= 0
	var chipset: String? = null
	var cooling: String? = null
	var coreClockSpeed // in megahertz
			= 0
	var effectiveMemClockSpeed // also in megahertz; unrelated to boostClockSpeed and coreClockSpeed
			= 0
	var expansionSlotWidth = 0
	var externalPower: String? = null
	var frameSync: String? = null
	private var gpuInterface: String? = null
	var length // in millimeters
			= 0
	var intMemory // internal gpu memory, in gb
			= 0
	var intMemoryType: String? = null
	var tdpW // thermal design power wattage (how much of a beating the computer is usually able to take, measured in watts)
			= 0
	var videoPorts // I don't wanna make a custom object for this if I need to implement parcelable eventually
			: ArrayList<CountedString?>? = null

	constructor(
		model: String?,
		manufacturer: String?,
		boostClockSpeed: Int,
		chipset: String?,
		cooling: String?,
		coreClockSpeed: Int,
		effectiveMemClockSpeed: Int,
		expansionSlotWidth: Int,
		externalPower: String?,
		frameSync: String?,
		gpuInterface: String?,
		length: Int,
		intMemory: Int,
		intMemoryType: String?,
		tdpW: Int,
		videoPorts: ArrayList<CountedString?>?
	) : super(model, manufacturer) {
		this.boostClockSpeed = boostClockSpeed
		this.chipset = chipset
		this.cooling = cooling
		this.coreClockSpeed = coreClockSpeed
		this.effectiveMemClockSpeed = effectiveMemClockSpeed
		this.expansionSlotWidth = expansionSlotWidth
		this.externalPower = externalPower
		this.frameSync = frameSync
		this.gpuInterface = gpuInterface
		this.length = length
		this.intMemory = intMemory
		this.intMemoryType = intMemoryType
		this.tdpW = tdpW
		this.videoPorts = videoPorts
	}

	constructor(model: String?, manufacturer: String?) : super(model, manufacturer)
	constructor(`in`: Parcel) : super(`in`.readString(), `in`.readString()) {
		boostClockSpeed = `in`.readInt()
		chipset = `in`.readString()
		cooling = `in`.readString()
		coreClockSpeed = `in`.readInt()
		effectiveMemClockSpeed = `in`.readInt()
		expansionSlotWidth = `in`.readInt()
		externalPower = `in`.readString()
		frameSync = `in`.readString()
		gpuInterface = `in`.readString()
		length = `in`.readInt()
		intMemory = `in`.readInt()
		intMemoryType = `in`.readString()
		tdpW = `in`.readInt()
		videoPorts = `in`.createTypedArrayList(CountedString.CREATOR)
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		if (!super.equals(other)) return false
		val gpu = other as GPU
		if (boostClockSpeed != gpu.boostClockSpeed) return false
		if (coreClockSpeed != gpu.coreClockSpeed) return false
		if (effectiveMemClockSpeed != gpu.effectiveMemClockSpeed) return false
		if (expansionSlotWidth != gpu.expansionSlotWidth) return false
		if (length != gpu.length) return false
		if (intMemory != gpu.intMemory) return false
		if (tdpW != gpu.tdpW) return false
		if (if (chipset != null) chipset != gpu.chipset else gpu.chipset != null) return false
		if (if (cooling != null) cooling != gpu.cooling else gpu.cooling != null) return false
		if (if (externalPower != null) externalPower != gpu.externalPower else gpu.externalPower != null) return false
		if (if (frameSync != null) frameSync != gpu.frameSync else gpu.frameSync != null) return false
		if (if (gpuInterface != null) gpuInterface != gpu.gpuInterface else gpu.gpuInterface != null) return false
		if (if (intMemoryType != null) intMemoryType != gpu.intMemoryType else gpu.intMemoryType != null) return false
		return if (videoPorts != null) videoPorts == gpu.videoPorts else gpu.videoPorts == null
	}

	override fun hashCode(): Int {
		var result = super.hashCode()
		result = 31 * result + boostClockSpeed
		result = 31 * result + if (chipset != null) chipset.hashCode() else 0
		result = 31 * result + if (cooling != null) cooling.hashCode() else 0
		result = 31 * result + coreClockSpeed
		result = 31 * result + effectiveMemClockSpeed
		result = 31 * result + expansionSlotWidth
		result = 31 * result + if (externalPower != null) externalPower.hashCode() else 0
		result = 31 * result + if (frameSync != null) frameSync.hashCode() else 0
		result = 31 * result + if (gpuInterface != null) gpuInterface.hashCode() else 0
		result = 31 * result + length
		result = 31 * result + intMemory
		result = 31 * result + if (intMemoryType != null) intMemoryType.hashCode() else 0
		result = 31 * result + tdpW
		result = 31 * result + if (videoPorts != null) videoPorts.hashCode() else 0
		return result
	}

	override fun writeToParcel(dest: Parcel, flags: Int) {
		super.writeToParcel(dest, flags)
		dest.writeInt(boostClockSpeed)
		dest.writeString(chipset)
		dest.writeString(cooling)
		dest.writeInt(coreClockSpeed)
		dest.writeInt(effectiveMemClockSpeed)
		dest.writeInt(expansionSlotWidth)
		dest.writeString(externalPower)
		dest.writeString(frameSync)
		dest.writeString(gpuInterface)
		dest.writeInt(length)
		dest.writeInt(intMemory)
		dest.writeString(intMemoryType)
		dest.writeInt(tdpW)
		dest.writeTypedList(videoPorts)
	}

	override val paramCount: Int
		get() = 16

	companion object {
		// the only important part for 12 year old me buying a gaming PC
		@JvmField
		val CREATOR: Creator<GPU> = object : Creator<GPU> {
			override fun createFromParcel(`in`: Parcel) = GPU(`in`)

			override fun newArray(size: Int): Array<GPU?> = arrayOfNulls(size)
		}
	}
}