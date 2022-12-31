package com.example.buildmypc.ui.parts.parts

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import org.jetbrains.annotations.Contract
import java.util.*

class Motherboard : Part, Parcelable {
	var isEcc = false // has error correction coding (all chips should tbh)
	var chipset: String? = null
	var formFactor: String? = null
	var m2slots: ArrayList<String>? = null
	var maxMemSupport = 0
	var memSlots = 0
	var compatibleMem: ArrayList<String>? = null
	private var memType: String? = null // pretty much always DDR4
	private var mSATASlotCount = 0 // love SSDs
	var incEthernetSupp: ArrayList<String>? = null
	var incVideo
			: String? = null
	var pciSlotList // had to create a sub-object for this
			: ArrayList<CountedString?>? = null
	var isHasRAID // has "Redundant Array of Inexpensive Disks"
			= false
	var sata6gbpsCount // no clue
			= 0
	var gen2USBCount = 0
	var gen32USBcount // I don't feel like making a new USB object to only be used once
			: ArrayList<CountedString?>? = null
	var wireless: String? = null

	constructor(model: String?, manufacturer: String?) : super(model, manufacturer)
	constructor(
		model: String?,
		manufacturer: String?,
		ecc: Boolean,
		chipset: String?,
		formFactor: String?,
		m2slots: ArrayList<String>?,
		maxMemSupport: Int,
		memSlots: Int,
		compatibleMem: ArrayList<String>?,
		memType: String?,
		mSATA_slotCount: Int,
		incEthernetSupp: ArrayList<String>?,
		incVideo: String?,
		PCISlotList: ArrayList<CountedString?>?,
		hasRAID: Boolean,
		sata6gbpsCount: Int,
		gen2USBCount: Int,
		gen32USBcount: ArrayList<CountedString?>?,
		wireless: String?
	) : this(model, manufacturer) {
		isEcc = ecc
		this.chipset = chipset
		this.formFactor = formFactor
		this.m2slots = m2slots
		this.maxMemSupport = maxMemSupport
		this.memSlots = memSlots
		this.compatibleMem = compatibleMem
		this.memType = memType
		this.mSATASlotCount = mSATA_slotCount
		this.incEthernetSupp = incEthernetSupp
		this.incVideo = incVideo
		pciSlotList = PCISlotList
		isHasRAID = hasRAID
		this.sata6gbpsCount = sata6gbpsCount
		this.gen2USBCount = gen2USBCount
		this.gen32USBcount = gen32USBcount
		this.wireless = wireless
	}

	constructor(`in`: Parcel) : super(`in`.readString(), `in`.readString()) {
		isEcc = `in`.readBoolean()
		chipset = `in`.readString()
		formFactor = `in`.readString()
		m2slots = `in`.createStringArrayList()
		maxMemSupport = `in`.readInt()
		memSlots = `in`.readInt()
		compatibleMem = `in`.createStringArrayList()
		memType = `in`.readString()
		mSATASlotCount = `in`.readInt()
		incEthernetSupp = `in`.createStringArrayList()
		incVideo = `in`.readString()
		pciSlotList = `in`.createTypedArrayList(CountedString.CREATOR)
		isHasRAID = `in`.readBoolean()
		sata6gbpsCount = `in`.readInt()
		gen2USBCount = `in`.readInt()
		gen32USBcount = `in`.createTypedArrayList(CountedString.CREATOR)
		wireless = `in`.readString()
	}

	fun getmSATASlotCount(): Int {
		return mSATASlotCount
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		if (!super.equals(other)) return false
		val that = other as Motherboard
		if (isEcc != that.isEcc) return false
		if (maxMemSupport != that.maxMemSupport) return false
		if (memSlots != that.memSlots) return false
		if (getmSATASlotCount() != that.getmSATASlotCount()) return false
		if (isHasRAID != that.isHasRAID) return false
		if (sata6gbpsCount != that.sata6gbpsCount) return false
		if (gen2USBCount != that.gen2USBCount) return false
		if (if (chipset != null) chipset != that.chipset else that.chipset != null) return false
		if (if (formFactor != null) formFactor != that.formFactor else that.formFactor != null) return false
		if (if (m2slots != null) m2slots != that.m2slots else that.m2slots != null) return false
		if (if (compatibleMem != null) compatibleMem != that.compatibleMem else that.compatibleMem != null) return false
		if (if (memType != null) memType != that.memType else that.memType != null) return false
		if (if (incEthernetSupp != null) incEthernetSupp != that.incEthernetSupp else that.incEthernetSupp != null) return false
		if (if (incVideo != null) incVideo != that.incVideo else that.incVideo != null) return false
		if (pciSlotList != that.pciSlotList) return false
		if (if (gen32USBcount != null) gen32USBcount != that.gen32USBcount else that.gen32USBcount != null) return false
		return if (wireless != null) wireless == that.wireless else that.wireless == null
	}

	override fun hashCode(): Int {
		var result = super.hashCode()
		result = 31 * result + if (isEcc) 1 else 0
		result = 31 * result + if (chipset != null) chipset.hashCode() else 0
		result = 31 * result + if (formFactor != null) formFactor.hashCode() else 0
		result = 31 * result + if (m2slots != null) m2slots.hashCode() else 0
		result = 31 * result + maxMemSupport
		result = 31 * result + memSlots
		result = 31 * result + if (compatibleMem != null) compatibleMem.hashCode() else 0
		result = 31 * result + if (memType != null) memType.hashCode() else 0
		result = 31 * result + getmSATASlotCount()
		result = 31 * result + if (incEthernetSupp != null) incEthernetSupp.hashCode() else 0
		result = 31 * result + if (incVideo != null) incVideo.hashCode() else 0
		result = 31 * result + if (pciSlotList != null) pciSlotList.hashCode() else 0
		result = 31 * result + if (isHasRAID) 1 else 0
		result = 31 * result + sata6gbpsCount
		result = 31 * result + gen2USBCount
		result = 31 * result + if (gen32USBcount != null) gen32USBcount.hashCode() else 0
		result = 31 * result + if (wireless != null) wireless.hashCode() else 0
		return result
	}

	override fun writeToParcel(dest: Parcel, flags: Int) {
		super.writeToParcel(dest, flags)
		dest.writeBoolean(isEcc)
		dest.writeString(chipset)
		dest.writeString(formFactor)
		dest.writeStringList(m2slots)
		dest.writeInt(maxMemSupport)
		dest.writeInt(memSlots)
		dest.writeStringList(compatibleMem)
		dest.writeString(memType)
		dest.writeInt(mSATASlotCount)
		dest.writeStringList(incEthernetSupp)
		dest.writeString(incVideo)
		dest.writeParcelableList(pciSlotList, flags)
		dest.writeBoolean(isHasRAID)
		dest.writeInt(sata6gbpsCount)
		dest.writeInt(gen2USBCount)
		dest.writeParcelableList(gen32USBcount, flags)
		dest.writeString(wireless)
	}

	override val paramCount = 19

	companion object {
		@JvmField
		val CREATOR: Creator<Motherboard> = object : Creator<Motherboard> {
			@Contract("_ -> new")
			override fun createFromParcel(`in`: Parcel) = Motherboard(`in`)

			@Contract(value = "_ -> new", pure = true)
			override fun newArray(size: Int): Array<Motherboard?> = arrayOfNulls(size)
		}
	}
}