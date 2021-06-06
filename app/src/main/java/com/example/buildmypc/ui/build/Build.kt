package com.example.buildmypc.ui.build

import com.example.buildmypc.ui.parts.parts.*

data class Build(
	val cpu: CPU,
	val cooler: Cooler,
	val motherboard: Motherboard,
	val memory: Array<Memory>,
	val storage: Array<StorageComp>,
	val gpu: Array<GPU>,
	val case: Case,
	val psu: PSU,
	val os: OS,
	val monitor: Monitor,
	val accessory: Array<Accessory>
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false
		other as Build
		return when {
			cpu != other.cpu ||
					cooler != other.cooler ||
					motherboard != other.motherboard ||
					!memory.contentEquals(other.memory) ||
					!storage.contentEquals(other.storage) ||
					!gpu.contentEquals(other.gpu) ||
					case != other.case ||
					psu != other.psu ||
					os != other.os ||
					monitor != other.monitor ||
					!accessory.contentEquals(other.accessory) -> false
			else -> true
		}
	}

	override fun hashCode(): Int {
		var result = cpu.hashCode()
		result = 31 * result + cooler.hashCode()
		result = 31 * result + motherboard.hashCode()
		result = 31 * result + memory.contentHashCode()
		result = 31 * result + storage.contentHashCode()
		result = 31 * result + gpu.contentHashCode()
		result = 31 * result + case.hashCode()
		result = 31 * result + psu.hashCode()
		result = 31 * result + os.hashCode()
		result = 31 * result + monitor.hashCode()
		result = 31 * result + accessory.contentHashCode()
		return result
	}
}
