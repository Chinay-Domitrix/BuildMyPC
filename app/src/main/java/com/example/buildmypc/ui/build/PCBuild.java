package com.example.buildmypc.ui.build;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.buildmypc.ui.parts.parts.CPU;
import com.example.buildmypc.ui.parts.parts.Case;
import com.example.buildmypc.ui.parts.parts.Cooler;
import com.example.buildmypc.ui.parts.parts.GPU;
import com.example.buildmypc.ui.parts.parts.Memory;
import com.example.buildmypc.ui.parts.parts.Monitor;
import com.example.buildmypc.ui.parts.parts.Motherboard;
import com.example.buildmypc.ui.parts.parts.OS;
import com.example.buildmypc.ui.parts.parts.PSU;
import com.example.buildmypc.ui.parts.parts.Part;
import com.example.buildmypc.ui.parts.parts.Storage;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PCBuild implements Parcelable {
	public static final Creator<PCBuild> CREATOR = new Creator<PCBuild>() {
		@NotNull
		@Contract("_ -> new")
		@Override
		public PCBuild createFromParcel(Parcel in) {
			return new PCBuild(in);
		}

		@NotNull
		@Contract(value = "_ -> new", pure = true)
		@Override
		public PCBuild[] newArray(int size) {
			return new PCBuild[size];
		}
	};
	private String name;
	private Drawable logo;
	private Case pcCase;
	private Cooler cooler;
	private CPU cpu;
	private GPU gpu;
	private Memory memory;
	private Monitor monitor;
	private Motherboard motherboard;
	private OS os;
	private PSU psu;
	private Storage storage;
	private ArrayList<Part> extraParts; // for all of your extra part needs (this won't be explicitly checked against, will possibly be a dropdown menu)

	private int idNumber;

	public PCBuild() {
	}

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

	public PCBuild(String name, Drawable logo, Case pcCase, Cooler cooler, CPU cpu, GPU gpu, Memory memory, Monitor monitor, Motherboard motherboard, OS os, PSU psu, Storage storage, ArrayList<Part> extraParts, int idNumber) {
		this.name = name;
		this.logo = logo;
		this.pcCase = pcCase;
		this.cooler = cooler;
		this.cpu = cpu;
		this.gpu = gpu;
		this.memory = memory;
		this.monitor = monitor;
		this.motherboard = motherboard;
		this.os = os;
		this.psu = psu;
		this.storage = storage;
		this.extraParts = extraParts;
		this.idNumber = idNumber;
	}

	public PCBuild(@NotNull Parcel in) {
		pcCase = in.readTypedObject(Case.CREATOR);
		cooler = in.readTypedObject(Cooler.CREATOR);
		cpu = in.readTypedObject(CPU.CREATOR);
		gpu = in.readTypedObject(GPU.CREATOR);
		memory = in.readTypedObject(Memory.CREATOR);
		monitor = in.readTypedObject(Monitor.CREATOR);
		motherboard = in.readTypedObject(Motherboard.CREATOR);
		os = in.readTypedObject(OS.CREATOR);
		psu = in.readTypedObject(PSU.CREATOR);
		storage = in.readTypedObject(Storage.CREATOR);
		extraParts = new ArrayList<>(in.createTypedArrayList(Part.CREATOR));
		idNumber = in.readInt();
	}

//	public PCBuild(Drawable logo){
//		this(" ", logo, null, null, null, null, null, null, null, null, null, null, null);
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Drawable getLogo() {
		return logo;
	}

	public void setLogo(Drawable logo) {
		this.logo = logo;
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

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public ArrayList<Part> getExtraParts() {
		return extraParts;
	}

	public void setExtraParts(ArrayList<Part> extraParts) {
		this.extraParts = extraParts;
	}

	public Case getPcCase() {
		return pcCase;
	}

	public void setPcCase(Case pcCase) {
		this.pcCase = pcCase;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int id) {
		this.idNumber = id;
	}

	@Override
	public boolean equals(Object o) { // hashcode and equals do NOT take into account idNumbers
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PCBuild pcBuild = (PCBuild) o;

		if (getName() != null ? !getName().equals(pcBuild.getName()) : pcBuild.getName() != null)
			return false;
		if (getLogo() != null ? !getLogo().equals(pcBuild.getLogo()) : pcBuild.getLogo() != null)
			return false;
		if (getPcCase() != null ? !getPcCase().equals(pcBuild.getPcCase()) : pcBuild.getPcCase() != null)
			return false;
		if (getCooler() != null ? !getCooler().equals(pcBuild.getCooler()) : pcBuild.getCooler() != null)
			return false;
		if (getCpu() != null ? !getCpu().equals(pcBuild.getCpu()) : pcBuild.getCpu() != null)
			return false;
		if (getGpu() != null ? !getGpu().equals(pcBuild.getGpu()) : pcBuild.getGpu() != null)
			return false;
		if (getMemory() != null ? !getMemory().equals(pcBuild.getMemory()) : pcBuild.getMemory() != null)
			return false;
		if (getMonitor() != null ? !getMonitor().equals(pcBuild.getMonitor()) : pcBuild.getMonitor() != null)
			return false;
		if (getMotherboard() != null ? !getMotherboard().equals(pcBuild.getMotherboard()) : pcBuild.getMotherboard() != null)
			return false;
		if (getOs() != null ? !getOs().equals(pcBuild.getOs()) : pcBuild.getOs() != null)
			return false;
		if (getPsu() != null ? !getPsu().equals(pcBuild.getPsu()) : pcBuild.getPsu() != null)
			return false;
		if (getStorage() != null ? !getStorage().equals(pcBuild.getStorage()) : pcBuild.getStorage() != null)
			return false;
		return getExtraParts() != null ? getExtraParts().equals(pcBuild.getExtraParts()) : pcBuild.getExtraParts() == null;
	}

	@Override
	public int hashCode() {
		int result = getName() != null ? getName().hashCode() : 0;
		result = 31 * result + (getLogo() != null ? getLogo().hashCode() : 0);
		result = 31 * result + (getPcCase() != null ? getPcCase().hashCode() : 0);
		result = 31 * result + (getCooler() != null ? getCooler().hashCode() : 0);
		result = 31 * result + (getCpu() != null ? getCpu().hashCode() : 0);
		result = 31 * result + (getGpu() != null ? getGpu().hashCode() : 0);
		result = 31 * result + (getMemory() != null ? getMemory().hashCode() : 0);
		result = 31 * result + (getMonitor() != null ? getMonitor().hashCode() : 0);
		result = 31 * result + (getMotherboard() != null ? getMotherboard().hashCode() : 0);
		result = 31 * result + (getOs() != null ? getOs().hashCode() : 0);
		result = 31 * result + (getPsu() != null ? getPsu().hashCode() : 0);
		result = 31 * result + (getStorage() != null ? getStorage().hashCode() : 0);
		result = 31 * result + (getExtraParts() != null ? getExtraParts().hashCode() : 0);
		return result;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NotNull Parcel dest, int flags) {
		dest.writeParcelable(pcCase, flags);
		dest.writeParcelable(cooler, flags);
		dest.writeParcelable(cpu, flags);
		dest.writeParcelable(gpu, flags);
		dest.writeParcelable(memory, flags);
		dest.writeParcelable(monitor, flags);
		dest.writeParcelable(motherboard, flags);
		dest.writeParcelable(os, flags);
		dest.writeParcelable(psu, flags);
		dest.writeParcelable(storage, flags);
		dest.writeParcelableList(extraParts, flags);
		dest.writeInt(idNumber);
	}

	@NonNull
	@NotNull
	@Override
	public String toString() {
		return getName() + super.toString();
	}
}