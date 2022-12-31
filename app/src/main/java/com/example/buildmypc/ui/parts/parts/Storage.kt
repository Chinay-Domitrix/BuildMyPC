package com.example.buildmypc.ui.parts.parts

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import org.jetbrains.annotations.Contract

class Storage : Part, Parcelable {
	var formFactor: String? = null
	var cacheSizeMB // in mb
			= 0
	var capacity // in MB -> 2TB is 2000, 1.5TB is 1500, 512 GB is 512
			: String? = null
	var sataInterface // which SATA does this part connect to
			: String? = null
	var nvme // NMVe, or non-volatile memory express
			= false
	var rpm // only applies for HDDs
			= 0
	var type // either HDD or SSD
			: String? = null

	constructor(model: String?, manufacturer: String?) : super(model, manufacturer)
	constructor(
		model: String?,
		manufacturer: String?,
		formFactor: String?,
		cacheSizeMB: Int,
		capacity: String?,
		hardwareInterface: String?,
		nvme: Boolean,
		rpm: Int,
		type: String?
	) : super(model, manufacturer) {
		this.formFactor = formFactor
		this.cacheSizeMB = cacheSizeMB
		this.capacity = capacity
		this.sataInterface = hardwareInterface
		this.nvme = nvme
		this.rpm = rpm
		this.type = type
	}

	constructor(`in`: Parcel) : super(`in`.toString(), `in`.toString()) {
		formFactor = `in`.readString()
		cacheSizeMB = `in`.readInt()
		capacity = `in`.readString()
		sataInterface = `in`.readString()
		nvme = `in`.readBoolean()
		rpm = `in`.readInt()
		type = `in`.readString()
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		if (!super.equals(other)) return false
		val storage = other as Storage
		if (cacheSizeMB != storage.cacheSizeMB) return false
		if (nvme != storage.nvme) return false
		if (rpm != storage.rpm) return false
		if (if (formFactor != null) formFactor != storage.formFactor else storage.formFactor != null) return false
		if (if (capacity != null) capacity != storage.capacity else storage.capacity != null) return false
		if (if (sataInterface != null) sataInterface != storage.sataInterface else storage.sataInterface != null) return false
		return if (type != null) type == storage.type else storage.type == null
	}

	override fun hashCode(): Int {
		var result = super.hashCode()
		result = 31 * result + if (formFactor != null) formFactor.hashCode() else 0
		result = 31 * result + cacheSizeMB
		result = 31 * result + if (capacity != null) capacity.hashCode() else 0
		result = 31 * result + if (sataInterface != null) sataInterface.hashCode() else 0
		result = 31 * result + if (nvme) 1 else 0
		result = 31 * result + rpm
		result = 31 * result + if (type != null) type.hashCode() else 0
		return result
	}

	override fun writeToParcel(dest: Parcel, flags: Int) {
		super.writeToParcel(dest, flags)
		dest.writeString(formFactor)
		dest.writeInt(cacheSizeMB)
		dest.writeString(capacity)
		dest.writeString(sataInterface)
		dest.writeBoolean(nvme)
		dest.writeInt(rpm)
		dest.writeString(type)
	}

	override val paramCount = 9

	companion object {
		@JvmField
		val CREATOR: Creator<Storage> = object : Creator<Storage> {
			@Contract("_ -> new")
			override fun createFromParcel(`in`: Parcel) = Storage(`in`)

			@Contract(value = "_ -> new", pure = true)
			override fun newArray(size: Int): Array<Storage?> = arrayOfNulls(size)
		}
	}
}