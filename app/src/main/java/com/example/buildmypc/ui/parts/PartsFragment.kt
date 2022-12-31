package com.example.buildmypc.ui.parts

import android.annotation.SuppressLint
import android.app.AlertDialog.Builder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.*
import com.example.buildmypc.MainActivity.Companion.coolers
import com.example.buildmypc.MainActivity.Companion.cpus
import com.example.buildmypc.MainActivity.Companion.gpus
import com.example.buildmypc.MainActivity.Companion.memory
import com.example.buildmypc.MainActivity.Companion.monitors
import com.example.buildmypc.MainActivity.Companion.motherboards
import com.example.buildmypc.MainActivity.Companion.oss
import com.example.buildmypc.MainActivity.Companion.pcCases
import com.example.buildmypc.MainActivity.Companion.psus
import com.example.buildmypc.MainActivity.Companion.storage
import com.example.buildmypc.R.id.*
import com.example.buildmypc.R.layout.parts_list
import com.example.buildmypc.R.layout.popup
import com.example.buildmypc.databinding.FragmentPartsBinding
import com.example.buildmypc.databinding.FragmentPartsBinding.inflate
import com.example.buildmypc.ui.parts.PartsFragment.PartsRecyclerViewAdapter.RecyclerViewHolder
import com.example.buildmypc.ui.parts.parts.*

