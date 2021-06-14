package com.example.buildmypc.ui.parts.parts

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import org.jetbrains.annotations.Contract
import java.lang.Double.doubleToLongBits
import java.util.*
import java.util.Arrays.*

class Monitor : Part, Parcelable {
	var aspectRatio: String? = null
	var brightness // in candla per square meter
			= 0
	var isCurved = false
	var frameSync: ArrayList<String>? = null
	var hdrTier: String? = null
	var isBuiltInSpeakers = false
	var monitorInterfaces: ArrayList<CountedString?>? = null
	var panelType: String? = null
	var refreshRate // in hertz
			= 0.0
	var resolution // 2-element integer array; e.g. {1920, 1080}
			: IntArray? = null
	var responseTimeMs // in milliseconds
			= 0
	var screenSizeIn // in square inches
			= 0.0
	var viewingAngle: String? = null
	var isWidescreen = false

	constructor(model: String?, manufacturer: String?) : super(model, manufacturer)
	constructor(
		model: String?,
		manufacturer: String?,
		aspectRatio: String?,
		brightness: Int,
		isCurved: Boolean,
		frameSync: ArrayList<String>?,
		hdrTier: String?,
		builtInSpeakers: Boolean,
		monitorInterfaces: ArrayList<CountedString?>?,
		panelType: String?,
		refreshRate: Double,
		resolution: ArrayList<*>,
		responseTimeMs: Int,
		screenSizeIn: Double,
		viewingAngle: String?,
		isWidescreen: Boolean
	) : super(model, manufacturer) {
		this.aspectRatio = aspectRatio
		this.brightness = brightness
		this.isCurved = isCurved
		this.frameSync = frameSync
		this.hdrTier = hdrTier
		isBuiltInSpeakers = builtInSpeakers
		this.monitorInterfaces = monitorInterfaces
		this.panelType = panelType
		this.refreshRate = refreshRate
		// this is a jank solution to Kotlin arrays not being translated well into Java arrays
		this.resolution = IntArray(2)
		for (i in 0..1) this.resolution!![i] = resolution[i] as Int
		this.responseTimeMs = responseTimeMs
		this.screenSizeIn = screenSizeIn
		this.viewingAngle = viewingAngle
		this.isWidescreen = isWidescreen
	}

	constructor(`in`: Parcel) : super(`in`.readString(), `in`.readString()) {
		aspectRatio = `in`.readString()
		brightness = `in`.readInt()
		isCurved = `in`.readBoolean()
		frameSync = `in`.createStringArrayList()
		hdrTier = `in`.readString()
		isBuiltInSpeakers = `in`.readBoolean()
		monitorInterfaces = `in`.createTypedArrayList(CountedString.CREATOR)
		panelType = `in`.readString()
		refreshRate = `in`.readDouble()
		resolution = `in`.createIntArray()
		responseTimeMs = `in`.readInt()
		screenSizeIn = `in`.readDouble()
		viewingAngle = `in`.readString()
		isWidescreen = `in`.readBoolean()
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		if (!super.equals(other)) return false
		val monitor = other as Monitor
		if (brightness != monitor.brightness) return false
		if (isCurved != monitor.isCurved) return false
		if (isBuiltInSpeakers != monitor.isBuiltInSpeakers) return false
		if (monitor.refreshRate.compareTo(refreshRate) != 0) return false
		if (responseTimeMs != monitor.responseTimeMs) return false
		if (monitor.screenSizeIn.compareTo(screenSizeIn) != 0) return false
		if (isWidescreen != monitor.isWidescreen) return false
		if (if (aspectRatio != null) aspectRatio != monitor.aspectRatio else monitor.aspectRatio != null) return false
		if (if (frameSync != null) frameSync != monitor.frameSync else monitor.frameSync != null) return false
		if (if (hdrTier != null) hdrTier != monitor.hdrTier else monitor.hdrTier != null) return false
		if (if (monitorInterfaces != null) monitorInterfaces != monitor.monitorInterfaces else monitor.monitorInterfaces != null) return false
		if (if (panelType != null) panelType != monitor.panelType else monitor.panelType != null) return false
		if (!equals(resolution, monitor.resolution)) return false
		return if (viewingAngle != null) viewingAngle == monitor.viewingAngle else monitor.viewingAngle == null
	}

	override fun hashCode(): Int {
		var result = super.hashCode()
		result = 31 * result + if (aspectRatio != null) aspectRatio.hashCode() else 0
		result = 31 * result + brightness
		result = 31 * result + if (isCurved) 1 else 0
		result = 31 * result + if (frameSync != null) frameSync.hashCode() else 0
		result = 31 * result + if (hdrTier != null) hdrTier.hashCode() else 0
		result = 31 * result + if (isBuiltInSpeakers) 1 else 0
		result = 31 * result + if (monitorInterfaces != null) monitorInterfaces.hashCode() else 0
		result = 31 * result + if (panelType != null) panelType.hashCode() else 0
		var temp = doubleToLongBits(refreshRate)
		result = 31 * result + (temp xor (temp ushr 32)).toInt()
		result = 31 * result + hashCode(resolution)
		result = 31 * result + responseTimeMs
		temp = doubleToLongBits(screenSizeIn)
		result = 31 * result + (temp xor (temp ushr 32)).toInt()
		result = 31 * result + if (viewingAngle != null) viewingAngle.hashCode() else 0
		result = 31 * result + if (isWidescreen) 1 else 0
		return result
	}

	override fun writeToParcel(dest: Parcel, flags: Int) {
		super.writeToParcel(dest, flags)
		dest.writeString(aspectRatio)
		dest.writeInt(brightness)
		dest.writeBoolean(isCurved)
		dest.writeStringList(frameSync)
		dest.writeString(hdrTier)
		dest.writeBoolean(isBuiltInSpeakers)
		dest.writeParcelableList(monitorInterfaces, flags)
		dest.writeString(panelType)
		dest.writeDouble(refreshRate)
		dest.writeIntArray(resolution) // 2 elements
		dest.writeInt(responseTimeMs)
		dest.writeDouble(screenSizeIn)
		dest.writeString(viewingAngle)
		dest.writeBoolean(isWidescreen)
	}

	override val paramCount: Int
		get() = 16

	companion object {
		@JvmField
		val CREATOR: Creator<Monitor> = object : Creator<Monitor> {
			@Contract("_ -> new")
			override fun createFromParcel(`in`: Parcel) = Monitor(`in`)

			@Contract(value = "_ -> new", pure = true)
			override fun newArray(size: Int): Array<Monitor?> = arrayOfNulls(size)
		}
	}
}