package com.example.buildmypc.ui.parts.parts

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import org.jetbrains.annotations.Contract
import java.lang.Double.doubleToLongBits

class CPU : Part, Parcelable {
	val coreCount: Int
	val coreClock: Double
	val boostClock: Double
	val tdp: Int
	private val series: String?
	val microarchitecture: String?
	val coreFamily: String?
	val socket: String?
	private val iGPU: Boolean
	val maxMemory: Int
	val isEcc: Boolean
	val isCooler: Boolean
	val isSmt: Boolean

	constructor(model: String?, manufacturer: String?) : super(model, manufacturer) {
		coreCount = -1
		coreClock = -1.0
		boostClock = -1.0
		tdp = -1
		series = null
		microarchitecture = null
		coreFamily = null
		socket = null
		iGPU = false
		maxMemory = -1
		isEcc = false
		isCooler = false
		isSmt = false
	}

	constructor(
		model: String?,
		manufacturer: String?,
		coreCount: Int,
		coreClock: Double,
		boostClock: Double,
		tdp: Int,
		series: String?,
		microarchitecture: String?,
		coreFamily: String?,
		socket: String?,
		iGPU: Boolean,
		maxMemory: Int,
		ecc: Boolean,
		cooler: Boolean,
		smt: Boolean
	) : super(model, manufacturer) {
		this.coreCount = coreCount
		this.coreClock = coreClock
		this.boostClock = boostClock
		this.tdp = tdp
		this.series = series
		this.microarchitecture = microarchitecture
		this.coreFamily = coreFamily
		this.socket = socket
		this.iGPU = iGPU
		this.maxMemory = maxMemory
		isEcc = ecc
		isCooler = cooler
		isSmt = smt
	}

	constructor(`in`: Parcel) : super(`in`.readString(), `in`.readString()) {
		coreCount = `in`.readInt()
		coreClock = `in`.readDouble()
		boostClock = `in`.readDouble()
		tdp = `in`.readInt()
		series = `in`.readString()
		microarchitecture = `in`.readString()
		coreFamily = `in`.readString()
		socket = `in`.readString()
		iGPU = `in`.readBoolean()
		maxMemory = `in`.readInt()
		isEcc = `in`.readBoolean()
		isCooler = `in`.readBoolean()
		isSmt = `in`.readBoolean()
	}

	fun isiGPU() = iGPU

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		val cpu = other as CPU
		if (coreCount != cpu.coreCount) return false
		if (cpu.coreClock.compareTo(coreClock) != 0) return false
		if (cpu.boostClock.compareTo(boostClock) != 0) return false
		if (tdp != cpu.tdp) return false
		if (isiGPU() != cpu.isiGPU()) return false
		if (maxMemory != cpu.maxMemory) return false
		if (isEcc != cpu.isEcc) return false
		if (isCooler != cpu.isCooler) return false
		if (isSmt != cpu.isSmt) return false
		if (if (manufacturer != null) manufacturer != cpu.manufacturer else cpu.manufacturer != null) return false
		if (if (model != null) model != cpu.model else cpu.model != null) return false
		if (if (series != null) series != cpu.series else cpu.series != null) return false
		if (if (microarchitecture != null) microarchitecture != cpu.microarchitecture else cpu.microarchitecture != null) return false
		if (if (coreFamily != null) coreFamily != cpu.coreFamily else cpu.coreFamily != null) return false
		return if (socket != null) socket == cpu.socket else cpu.socket == null
	}

	override fun hashCode(): Int {
		var result = if (manufacturer != null) manufacturer.hashCode() else 0
		result = 31 * result + if (model != null) model.hashCode() else 0
		result = 31 * result + coreCount
		var temp = doubleToLongBits(coreClock)
		result = 31 * result + (temp xor (temp ushr 32)).toInt()
		temp = doubleToLongBits(boostClock)
		result = 31 * result + (temp xor (temp ushr 32)).toInt()
		result = 31 * result + tdp
		result = 31 * result + (series?.hashCode() ?: 0)
		result = 31 * result + (microarchitecture?.hashCode() ?: 0)
		result = 31 * result + (coreFamily?.hashCode() ?: 0)
		result = 31 * result + (socket?.hashCode() ?: 0)
		result = 31 * result + if (isiGPU()) 1 else 0
		result = 31 * result + maxMemory
		result = 31 * result + if (isEcc) 1 else 0
		result = 31 * result + if (isCooler) 1 else 0
		result = 31 * result + if (isSmt) 1 else 0
		return result
	}

	override fun writeToParcel(dest: Parcel, flags: Int) {
		super.writeToParcel(dest, flags)
		dest.writeInt(coreCount)
		dest.writeDouble(coreClock)
		dest.writeDouble(boostClock)
		dest.writeInt(tdp)
		dest.writeString(series)
		dest.writeString(microarchitecture)
		dest.writeString(coreFamily)
		dest.writeString(socket)
		dest.writeBoolean(iGPU)
		dest.writeInt(maxMemory)
		dest.writeBoolean(isEcc)
		dest.writeBoolean(isCooler)
		dest.writeBoolean(isSmt)
	}

	override val paramCount: Int
		get() = 15

	companion object {
		@JvmField
		val CREATOR: Creator<CPU> = object : Creator<CPU> {
			@Contract("_ -> new")
			override fun createFromParcel(`in`: Parcel) = CPU(`in`)

			@Contract(value = "_ -> new", pure = true)
			override fun newArray(size: Int): Array<CPU?> = arrayOfNulls(size)
		}
	}
}