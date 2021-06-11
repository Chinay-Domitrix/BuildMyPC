package com.example.buildmypc

import com.example.buildmypc.MainActivity.parts
import com.example.buildmypc.ui.parts.parts.*

class PartsJSONParse : Thread() {
	override fun run() {
		val tempJSONObject = parts.get()
		ArrayList<Thread>().apply {
			add(Thread {
				val cpus = tempJSONObject.getJSONArray("cpu")
				val tempCPUs = MainActivity.cpus.get()
				(0 until cpus.length()).forEach {
					cpus.getJSONObject(it).apply {
						tempCPUs += CPU(
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
					}
				}
				MainActivity.cpus.set(tempCPUs)
			})
			add(Thread {
				val coolers = tempJSONObject.getJSONArray("cooler")
				val tempCoolers = MainActivity.coolers.get()
				(0 until coolers.length()).forEach {
					coolers.getJSONObject(it).apply {
						val socketSupport = getJSONArray("socket-support")
						val sockets = ArrayList<String>()
						for (i in 0 until socketSupport.length())
							sockets.add(socketSupport[i] as String)
						if (getBoolean("water-cooled")) tempCoolers += Cooler(
							getString("model"),
							getString("manufacturer"),
							getString("rpm"),
							getString("noise-level-db"),
							getInt("water-cooler-size-mm"),
							sockets,
							getBoolean("water-cooled"),
							getBoolean("smt")
						) else tempCoolers += Cooler(
							getString("model"),
							getString("manufacturer"),
							getString("rpm"),
							getString("noise-level-db"),
							getInt("fan-height-mm"),
							sockets,
							getBoolean("water-cooled"),
							getBoolean("smt")
						)
					}
				}
				MainActivity.coolers.set(tempCoolers)
			})
			add(Thread {
				val motherboards = tempJSONObject.getJSONArray("motherboard")
				val tempMotherboards = MainActivity.motherboards.get()
				(0 until motherboards.length()).forEach {
					motherboards.getJSONObject(it).apply {
						val m2Slots = getJSONArray("m2-slots")
						val m2 = ArrayList<String>()
						(0 until m2Slots.length()).mapTo(m2) { it1 -> m2Slots[it1] as String }
						val memorySpeeds = getJSONArray("memory-speeds")
						val mem = ArrayList<String>()
						(0 until memorySpeeds.length()).mapTo(mem) { it1 -> memorySpeeds[it1] as String }
						val oe = getJSONArray("onboard-ethernet")
						val eth = ArrayList<String>()
						(0 until oe.length()).mapTo(eth) { it1 -> oe[it1] as String }
						val usbHeaders = getJSONObject("usb-headers")
						tempMotherboards += Motherboard(
							getString("model"),
							getString("manufacturer"),
							getBoolean("ecc"),
							getString("chipset"),
							getString("form-factor"),
							m2,
							getInt("max-memory-gb"),
							getInt("memory-slots"),
							mem,
							getString("memory-type"),
							getInt("msata-slots"),
							eth,
							getString("onboard-video"),
							ArrayList<CountedString>().apply {
								getJSONObject("pci-slots").apply {
									add(CountedString("e-x1", getInt("e-x1")))
									add(CountedString("e-x16", getInt("e-x16")))
									add(CountedString("e-x4", getInt("e-x4")))
									add(CountedString("e-x8", getInt("e-x8")))
									add(CountedString("standard", getInt("standard")))
								}
							},
							getBoolean("raid"),
							getInt("sata-6gbps"),
							usbHeaders.getInt("2"),
							ArrayList<CountedString>().apply {
								usbHeaders.getJSONObject("3-2").apply {
									add(CountedString("gen-1", getInt("gen-1")))
									add(CountedString("gen-2", getInt("gen-2")))
									add(CountedString("gen-2x2", getInt("gen-2x2")))
								}
							},
							getBoolean("wireless")
						)
					}
				}
				MainActivity.motherboards.set(tempMotherboards)
			})
			add(Thread {
				val memory = tempJSONObject.getJSONArray("memory")
				val tempMemory = MainActivity.memory.get()
				(0 until memory.length()).forEach {
					memory.getJSONObject(it).apply {
						tempMemory += Memory(
							getString("model"),
							getString("manufacturer"),
							getBoolean("ecc"),
							getInt("cas-latency"),
							getInt("ddr-gen"),
							getInt("first-word-latency-ns"),
							getString("form-factor"),
							getBoolean("heat-spreader"),
							getJSONObject("modules").getInt("size-gb"),
							getJSONObject("modules").getInt("quantity"),
							getInt("speed-mhz"),
							getString("timing"),
							getDouble("voltage")
						)
					}
				}
				MainActivity.memory.set(tempMemory)
			})
			add(Thread {
				val storage = tempJSONObject.getJSONArray("storage")
				val tempStorage = MainActivity.storage.get()
				(0 until storage.length()).forEach {
					storage.getJSONObject(it).apply {
						tempStorage += Storage(
							getString("model"),
							getString("manufacturer"),
							getDouble("form-factor"),
							getInt("cache-mb"),
							getJSONObject("capacity").getInt("size")
								.toString() + getJSONObject("capacity").getString("unit"),
							getString("interface"),
							getBoolean("nvme"),
							getInt("rpm"),
							getString("type")
						)
					}
				}
				MainActivity.storage.set(tempStorage)
			})
			add(Thread {
				val gpus = tempJSONObject.getJSONArray("gpu")
				val tempGPUs = MainActivity.gpus.get()
				(0 until gpus.length()).forEach {
					gpus.getJSONObject(it).apply {
						tempGPUs += GPU(
							getString("model"),
							getString("manufacturer"),
							getInt("boost-clock-mhz"),
							getString("chipset"),
							getString("cooling"),
							getInt("core-clock-mhz"),
							getInt("effective-memory-clock-mhz"),
							getInt("expansion-slot-width"),
							getString("external-power"),
							getString("frame-sync"),
							getString("interface"),
							getInt("length-mm"),
							getInt("memory-gb"),
							getString("memory-type"),
							getInt("tdp-w"),
							ArrayList<CountedString>().apply {
								getJSONObject("video-ports").apply {
									add(CountedString("dp", getInt("dp")))
									add(CountedString("dvi", getInt("dvi")))
									add(CountedString("hdmi", getInt("hdmi")))
									add(CountedString("mini-dp", getInt("mini-dp")))
									add(CountedString("mini-hdmi", getInt("mini-hdmi")))
								}
							}
						)
					}
				}
				MainActivity.gpus.set(tempGPUs)
			})
			add(Thread {
				val cases = tempJSONObject.getJSONArray("case")
				val tempCases = MainActivity.pcCases.get()
				(0 until cases.length()).forEach {
					cases.getJSONObject(it).apply {
						tempCases += Case(
							getString("model"),
							getString("manufacturer"),
							getString("color"),
							ArrayList<String>().apply {
								getJSONArray("dimensions").apply {
									add(getString(0))
									add(getString(1))
								}
							},
							ArrayList<CountedString>().apply {
								getJSONObject("expansion-slots").apply {
									add(CountedString("full-height", getInt("full-height")))
									add(CountedString("half-height", getInt("half-height")))
								}
							},
							ArrayList<String>().apply {
								getJSONArray("front-panel-usb").apply {
									(0 until this.length()).forEach { i -> add(getString(i)) }
								}
							},
							ArrayList<>
						)
					}
				}
				MainActivity.pcCases.set(tempCases)
			})
		}.forEach(Thread::start)
	}
}