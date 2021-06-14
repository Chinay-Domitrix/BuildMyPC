package com.example.buildmypc.ui.parts.parts

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import org.jetbrains.annotations.Contract
import java.lang.Double.doubleToLongBits

class Memory : Part, Parcelable {
	var hasECC = false
	var latencyCAS // backwards to respect carrot case
			= 0
	var ddrGen // pretty much always four
			= 0
	var firstWordLatency // in nanoseconds
			= 0
	var formFactor: String? = null
	var heatSpreader = false
	var moduleSize // in gb
			= 0
	var moduleCount // regular old integer
			= 0
	private var speed // in mhz
			= 0
	var timing: String? = null
	var voltage = 0.0

	constructor(model: String?, manufacturer: String?) : super(model, manufacturer)
	constructor(
		model: String?,
		manufacturer: String?,
		hasECC: Boolean,
		latencyCAS: Int,
		ddrGen: Int,
		firstWordLatency: Int,
		formFactor: String?,
		heatSpreader: Boolean,
		moduleSize: Int,
		moduleCount: Int,
		speed: Int,
		timing: String?,
		voltage: Double
	) : this(model, manufacturer) {
		this.hasECC = hasECC
		this.latencyCAS = latencyCAS
		this.ddrGen = ddrGen
		this.firstWordLatency = firstWordLatency
		this.formFactor = formFactor
		this.heatSpreader = heatSpreader
		this.moduleSize = moduleSize
		this.moduleCount = moduleCount
		this.speed = speed
		this.timing = timing
		this.voltage = voltage
	}

	constructor(`in`: Parcel) : super(`in`.readString(), `in`.readString()) {
		hasECC = `in`.readBoolean()
		latencyCAS = `in`.readInt()
		ddrGen = `in`.readInt()
		firstWordLatency = `in`.readInt()
		formFactor = `in`.readString()
		heatSpreader = `in`.readBoolean()
		moduleSize = `in`.readInt()
		moduleCount = `in`.readInt()
		speed = `in`.readInt()
		timing = `in`.readString()
		voltage = `in`.readDouble()
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		if (!super.equals(other)) return false
		val memory = other as Memory
		if (hasECC != memory.hasECC) return false
		if (latencyCAS != memory.latencyCAS) return false
		if (ddrGen != memory.ddrGen) return false
		if (firstWordLatency != memory.firstWordLatency) return false
		if (heatSpreader != memory.heatSpreader) return false
		if (moduleSize != memory.moduleSize) return false
		if (moduleCount != memory.moduleCount) return false
		if (speed != memory.speed) return false
		if (memory.voltage.compareTo(voltage) != 0) return false
		if (if (formFactor != null) formFactor != memory.formFactor else memory.formFactor != null) return false
		return if (timing != null) timing == memory.timing else memory.timing == null
	}

	override fun hashCode(): Int {
		var result = super.hashCode()
		result = 31 * result + if (hasECC) 1 else 0
		result = 31 * result + latencyCAS
		result = 31 * result + ddrGen
		result = 31 * result + firstWordLatency
		result = 31 * result + if (formFactor != null) formFactor.hashCode() else 0
		result = 31 * result + if (heatSpreader) 1 else 0
		result = 31 * result + moduleSize
		result = 31 * result + moduleCount
		result = 31 * result + speed
		result = 31 * result + if (timing != null) timing.hashCode() else 0
		val temp = doubleToLongBits(voltage)
		result = 31 * result + (temp xor (temp ushr 32)).toInt()
		return result
	}

	override fun writeToParcel(dest: Parcel, flags: Int) {
		super.writeToParcel(dest, flags)
		dest.writeBoolean(hasECC)
		dest.writeInt(latencyCAS)
		dest.writeInt(ddrGen)
		dest.writeInt(firstWordLatency)
		dest.writeString(formFactor)
		dest.writeBoolean(heatSpreader)
		dest.writeInt(moduleSize)
		dest.writeInt(moduleCount)
		dest.writeInt(speed)
		dest.writeString(timing)
		dest.writeDouble(voltage)
	}

	override val paramCount: Int
		get() = 13

	companion object {
		@JvmField
		val CREATOR: Creator<Memory> = object : Creator<Memory> {
			@Contract("_ -> new")
			override fun createFromParcel(`in`: Parcel) = Memory(`in`)

			@Contract(value = "_ -> new", pure = true)
			override fun newArray(size: Int): Array<Memory?> = arrayOfNulls(size)
		}
	}
}