package com.example.buildmypc.ui.build;

import android.graphics.drawable.Drawable;

import com.example.buildmypc.ui.parts.parts.CPU;
import com.example.buildmypc.ui.parts.parts.Cooler;
import com.example.buildmypc.ui.parts.parts.GPU;
import com.example.buildmypc.ui.parts.parts.Memory;
import com.example.buildmypc.ui.parts.parts.Monitor;
import com.example.buildmypc.ui.parts.parts.Motherboard;
import com.example.buildmypc.ui.parts.parts.OS;
import com.example.buildmypc.ui.parts.parts.PSU;
import com.example.buildmypc.ui.parts.parts.Part;
import com.example.buildmypc.ui.parts.parts.StorageComp;

import java.util.ArrayList;

public class PCBuild {

	private String name;
	private Drawable logo;

	private Cooler cooler;
	private CPU cpu;
	private GPU gpu;
	private Memory memory;
	private Monitor monitor;
	private Motherboard motherboard;
	private OS os;
	private PSU psu;
	private StorageComp storageComp;
	private ArrayList<Part> extraParts; // for all of your extra part needs (this won't be explicitly checked against, will possibly be a dropdown menu)

	public PCBuild() {
	}

	public PCBuild(String name, Drawable logo, Cooler cooler, CPU cpu, GPU gpu, Memory memory, Monitor monitor, Motherboard motherboard, OS os, PSU psu, StorageComp storageComp, ArrayList<Part> extraParts) {
		this.name = name;
		this.logo = logo;
		this.cooler = cooler;
		this.cpu = cpu;
		this.gpu = gpu;
		this.memory = memory;
		this.monitor = monitor;
		this.motherboard = motherboard;
		this.os = os;
		this.psu = psu;
		this.storageComp = storageComp;
		this.extraParts = extraParts;
	}

	public Cooler getCooler() {
		return cooler;
	}

	public void setCooler(Cooler cooler) {
		this.cooler = cooler;
	}

	public CPU getCpu() {
		return cpu;
	}

	public void setCpu(CPU cpu) {
		this.cpu = cpu;
	}

	public GPU getGpu() {
		return gpu;
	}

	public void setGpu(GPU gpu) {
		this.gpu = gpu;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public Motherboard getMotherboard() {
		return motherboard;
	}

	public void setMotherboard(Motherboard motherboard) {
		this.motherboard = motherboard;
	}

	public OS getOs() {
		return os;
	}

	public void setOs(OS os) {
		this.os = os;
	}

	public PSU getPsu() {
		return psu;
	}

	public void setPsu(PSU psu) {
		this.psu = psu;
	}

	public StorageComp getStorageComp() {
		return storageComp;
	}

	public void setStorageComp(StorageComp storageComp) {
		this.storageComp = storageComp;
	}

	public ArrayList<Part> getExtraParts() {
		return extraParts;
	}

	public void setExtraParts(ArrayList<Part> extraParts) {
		this.extraParts = extraParts;
	}

	public Drawable getLogo() {
		return logo;
	}

	public void setLogo(Drawable logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
