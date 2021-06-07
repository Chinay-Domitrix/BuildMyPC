package com.example.buildmypc

import com.example.buildmypc.MainActivity.parts
import com.example.buildmypc.ui.parts.parts.CPU

class PartsJSONParse : Thread() {
	override fun run() {
		val tempJSONObject = parts.get()
		val cpus = tempJSONObject.getJSONArray("cpus")
		val tempCPUS = MainActivity.cpus.get()
		(0 until cpus.length()).forEach {
			cpus.getJSONObject(it).apply {
				tempCPUS.add(
					CPU(
						getString("model"),
						getString("manufacturer"),
						getInt("core-count"),
						getDouble("core-clock-ghz"),
						getDouble("boost-clock-ghz"),
						getInt("tdp-w"),
						getString("series"),
						getString("microarchitecture"),
						getString("core-family"),
						getString("socket"),
						getBoolean("integrated-gpu"),
						getInt("max-memory-support-gb"),
						getBoolean("ecc"),
						getBoolean("cooler"),
						getBoolean("smt")
					)
				)
			}
		}
		MainActivity.cpus.set(tempCPUS)
	}
}