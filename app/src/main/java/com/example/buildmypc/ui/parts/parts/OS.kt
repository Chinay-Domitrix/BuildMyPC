package com.example.buildmypc.ui.parts.parts

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import org.jetbrains.annotations.Contract

class OS : Accessory, Parcelable {
	var bitMode: String? = null
	var maxMemSupport = 0
	var type: String? = null

	//	public String getEdition() {
	//		return edition;
	//	}
	//
	//	public void setEdition(String edition) {
	//		this.edition = edition;
	//	}
	//	private String edition;
	var oemRetail: String? = null

	constructor(model: String?, manufacturer: String?) : super(model, manufacturer)
	constructor(`in`: Parcel) : super(`in`.readString(), `in`.readString()) {
		bitMode = `in`.readString()
		maxMemSupport = `in`.readInt()
		type = `in`.readString()
		oemRetail = `in`.readString()
	}

	constructor(
		model: String?,
		manufacturer: String?,
		bitMode: String?,
		maxMemSupport: Int,
		type: String?,
		oem_retail: String?
	) : super(model, manufacturer) {
		this.bitMode = bitMode
		this.maxMemSupport = maxMemSupport
		this.type = type
		this.oemRetail = oem_retail
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		if (!super.equals(other)) return false
		val os = other as OS
		if (maxMemSupport != os.maxMemSupport) return false
		if (if (bitMode != null) bitMode != os.bitMode else os.bitMode != null) return false
		if (if (type != null) type != os.type else os.type != null) return false
		return if (oemRetail != null) oemRetail == os.oemRetail else os.oemRetail == null
	}

	override fun hashCode(): Int {
		var result = super.hashCode()
		result = 31 * result + if (bitMode != null) bitMode.hashCode() else 0
		result = 31 * result + maxMemSupport
		result = 31 * result + if (type != null) type.hashCode() else 0
		result = 31 * result + if (oemRetail != null) oemRetail.hashCode() else 0
		return result
	}

	override fun writeToParcel(dest: Parcel, flags: Int) {
		super.writeToParcel(dest, flags)
		dest.writeString(bitMode)
		dest.writeInt(maxMemSupport)
		dest.writeString(type)
		dest.writeString(oemRetail)
	}

	override val paramCount: Int
		get() = 6

	companion object {
		@JvmField
		val CREATOR: Creator<OS> = object : Creator<OS> {
			@Contract("_ -> new")
			override fun createFromParcel(`in`: Parcel) = OS(`in`)

			@Contract(value = "_ -> new", pure = true)
			override fun newArray(size: Int): Array<OS?> = arrayOfNulls(size)
		}
	}
}