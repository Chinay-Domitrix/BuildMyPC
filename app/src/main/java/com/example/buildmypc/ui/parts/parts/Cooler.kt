package com.example.buildmypc.ui.parts.parts

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import org.jetbrains.annotations.Contract
import java.util.*

class Cooler : Part, Parcelable {
	val rpm: String?
	val noiseLevel: String?
	val height: Int
	val socketSupport: ArrayList<String>?
	val isWaterCooled: Boolean
	val isFanless: Boolean

	constructor(model: String?, manufacturer: String?) : super(model, manufacturer) {
		rpm = null
		noiseLevel = null
		height = -1
		socketSupport = null
		isWaterCooled = false
		isFanless = false
	}

	constructor(
		model: String?,
		manufacturer: String?,
		rpm: String?,
		noiseLevel: String?,
		height: Int,
		socketSupport: ArrayList<String>?,
		waterCooled: Boolean,
		fanless: Boolean
	) : super(model, manufacturer) {
		this.rpm = rpm
		this.noiseLevel = noiseLevel
		this.height = height
		this.socketSupport = socketSupport
		isWaterCooled = waterCooled
		isFanless = fanless
	}

	constructor(`in`: Parcel) : super(`in`.readString(), `in`.readString()) {
		rpm = `in`.readString()
		noiseLevel = `in`.readString()
		height = `in`.readInt()
		socketSupport = `in`.createStringArrayList()
		isWaterCooled = `in`.readBoolean()
		isFanless = `in`.readBoolean()
	}

	override fun equals(o: Any?): Boolean {
		if (this === o) return true
		if (o == null || javaClass != o.javaClass) return false
		if (!super.equals(o)) return false
		val cooler = o as Cooler
		if (height != cooler.height) return false
		if (isWaterCooled != cooler.isWaterCooled) return false
		if (isFanless != cooler.isFanless) return false
		if (if (rpm != null) rpm != cooler.rpm else cooler.rpm != null) return false
		if (if (noiseLevel != null) noiseLevel != cooler.noiseLevel else cooler.noiseLevel != null) return false
		return if (socketSupport != null) socketSupport == cooler.socketSupport else cooler.socketSupport == null
	}

	override fun hashCode(): Int {
		var result = super.hashCode()
		result = 31 * result + (rpm?.hashCode() ?: 0)
		result = 31 * result + (noiseLevel?.hashCode() ?: 0)
		result = 31 * result + height
		result = 31 * result + (socketSupport?.hashCode() ?: 0)
		result = 31 * result + if (isWaterCooled) 1 else 0
		result = 31 * result + if (isFanless) 1 else 0
		return result
	}

	override fun writeToParcel(dest: Parcel, flags: Int) {
		super.writeToParcel(dest, flags)
		dest.writeString(rpm)
		dest.writeString(noiseLevel)
		dest.writeInt(height)
		dest.writeStringList(socketSupport)
		dest.writeBoolean(isWaterCooled)
		dest.writeBoolean(isFanless)
	}

	override val paramCount: Int
		get() = 8

	companion object {
		@JvmField
		val CREATOR: Creator<Cooler> = object : Creator<Cooler> {
			@Contract("_ -> new")
			override fun createFromParcel(`in`: Parcel) = Cooler(`in`)

			@Contract(value = "_ -> new", pure = true)
			override fun newArray(size: Int): Array<Cooler?> = arrayOfNulls(size)
		}
	}
}