class PartsFragment : Fragment() {
	private lateinit var binding: FragmentPartsBinding
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = inflate(inflater, container, false)
		val root = binding.root
		// RecyclerView time
		// --> using the same XML for each
		// CPU, Cooler, Motherboard Memory, Storage, GPU, Case, PSU, OS, Monitor, extraParts
		binding.apply {
			arrayOf(
				partsCpuRecyclerview.apply {
					adapter = PartsRecyclerViewAdapter(cpus.get())
				},
				partsCoolerRecyclerview.apply { adapter = PartsRecyclerViewAdapter(coolers.get()) },
				partsMotherboardRecyclerview.apply {
					adapter = PartsRecyclerViewAdapter(motherboards.get())
				},
				partsStorageRecyclerview.apply {
					adapter = PartsRecyclerViewAdapter(storage.get())
				},
				partsGpuRecyclerview.apply { adapter = PartsRecyclerViewAdapter(gpus.get()) },
				partsCaseRecyclerview.apply { adapter = PartsRecyclerViewAdapter(pcCases.get()) },
				partsPsuRecyclerview.apply { adapter = PartsRecyclerViewAdapter(psus.get()) },
				partsOsRecyclerview.apply { adapter = PartsRecyclerViewAdapter(oss.get()) },
				partsMonitorRecyclerview.apply {
					adapter = PartsRecyclerViewAdapter(monitors.get())
				},
				partsMemoryRecyclerview.apply {
					adapter = PartsRecyclerViewAdapter(memory.get())
				}).forEach { it.layoutManager = LinearLayoutManager(context) }
		}
		return root
	}

	inner class PartsRecyclerViewAdapter(private val internalList: ArrayList<Part>) :
		Adapter<RecyclerViewHolder?>() {
		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
			// where the XML is inflated
			RecyclerViewHolder(from(context).inflate(parts_list, parent, false))

		override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
			// the method where info is set for EACH individual layout element for each list entry
			val currentPart = internalList[position]
			// since only the basic data is displayed, we'll do that first
			holder.nameTextView.text = currentPart.toString()
			holder.internalButton.setOnClickListener { createNewPopupWindow(currentPart) } // this is where the pop-up window will be
//			TODO write this and write the clickable code to make a popup window (specifics of the display determined by a switch(getCLass().getName()))
		}

		override fun getItemCount() = internalList.size

		@SuppressLint("SetTextI18n")
		fun createNewPopupWindow(part: Part) {
			val dialogBuilder = Builder(context)
			val infoPopUp = layoutInflater.inflate(popup, null)
			val tvs = Array(20) { arrayOfNulls<TextView>(2) }
//			I, Alkiepoodlez Kurz, don't know how to automatically do this, so it's manual time
			infoPopUp.apply {
				tvs[0][0] = findViewById(popup_title1)
				tvs[0][1] = findViewById(popup_message1)
				tvs[1][0] = findViewById(popup_title2)
				tvs[1][1] = findViewById(popup_message2)
				tvs[2][0] = findViewById(popup_title3)
				tvs[2][1] = findViewById(popup_message3)
				tvs[3][0] = findViewById(popup_title4)
				tvs[3][1] = findViewById(popup_message4)
				tvs[4][0] = findViewById(popup_title5)
				tvs[4][1] = findViewById(popup_message5)
				tvs[5][0] = findViewById(popup_title6)
				tvs[5][1] = findViewById(popup_message6)
				tvs[6][0] = findViewById(popup_title7)
				tvs[6][1] = findViewById(popup_message7)
				tvs[7][0] = findViewById(popup_title8)
				tvs[7][1] = findViewById(popup_message8)
				tvs[8][0] = findViewById(popup_title9)
				tvs[8][1] = findViewById(popup_message9)
				tvs[9][0] = findViewById(popup_title10)
				tvs[9][1] = findViewById(popup_message10)
				tvs[10][0] = findViewById(popup_title11)
				tvs[10][1] = findViewById(popup_message11)
				tvs[11][0] = findViewById(popup_title12)
				tvs[11][1] = findViewById(popup_message12)
				tvs[12][0] = findViewById(popup_title13)
				tvs[12][1] = findViewById(popup_message13)
				tvs[13][0] = findViewById(popup_title14)
				tvs[13][1] = findViewById(popup_message14)
				tvs[14][0] = findViewById(popup_title15)
				tvs[14][1] = findViewById(popup_message15)
				tvs[15][0] = findViewById(popup_title16)
				tvs[15][1] = findViewById(popup_message16)
				tvs[16][0] = findViewById(popup_title17)
				tvs[16][1] = findViewById(popup_message17)
				tvs[17][0] = findViewById(popup_title18)
				tvs[17][1] = findViewById(popup_message18)
				tvs[18][0] = findViewById(popup_title19)
				tvs[18][1] = findViewById(popup_message19)
				tvs[19][0] = findViewById(popup_title20)
				tvs[19][1] = findViewById(popup_message20)
			}
			var str: StringBuilder
			var output: StringBuilder
			val name: TextView
			when (part.javaClass.simpleName) {
				"CPU" -> {
					part as CPU
					name =
						infoPopUp.findViewById(popup_superTitleTextView) // the name text view in a series
					name.text = part.name
					tvs[0][0]?.text = "Manufacturer:"
					tvs[0][1]?.text = part.manufacturer
					tvs[1][0]?.text = "# of Cores:"
					tvs[1][1]?.text = "${part.coreCount}"
					tvs[2][0]?.text = "Clock Speed:"
					tvs[2][1]?.text = "${part.coreClock} GHz"
					tvs[3][0]?.text = "Boost Clock Speed:"
					tvs[3][1]?.text = "${part.boostClock} GHz"
					tvs[4][0]?.text = "Thermal Design Power:"
					tvs[4][1]?.text = "${part.tdp} W"
					tvs[5][0]?.text = "Microarchitecture:"
					tvs[5][1]?.text = part.microarchitecture
					tvs[6][0]?.text = "Core Family:"
					tvs[6][1]?.text = part.coreFamily
					tvs[7][0]?.text = "Socket Type"
					tvs[7][1]?.text = part.socket
					tvs[8][0]?.text = "Has Integrated GPU?: "
					tvs[8][1]?.text = if (part.isiGPU()) "Yes" else "No"
					tvs[9][0]?.text = "Max Supported Memory: "
					tvs[9][1]?.text = "${part.maxMemory} GB"
					tvs[10][0]?.text = "Can error-correct?: "
					tvs[10][1]?.text = if (part.isEcc) "Yes" else "No"
					tvs[11][0]?.text = "Has an internal cooler?: "
					tvs[11][1]?.text = if (part.isCooler) "Yes" else "No"
					tvs[12][0]?.text = "Has s. multithreading?: "
					tvs[12][1]?.text = if (part.isSmt) "Yes" else "No"
					var i = 13
					while (i < tvs.size) {
						tvs[i][0]?.visibility = GONE
						tvs[i][1]?.visibility = GONE
						i++
					}
				}
				"Cooler" -> {
					part as Cooler
					name =
						infoPopUp.findViewById(popup_superTitleTextView) // The name TextView in a series
					name.text = part.name
					tvs[0][0]?.text = "Manufacturer:"
					tvs[0][1]?.text = part.manufacturer
					tvs[1][0]?.text = "Watercooled or Fan?: "
					tvs[1][1]?.text = if (part.isWaterCooled) "Watercooled" else "Fan"
					tvs[2][0]?.text = "Includes Fan: "
					tvs[2][1]?.text = if (part.isFanless) "Yes" else "No"
					tvs[3][0]?.text = "Height: "
					tvs[3][1]?.text = "${part.height} mm"
					tvs[4][0]?.text = "Noise Level: "
					tvs[4][1]?.text = part.noiseLevel + " dB"
					tvs[5][0]?.text = "Supported Sockets: \n"
//					Build the string of all the supported sockets
					str = StringBuilder()
					for (s in part.socketSupport!!) str.append(s).append("\n")
					str.substring(str.length - 2)
					tvs[5][1]?.text = str
					if (!part.isFanless) {
						tvs[6][0]?.text = "Fan RPM:"
						tvs[6][1]?.text = part.rpm + " rpm"
					} else {
						tvs[6][0]?.visibility = GONE
						tvs[6][1]?.visibility = GONE
					}
					var i = 7
					while (i < tvs.size) {
						tvs[i][0]?.visibility = GONE
						tvs[i][1]?.visibility = GONE
						i++
					}
				}
				"Motherboard" -> {
					part as Motherboard
					name =
						infoPopUp.findViewById(popup_superTitleTextView) // The name TextView in a series
					name.text = part.name
					tvs[0][0]?.text = "Manufacturer:"
					tvs[0][1]?.text = part.manufacturer
					tvs[1][0]?.text = "Can error-correct?: "
					tvs[1][1]?.text = if (part.isEcc) "Yes" else "No"
					tvs[2][0]?.text = "Chipset"
					tvs[2][1]?.text = part.chipset
					tvs[3][0]?.text = "Form Factor:"
					tvs[3][1]?.text = part.formFactor
//					TODO format this properly
					tvs[4][0]?.text = "M.2 slots:" // m2slots
					str = StringBuilder()
					part.m2slots!!.forEach { str.append(it).append("\n") }
					str.substring(str.length - 2)
					tvs[4][1]?.text = str
					tvs[5][0]?.text = "Max Supported Memory:"
					tvs[5][1]?.text = "${part.maxMemSupport} GB"
					tvs[6][0]?.text = "# of Memory Slots: "
					tvs[6][1]?.setText(part.memSlots)
					tvs[7][0]?.text = "Compatible Memory Types: "
					str = StringBuilder()
					part.compatibleMem!!.forEach { str.append("$it\n") }
					str.substring(str.length - 2)
					tvs[7][1]?.text = str
					tvs[8][0]?.text = "mSATA Slot Count:"
					tvs[8][1]?.setText(part.getmSATASlotCount())
					tvs[9][0]?.text = "Compatible Ethernet Types: "
					str = StringBuilder()
					part.incEthernetSupp!!.forEach { str.append("$it\n") }
					str.substring(str.length - 2)
					tvs[9][1]?.text = str
					tvs[10][0]?.text = "Included GPU: "
					tvs[10][1]?.text = part.incVideo
					tvs[11][0]?.text = "PCI Slot List: "
					str = StringBuilder()
					part.pciSlotList!!.filterNotNull()
						.forEach { str.append("${it.name}, ${it.amount}\n") }
					str.substring(str.length - 2)
					tvs[11][1]?.text = str
					tvs[12][0]?.text = "Has RAID?: "
					tvs[12][1]?.text = if (part.isHasRAID) "Yes" else "No"
					tvs[13][0]?.text = "SATA 6GB/s Count: "
					tvs[13][1]?.setText(part.sata6gbpsCount)
					tvs[14][0]?.text = "Gen 2 USB Count: "
					tvs[14][1]?.setText(part.gen2USBCount)
					tvs[15][0]?.text = "Gen 3.2 USB Count: "
					str = StringBuilder()
					part.gen32USBcount!!.filterNotNull()
						.forEach { str.append("${it.name}, ${it.amount}\n") }
					str.substring(str.length - 2)
					tvs[15][1]?.text = str
					tvs[16][0]?.text = "Wireless Type: "
					tvs[16][1]?.text = part.wireless
					var i = 17
					while (i < tvs.size) {
						tvs[i][0]?.visibility = GONE
						tvs[i][1]?.visibility = GONE
						i++
					}
				}
				"Memory" -> {
					part as Memory
					name =
						infoPopUp.findViewById(popup_superTitleTextView) // The name TextView in a series
					name.text = part.name
					tvs[0][0]?.text = "Manufacturer:"
					tvs[0][1]?.text = part.manufacturer
					tvs[1][0]?.text = "Can error-correct?:"
					tvs[1][1]?.text = if (part.hasECC) "Yes" else "No"
					tvs[2][0]?.text = "CAS Latency:"
					tvs[2][1]?.text = "${part.latencyCAS} cycles"
					tvs[3][0]?.text = "DDR Generation:"
					tvs[3][1]?.setText(part.ddrGen)
					tvs[4][0]?.text = "First Word Latency"
					tvs[4][1]?.text = "${part.firstWordLatency} ns"
					tvs[5][0]?.text = "Form Factor"
					tvs[5][1]?.text = part.formFactor
					tvs[6][0]?.text = "Has a Heat Spreader?:"
					tvs[6][1]?.text = if (part.heatSpreader) "Yes" else "No"
					tvs[7][0]?.text = "Module Size:"
					tvs[7][1]?.text = "${part.moduleSize} GB"
					tvs[8][0]?.text = "Module Count:"
					tvs[8][1]?.setText(part.moduleCount)
					tvs[9][0]?.text = "RAM Speed:"
					tvs[9][1]?.text = "${part.moduleSize} MHz"
					tvs[10][0]?.text = "Timing:"
					tvs[10][1]?.text = part.timing
					tvs[11][0]?.text = "Voltage:"
					tvs[11][1]?.text = part.voltage.toString()
					if (tvs[11][1]?.text == "null") tvs[11][1]?.text = "N/A"
					var i = 12
					while (i < tvs.size) {
						tvs[i][0]?.visibility = GONE
						tvs[i][1]?.visibility = GONE
						i++
					}
				}
				"Storage" -> {
					part as Storage
					name =
						infoPopUp.findViewById(popup_superTitleTextView) // The name TextView in a series
					name.text = part.name
					tvs[0][0]?.text = "Manufacturer:"
					tvs[0][1]?.text = part.manufacturer
					tvs[1][0]?.text = "Type:"
					tvs[1][1]?.text = part.type
					tvs[2][0]?.text = "Form Factor:"
					tvs[2][1]?.text = part.formFactor
					tvs[3][0]?.text = "Cache Size:"
					tvs[3][1]?.text = "${part.cacheSizeMB} MB"
					tvs[4][0]?.text = "Capacity:"
					tvs[4][1]?.text = part.capacity
					tvs[5][0]?.text = "SATA Interface:"
					tvs[5][1]?.text = part.sataInterface
					tvs[6][0]?.text = "Has NVM Express?:"
					tvs[6][1]?.text = if (part.nvme) "Yes" else "No"
					tvs[7][0]?.text = "RPM:"
					tvs[7][1]?.text = "${part.rpm} rpm"
					if (tvs[7][1]?.text == "null") tvs[7][1]?.text = "N/A"
					tvs[8][0]?.text = "Capacity:"
					tvs[8][1]?.text = part.capacity
					var i = 9
					while (i < tvs.size) {
						tvs[i][0]?.visibility = GONE
						tvs[i][1]?.visibility = GONE
						i++
					}
				}
				"GPU" -> {
					part as GPU
					name =
						infoPopUp.findViewById(popup_superTitleTextView) // The name TextView in a series
					name.text = part.name
					tvs[0][0]?.text = "Manufacturer:"
					tvs[0][1]?.text = part.manufacturer
					tvs[1][0]?.text = "Core Clock Speed:"
					tvs[1][1]?.text = "${part.coreClockSpeed} MHz"
					tvs[2][0]?.text = "Boost Clock Speed:"
					tvs[2][1]?.text = "${part.boostClockSpeed} MHz"
					tvs[3][0]?.text = "Effective Memory Clock Speed:"
					tvs[3][1]?.text = "${part.effectiveMemClockSpeed} MHz"
					tvs[4][0]?.text = "Chipset:"
					tvs[4][1]?.text = part.chipset
					tvs[5][0]?.text = "Cooling:"
					tvs[5][1]?.text = part.cooling
					tvs[6][0]?.text = "Expansion Slot Width:"
					tvs[6][1]?.text = "${part.expansionSlotWidth} cm"
					tvs[7][0]?.text = "External Power:"
					tvs[7][1]?.text = part.externalPower
					tvs[8][0]?.text = "GPU Interface:"
					tvs[8][1]?.text = part.frameSync
					tvs[9][0]?.text = "Component Length:"
					tvs[9][1]?.text = "${part.length} mm"
					tvs[10][0]?.text = "Internal GPU Memory:"
					tvs[10][1]?.text = "${part.intMemory} GB"
					tvs[11][0]?.text = "Internal Memory Type:"
					tvs[11][1]?.text = part.intMemoryType
					tvs[12][0]?.text = "Thermal Design Power:"
					tvs[12][1]?.text = "${part.tdpW} W"
					tvs[13][0]?.text = "Compatible Video Ports:"
					str = StringBuilder()
					part.videoPorts!!.filterNotNull()
						.forEach { str.append("${it.name}, ${it.amount}\n") }
					str.substring(str.length - 2)
					tvs[13][1]?.text = str
					var i = 14
					while (i < tvs.size) {
						tvs[i][0]?.visibility = GONE
						tvs[i][1]?.visibility = GONE
						i++
					}
				}
				"Case" -> {
					part as Case
					name =
						infoPopUp.findViewById(popup_superTitleTextView) // The name TextView in a series
					name.text = part.name
					tvs[0][0]?.text = "Manufacturer:"
					tvs[0][1]?.text = part.manufacturer
					tvs[1][0]?.text = "Type:"
					tvs[1][1]?.text = part.type
					tvs[2][0]?.text = "Dimensions:"
					val caseListStr = part.dimensionsMm
					tvs[2][1]?.text = "${caseListStr?.get(0)}\n${caseListStr?.get(1)}"
					tvs[3][0]?.text = "Expansion Slots:"
					output = StringBuilder()
					part.expansionSlots!!.filterNotNull()
						.forEach { output.append("${it.name}, ${it.amount}\n") }
					output.substring(output.length - 2)
					tvs[3][1]?.text = output
					tvs[4][0]?.text = "Supported Front USB Types:"
					output = StringBuilder()
					part.supportedFrontUSBs!!.forEach { output.append("$it,\n") }
					output.substring(output.length - 3)
					tvs[4][1]?.text = output
					tvs[5][0]?.text = "Internal Drive Bays:"
					output = StringBuilder()
					part.internalDriveBays!!.filterNotNull()
						.forEach { output.append("${it.name}, ${it.amount}\n") }
					output.substring(output.length - 2)
					tvs[5][1]?.text = output
					tvs[6][0]?.text = "Max GPU Lengths:"
					output = StringBuilder()
					part.maxGPULength!!.forEach { output.append("$it\n") }
					output.substring(output.length - 2)
					tvs[6][1]?.text = output
					tvs[7][0]?.text = "Motherboard Form Factor:"
					output = StringBuilder()
					part.mbFormFactor!!.forEach { output.append("$it,\n") }
					output.substring(output.length - 3)
					tvs[7][1]?.text = output
					tvs[8][0]?.text = "Side Panel:"
					tvs[8][1]?.text = part.sidePanel
					tvs[9][0]?.text = "Volume:"
					output = StringBuilder()
					part.volume!!.forEach { output.append("$it\n") }
					output.substring(output.length - 2)
					tvs[9][1]?.text = output
					tvs[10][0]?.text = "Has PSU Shroud?:"
					tvs[10][1]?.text = if (part.isPsuShroud) "Yes" else "No"
					var i = 11
					while (i < tvs.size) {
						tvs[i][0]?.visibility = GONE
						tvs[i][1]?.visibility = GONE
						i++
					}
				}
				"PSU" -> {
					part as PSU
					name =
						infoPopUp.findViewById(popup_superTitleTextView) // The name TextView in a series
					name.text = part.name
					tvs[0][0]?.text = "Manufacturer:"
					tvs[0][1]?.text = part.manufacturer
					tvs[1][0]?.text = "Internal Drive Bays:"
					output = StringBuilder()
					part.connectorList!!.filterNotNull()
						.forEach { output.append("${it.name}, ${it.amount}\n") }
					output.substring(output.length - 2)
					tvs[1][1]?.text = output
					tvs[2][0]?.text = "Efficiency Rating"
					tvs[2][1]?.text = part.efficiencyRating
					tvs[3][0]?.text = "Includes a Fan?:"
					tvs[3][1]?.text = if (part.isHasFan) "Yes" else "No"
					tvs[4][0]?.text = "Form Factor:"
					tvs[4][1]?.text = part.formFactor
					tvs[5][0]?.text = "Component Length:"
					tvs[5][1]?.text = "${part.length} mm"
					tvs[6][0]?.text = "Modular:"
					tvs[6][1]?.text = part.modular
					tvs[7][0]?.text = "Wattage:"
					tvs[7][1]?.text = "${part.wattage} W"
					var i = 8
					while (i < tvs.size) {
						tvs[i][0]?.visibility = GONE
						tvs[i][1]?.visibility = GONE
						i++
					}
				}
				"OS" -> {
					part as OS
					name =
						infoPopUp.findViewById(popup_superTitleTextView) // The name TextView in a series
					name.text = part.name
					tvs[0][0]?.text = "Manufacturer:"
					tvs[0][1]?.text = part.manufacturer
					tvs[1][0]?.text = "Bit Mode:"
					tvs[1][1]?.text = part.bitMode
					tvs[2][0]?.text = "Max Memory Support:"
					tvs[2][1]?.setText(part.maxMemSupport)
					tvs[3][0]?.text = "Type:"
					tvs[3][1]?.text = part.type
					tvs[4][0]?.text = "OEM or Retail:"
					tvs[4][1]?.text = part.oemRetail
					var i = 5
					while (i < tvs.size) {
						tvs[i][0]?.visibility = GONE
						tvs[i][1]?.visibility = GONE
						i++
					}
				}
				"Monitor" -> {
					part as Monitor
					name =
						infoPopUp.findViewById(popup_superTitleTextView) // The name TextView in a series
					name.text = part.name
					tvs[0][0]?.text = "Manufacturer:"
					tvs[0][1]?.text = part.manufacturer
					tvs[1][0]?.text = "Aspect Ratio:"
					tvs[1][1]?.text = part.aspectRatio
					tvs[2][0]?.text = "Brightness:"
					tvs[2][1]?.text = "${part.brightness} cd/m^2"
					tvs[3][0]?.text = "Is Curved?:"
					tvs[3][1]?.text = if (part.isCurved) "Yes" else "No"
					tvs[4][0]?.text = "Frame Sync:"
					output = StringBuilder()
					part.frameSync!!.forEach { output.append("$it\n") }
					output.substring(output.length - 2)
					tvs[4][1]?.text = output
					tvs[5][0]?.text = "HDR Tier:"
					tvs[5][1]?.text = part.hdrTier
					if (tvs[5][1]?.text == "null") tvs[5][1]?.text = "N/A"
					tvs[6][0]?.text = "Has Built-in Speakers?:"
					tvs[6][1]?.text = if (part.isBuiltInSpeakers) "Yes" else "No"
					tvs[7][0]?.text = "Compatible Monitor Interfaces:"
					output = StringBuilder()
					part.monitorInterfaces!!.filterNotNull()
						.forEach { output.append("${it.name}, ${it.amount}\n") }
					output.substring(output.length - 2)
					tvs[1][1]?.text = output
					tvs[8][0]?.text = "Panel Type:"
					tvs[8][1]?.text = part.panelType
					tvs[9][0]?.text = "Refresh Rate:"
					tvs[9][1]?.text = "${part.refreshRate} Hz"
					tvs[10][0]?.text = "Resolution:"
					tvs[10][1]?.text = "${part.resolution?.get(0)} by ${part.resolution?.get(1)}"
					tvs[11][0]?.text = "PC Response Time:"
					tvs[11][1]?.text = "${part.responseTimeMs} ms"
					tvs[12][0]?.text = "Screen Size:"
					tvs[12][1]?.text = "${part.screenSizeIn} in (diagonal)"
					tvs[13][0]?.text = "Viewing Angle:"
					tvs[13][1]?.text = part.viewingAngle
					tvs[14][0]?.text = "Is Widescreen?:"
					tvs[14][1]?.text = if (part.isWidescreen) "Yes" else "No"
					var i = 14
					while (i < tvs.size) {
						tvs[i][0]?.visibility = GONE
						tvs[i][1]?.visibility = GONE
						i++
					}
				}
			}
//			Popup window declarations
			dialogBuilder.setView(infoPopUp).create().show()
		}

		inner class RecyclerViewHolder(itemView: View) : ViewHolder(itemView) {
			// This is where the findViewById stuff goes for each element, and only the findViewById
			val nameTextView: TextView = itemView.findViewById(partsList_nameTextView)
			val internalButton: Button = itemView.findViewById(partsList_addButton)
		}
	}
}