package com.example.buildmypc.ui.build

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.example.buildmypc.ui.parts.parts.*

class PCBuild : Parcelable {
	//	public PCBuild(Drawable logo){
	//		this(" ", logo, null, null, null, null, null, null, null, null, null, null, null);
	//	}
	lateinit var name: String
	lateinit var logo: Drawable
	lateinit var pcCase: Case
	lateinit var cooler: Cooler
	lateinit var cpu: CPU
	lateinit var gpu: GPU
	lateinit var memory: Memory
	lateinit var monitor: Monitor
	lateinit var motherboard: Motherboard
	lateinit var os: OS
	lateinit var psu: PSU
	lateinit var storage: Storage
	lateinit var extraParts // for all of your extra part needs (this won't be explicitly checked against, will possibly be a dropdown menu)
			: ArrayList<Part>
	var idNumber = 0

	constructor()

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
		name: String,
		logo: Drawable,
		pcCase: Case,
		cooler: Cooler,
		cpu: CPU,
		gpu: GPU,
		memory: Memory,
		monitor: Monitor,
		motherboard: Motherboard,
		os: OS,
		psu: PSU,
		storage: Storage,
		extraParts: ArrayList<Part>,
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
		pcCase = `in`.readTypedObject(Case.CREATOR)!!
		cooler = `in`.readTypedObject(Cooler.CREATOR)!!
		cpu = `in`.readTypedObject(CPU.CREATOR)!!
		gpu = `in`.readTypedObject(GPU.CREATOR)!!
		memory = `in`.readTypedObject(Memory.CREATOR)!!
		monitor = `in`.readTypedObject(Monitor.CREATOR)!!
		motherboard = `in`.readTypedObject(Motherboard.CREATOR)!!
		os = `in`.readTypedObject(OS.CREATOR)!!
		psu = `in`.readTypedObject(PSU.CREATOR)!!
		storage = `in`.readTypedObject(Storage.CREATOR)!!
		extraParts = `in`.createTypedArrayList(Part.CREATOR)?.let { ArrayList(it) }!!
		idNumber = `in`.readInt()
	}

	override fun equals(other: Any?): Boolean { // hashcode and equals do NOT take into account idNumbers
		if (this === other) return true
		if ((other == null) || (javaClass != other.javaClass)) return false
		val pcBuild = other as PCBuild
		return when {
			name != pcBuild.name -> false
			logo != pcBuild.logo -> false
			pcCase != pcBuild.pcCase -> false
			cooler != pcBuild.cooler -> false
			cpu != pcBuild.cpu -> false
			gpu != pcBuild.gpu -> false
			memory != pcBuild.memory -> false
			monitor != pcBuild.monitor -> false
			motherboard != pcBuild.motherboard -> false
			os != pcBuild.os -> false
			psu != pcBuild.psu -> false
			storage != pcBuild.storage -> false
			else -> extraParts == pcBuild.extraParts
		}
	}

	override fun hashCode(): Int {
		var result = name.hashCode()
		intArrayOf(
			logo.hashCode(),
			pcCase.hashCode(),
			cooler.hashCode(),
			cpu.hashCode(),
			gpu.hashCode(),
			memory.hashCode(),
			monitor.hashCode(),
			motherboard.hashCode(),
			os.hashCode(),
			psu.hashCode(),
			storage.hashCode(),
			extraParts.hashCode()
		).forEach { result = 31 * result + it }
		return result
	}

	override fun describeContents() = 0
	override fun writeToParcel(dest: Parcel, flags: Int) = dest.run {
		writeParcelable(pcCase, flags)
		writeParcelable(cooler, flags)
		writeParcelable(cpu, flags)
		writeParcelable(gpu, flags)
		writeParcelable(memory, flags)
		writeParcelable(monitor, flags)
		writeParcelable(motherboard, flags)
		writeParcelable(os, flags)
		writeParcelable(psu, flags)
		writeParcelable(storage, flags)
		writeParcelableList(extraParts, flags)
		writeInt(idNumber)
	}

	override fun toString() = "$name${super.toString()}"

	companion object CREATOR : Creator<PCBuild> {
		override fun createFromParcel(`in`: Parcel) = PCBuild(`in`)
		override fun newArray(size: Int) = arrayOfNulls<PCBuild>(size)
	}
}