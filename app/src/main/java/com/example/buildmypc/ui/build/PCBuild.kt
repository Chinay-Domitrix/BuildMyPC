package com.example.buildmypc.ui.build

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.example.buildmypc.ui.parts.parts.*
import java.util.*

class PCBuild : Parcelable {
	//	public PCBuild(Drawable logo){
	//		this(" ", logo, null, null, null, null, null, null, null, null, null, null, null);
	//	}
	var name: String? = null
	var logo: Drawable? = null
	var pcCase: Case? = null
	var cooler: Cooler? = null
	var cpu: CPU? = null
	var gpu: GPU? = null
	var memory: Memory? = null
	var monitor: Monitor? = null
	var motherboard: Motherboard? = null
	var os: OS? = null
	var psu: PSU? = null
	var storage: Storage? = null
	var extraParts // for all of your extra part needs (this won't be explicitly checked against, will possibly be a dropdown menu)
			: ArrayList<Part>? = null
	var idNumber = 0

	constructor() {}

	//	public PCBuild(String name, Drawable logo, Case pcCase, Cooler cooler, CPU cpu, GPU gpu, Memory memory, Monitor monitor, Motherboard motherboard, OS os, PSU psu, Storage storage, ArrayList<Part> extraParts) {
	//		this.name = name;
	//		this.logo = logo;
	//		this.pcCase = pcCase;
	//		this.cooler = cooler;
	//		this.cpu = cpu;
	//		this.gpu = gpu;
	//		this.memory = memory;
	//		this.monitor = monitor;
	//		this.motherboard = motherboard;
	//		this.os = os;
	//		this.psu = psu;
	//		this.storage = storage;
	//		this.extraParts = extraParts;
	//		this.idNumber = (int)(Math.random() * 1000000); // id generator
	//	}
	constructor(
		name: String?,
		logo: Drawable?,
		pcCase: Case?,
		cooler: Cooler?,
		cpu: CPU?,
		gpu: GPU?,
		memory: Memory?,
		monitor: Monitor?,
		motherboard: Motherboard?,
		os: OS?,
		psu: PSU?,
		storage: Storage?,
		extraParts: ArrayList<Part>?,
		idNumber: Int
	) {
		this.name = name
		this.logo = logo
		this.pcCase = pcCase
		this.cooler = cooler
		this.cpu = cpu
		this.gpu = gpu
		this.memory = memory
		this.monitor = monitor
		this.motherboard = motherboard
		this.os = os
		this.psu = psu
		this.storage = storage
		this.extraParts = extraParts
		this.idNumber = idNumber
	}

	constructor(`in`: Parcel) {
		pcCase = `in`.readTypedObject(Case.CREATOR)
		cooler = `in`.readTypedObject(Cooler.CREATOR)
		cpu = `in`.readTypedObject(CPU.CREATOR)
		gpu = `in`.readTypedObject(GPU.CREATOR)
		memory = `in`.readTypedObject(Memory.CREATOR)
		monitor = `in`.readTypedObject(Monitor.CREATOR)
		motherboard = `in`.readTypedObject(Motherboard.CREATOR)
		os = `in`.readTypedObject(OS.CREATOR)
		psu = `in`.readTypedObject(PSU.CREATOR)
		storage = `in`.readTypedObject(Storage.CREATOR)
		extraParts = ArrayList(`in`.createTypedArrayList(Part.CREATOR))
		idNumber = `in`.readInt()
	}

	override fun equals(other: Any?): Boolean { // hashcode and equals do NOT take into account idNumbers
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		val pcBuild = other as PCBuild
		return when {
			if (name != null) name != pcBuild.name else pcBuild.name != null -> false
			if (logo != null) logo != pcBuild.logo else pcBuild.logo != null -> false
			if (pcCase != null) pcCase!! != pcBuild.pcCase else pcBuild.pcCase != null -> false
			if (cooler != null) cooler!! != pcBuild.cooler else pcBuild.cooler != null -> false
			if (cpu != null) cpu!! != pcBuild.cpu else pcBuild.cpu != null -> false
			if (gpu != null) gpu!! != pcBuild.gpu else pcBuild.gpu != null -> false
			if (memory != null) memory!! != pcBuild.memory else pcBuild.memory != null -> false
			if (monitor != null) monitor!! != pcBuild.monitor else pcBuild.monitor != null -> false
			if (motherboard != null) motherboard!! != pcBuild.motherboard else pcBuild.motherboard != null -> false
			if (os != null) os!! != pcBuild.os else pcBuild.os != null -> false
			if (psu != null) psu!! != pcBuild.psu else pcBuild.psu != null -> false
			if (storage != null) storage!! != pcBuild.storage else pcBuild.storage != null -> false
			else -> if (extraParts != null) extraParts == pcBuild.extraParts else pcBuild.extraParts == null
		}
	}

	override fun hashCode(): Int {
		var result = if (name != null) name.hashCode() else 0
		intArrayOf(
			if (logo != null) logo.hashCode() else 0,
			if (pcCase != null) pcCase.hashCode() else 0,
			if (cooler != null) cooler.hashCode() else 0,
			if (cpu != null) cpu.hashCode() else 0,
			if (gpu != null) gpu.hashCode() else 0,
			if (memory != null) memory.hashCode() else 0,
			if (monitor != null) monitor.hashCode() else 0,
			if (motherboard != null) motherboard.hashCode() else 0,
			if (os != null) os.hashCode() else 0,
			if (psu != null) psu.hashCode() else 0,
			if (storage != null) storage.hashCode() else 0,
			if (extraParts != null) extraParts.hashCode() else 0
		).forEach { result = 31 * result + it }
		return result
	}

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) {
		dest.writeParcelable(pcCase, flags)
		dest.writeParcelable(cooler, flags)
		dest.writeParcelable(cpu, flags)
		dest.writeParcelable(gpu, flags)
		dest.writeParcelable(memory, flags)
		dest.writeParcelable(monitor, flags)
		dest.writeParcelable(motherboard, flags)
		dest.writeParcelable(os, flags)
		dest.writeParcelable(psu, flags)
		dest.writeParcelable(storage, flags)
		dest.writeParcelableList(extraParts, flags)
		dest.writeInt(idNumber)
	}

	override fun toString() = "$name${super.toString()}"

	companion object CREATOR : Creator<PCBuild> {
		override fun createFromParcel(`in`: Parcel) = PCBuild(`in`)

		override fun newArray(size: Int) = arrayOfNulls<PCBuild>(size)
	}
}