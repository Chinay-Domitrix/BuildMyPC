package com.example.buildmypc

import android.util.Log.d
import com.example.buildmypc.MainActivity.parts
import com.example.buildmypc.ui.parts.parts.*
import org.json.JSONException

class PartsJSONParse : Thread() {
	override fun run() {
		parts.get().apply {
			d("ACTUAL_PARSER", "parts received as: " + parts.get().toString())
			ArrayList<Thread>().apply {
				add(Thread {
					val cpus = getJSONArray("cpu")
					val tempCPUs = MainActivity.cpus.get()
					d("ACTUAL_PARSER", "CPU list started")
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
								try {
									getBoolean("integrated-gpu")
								} catch (e: JSONException) {
									false
								},
								getInt("max-memory-support-gb"),
								getBoolean("ecc"),
								getBoolean("cooler"),
								try {
									getBoolean("smt")
								} catch (e: JSONException) {
									false
								},
							)
						}
					}
					d("ACTUAL_PARSER", "cpus completed somewhat")
					MainActivity.cpus.set(tempCPUs)
				})
				add(Thread {
					val coolers = getJSONArray("cooler")
					val tempCoolers = MainActivity.coolers.get()
					d("ACTUAL_PARSER", "cooler list started")
					(0 until coolers.length()).forEach {
						coolers.getJSONObject(it).apply {
							val socketSupport = getJSONArray("socket-support")
							val sockets = ArrayList<String>()
							for (i in 0 until socketSupport.length())
								sockets.add(socketSupport[i] as String)
							if (getBoolean("water-cooled")) tempCoolers += Cooler(
								getString("model"),
								getString("manufacturer"),
								getString("fan-rpm"),
								getString("noise-level-db"),
								getInt("water-cooler-size-mm"),
								sockets,
								getBoolean("water-cooled"),
								getBoolean("fanless")
							) else tempCoolers += Cooler(
								getString("model"),
								getString("manufacturer"),
								getString("fan-rpm"),
								getString("noise-level-db"),
								getInt("fan-height-mm"),
								sockets,
								getBoolean("water-cooled"),
								getBoolean("fanless")
							)
						}
					}
					d("ACTUAL_PARSER", "coolers completed somewhat")
					MainActivity.coolers.set(tempCoolers)
				})
				add(Thread {
					val motherboards = getJSONArray("motherboard")
					val tempMotherboards = MainActivity.motherboards.get()
					d("ACTUAL_PARSER", "motherboard list started")
					d("ACTUAL_PARSER", "mb: " + motherboards.getJSONObject(0).has("chirag"))
					d(
						"ACTUAL_PARSER",
						"mb: " + motherboards.getJSONObject(0).getJSONArray("m2-slots").toString()
					)
					(0 until motherboards.length()).forEach {
						motherboards.getJSONObject(it).apply {
							val usbHeaders = getJSONObject("usb-headers")
							tempMotherboards += Motherboard(
								getString("model"),
								getString("manufacturer"),
								getBoolean("ecc"),
								getString("chipset"),
								getString("form-factor"),
								ArrayList<String>().apply {
									getJSONArray("m2-slots").apply {
										for (i in 0 until this.length()) {
											add(getString(i))
										}
									}
								},
								getInt("max-memory-gb"),
								getInt("memory-slots"),
								ArrayList<String>().apply {
									getJSONArray("memory-speeds").apply {
										(0 until this.length()).forEach { i -> add(getString(i)) }
									}
								},
								getString("memory-type"),
								getInt("msata-slots"),
								ArrayList<String>().apply {
									getJSONArray("onboard-ethernet").apply {
										(0 until this.length()).forEach { i -> add(getString(i)) }
									}
								},
								getString("onboard-video"),
								ArrayList<CountedString?>().apply {
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
								ArrayList<CountedString?>().apply {
									usbHeaders.getJSONObject("3-2").apply {
										add(CountedString("gen-1", getInt("gen-1")))
										add(CountedString("gen-2", getInt("gen-2")))
										add(CountedString("gen-2x2", getInt("gen-2x2")))
									}
								},
								getString("wireless")
							)
						}
					}
					d("ACTUAL_PARSER", "motherboards completed somewhat")
					MainActivity.motherboards.set(tempMotherboards)
				})
				add(Thread {
					val memory = getJSONArray("memory")
					val tempMemory = MainActivity.memory.get()
					d("ACTUAL_PARSER", "memory list started")
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
					d("ACTUAL_PARSER", "memory completed somewhat")
					MainActivity.memory.set(tempMemory)
				})
				add(Thread {
					val storage = getJSONArray("storage")
					val tempStorage = MainActivity.storage.get()
					d("ACTUAL_PARSER", "storage list started")
					(0 until storage.length()).forEach {
						storage.getJSONObject(it).apply {
							tempStorage += Storage(
								getString("model"),
								getString("manufacturer"),
								getString("form-factor"),
								try {
									getInt("cache-mb")
								} catch (e: JSONException) {
									-1
								},
								getJSONObject("capacity").getInt("size")
									.toString() + getJSONObject("capacity").getString("unit"),
								getString("interface"),
								getBoolean("nvme"),
								try {
									getInt("rpm")
								} catch (e: JSONException) {
									-1
								},
								getString("type")
							)
						}
					}
					d("ACTUAL_PARSER", "storage completed somewhat")
					MainActivity.storage.set(tempStorage)
				})
				add(Thread {
					val gpus = getJSONArray("gpu")
					val tempGPUs = MainActivity.gpus.get()
					d("ACTUAL_PARSER", "gpu list started")
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
								ArrayList<CountedString?>().apply {
									getJSONObject("video-ports").apply {
										add(CountedString("dp", getInt("dp")))
										add(CountedString("dvi", getInt("dvi")))
										add(CountedString("hdmi", getInt("hdmi")))
										add(CountedString("mini-dp", getInt("mini-dp")))
										add(CountedString("mini-hdmi", getInt("mini-hdmi")))
									}
								})
						}
					}
					d("ACTUAL_PARSER", "gpus completed somewhat")
					MainActivity.gpus.set(tempGPUs)
				})
				add(Thread {
					val cases = getJSONArray("case")
					val tempCases = MainActivity.pcCases.get()
					d("ACTUAL_PARSER", "case list started")
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
								ArrayList<CountedString?>().apply {
									getJSONObject("expansion-slots").apply {
										add(CountedString("full-height", getInt("full-height")))
										add(CountedString("half-height", getInt("half-height")))
									}
								},
								ArrayList<String>().apply {
									getJSONArray("front-panel-usb").apply {
										(0 until this.length()).forEach { i ->
											add(getString(i))
										}
									}
								},
								ArrayList<CountedString?>().apply {
									getJSONObject("internal-drive-bays").apply {
										add(CountedString("2-5", getInt("2-5")))
										add(CountedString("3-5", getInt("3-5")))
									}
								},
								ArrayList<String>().apply {
									getJSONArray("max-gpu-length").apply {
										(0 until this.length()).forEach { i ->
											add(getString(i))
										}
									}
								},
								ArrayList<String>().apply {
									getJSONArray("mb-form-factor").apply {
										(0 until this.length()).forEach { i ->
											add(getString(i))
										}
									}
								},
								getString("side-panel"),
								getString("type"),
								ArrayList<String>().apply {
									getJSONArray("volume").apply {
										(0 until this.length()).forEach { i ->
											add(getString(i))
										}
									}
								},
								getBoolean("psu-shroud")
							)
						}
					}
					d("ACTUAL_PARSER", "cases completed somewhat")
					MainActivity.pcCases.set(tempCases)
				})
				add(Thread {
					val psus = getJSONArray("psu")
					val tempPSUs = MainActivity.psus.get()
					d("ACTUAL_PARSER", "power supply list started")
					(0 until psus.length()).forEach {
						psus.getJSONObject(it).apply {
							tempPSUs += PSU(
								getString("model"),
								getString("manufacturer"),
								ArrayList<CountedString?>().apply {
									getJSONObject("connectors").apply {
										add(CountedString("eps", getInt("eps")))
										add(CountedString("molex-4-pin", getInt("molex-4-pin")))
										add(CountedString("pcie-6+2-pin", getInt("pcie-6+2-pin")))
										add(CountedString("sata", getInt("sata")))
									}
								},
								getString("efficiency-rating"),
								getBoolean("fanless"),
								getString("form-factor"),
								getInt("length-mm"),
								getString("modular"),
								getInt("wattage")
							)
						}
					}
					d("ACTUAL_PARSER", "power supply list completed somewhat")
					MainActivity.psus.set(tempPSUs)
				})
				add(Thread {
					val oss = getJSONArray("os")
					val tempOSs = MainActivity.oss.get()
					d("ACTUAL_PARSER", "os list started")
					(0 until oss.length()).forEach {
						oss.getJSONObject(it).apply {
							tempOSs += OS(
								getJSONObject("version").getString("edition"),
								getString("manufacturer"),
								getString("bit-mode"),
								getInt("max-memory-support-gb"),
								getString("type"),
								getJSONObject("version").getString("oem-retail"),
							)
						}
					}
					d("ACTUAL_PARSER", "os list completed somewhat")
					MainActivity.oss.set(tempOSs)
				})
				add(Thread {
					val monitors = getJSONArray("monitor")
					val tempMonitors = MainActivity.monitors.get()
					d("ACTUAL_PARSER", "monitor list started")
					d(
						"ACTUAL_PARSER",
						monitors.getJSONObject(0).getJSONArray("interface").toString()
					)
					(0 until monitors.length()).forEach {
						monitors.getJSONObject(it).apply {
							tempMonitors += Monitor(
								getString("model"),
								getString("manufacturer"),
								getString("aspect-ratio"),
								getInt("brightness-cd-per-sq-m"),
								getBoolean("curved-screen"),
								ArrayList<String>().apply {
									getJSONArray("frame-sync").apply {
										(0 until this.length()).forEach { i ->
											add(getString(i))
										}
									}
								},
								getString("hdr-tier"),
								getBoolean("inbuilt-speakers"),
								ArrayList<CountedString?>().apply {
									getJSONArray("interface").apply {
										(0 until this.length()).forEach { i ->
											add(
												CountedString(
													getJSONObject(i).getString("name"),
													getJSONObject(i).getInt("quantity")
												)
											)
										}
									}
								},
								getString("panel-type"),
								getDouble("refresh-rate-hz"),
								ArrayList<Int>().apply {
									add(
										getString("resolution").split("x")[0].substring(0, 4)
											.toInt()
									) // jank solution assuming BOTH resolution entries are 4 digits in length
									add(
										getString("resolution").split("x")[0].substring(0, 4)
											.toInt()
									)
								},
								getInt("response-time-ms"),
								getDouble("screen-size-in"),
								getString("viewing-angle"),
								getBoolean("widescreen")
							)
						}
					}
					d("ACTUAL_PARSER", "monitor list completed somewhat")
					MainActivity.monitors.set(tempMonitors)
				})
			}.forEach(Thread::start)
		}
	}
}
