package com.example.buildmypc.ui.parts.parts;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import static android.os.Build.VERSION_CODES.Q;

public class Motherboard extends Part implements Parcelable {
	private boolean ecc; // has error correction coding (all chips should tbh)
	private String chipset;
	private String formFactor;
	private ArrayList<String> m2slots; // no clue what this is
	private int maxMemSupport;
	private int memSlots;
	private ArrayList<String> compatibleMem;
	private String memType; // pretty much always DDR4
	private int mSATA_slotCount; // love SSDs
	private ArrayList<String> incEthernetSupp;
	private String incVideo; // no idea what this is
	private ArrayList<CountedString> PCISlotList; // had to create a subobject for this
	private boolean hasRAID; // has "Redundant Array of Inexpensive Disks"
	private int sata6gbpsCount; // no clue
	private int gen2USBCount;
	private ArrayList<CountedString> gen32USBcount; // I don't feel like making a new USB object to only be used once
	private boolean isWireless;

	public Motherboard(String model, String manufacturer) {
		super(model, manufacturer);
	}

	public Motherboard(String model, String manufacturer, boolean ecc, String chipset, String formFactor, ArrayList<String> m2slots, int maxMemSupport, int memSlots, ArrayList<String> compatibleMem, String memType, int mSATA_slotCount, ArrayList<String> incEthernetSupp, String incVideo, ArrayList<CountedString> PCISlotList, boolean hasRAID, int sata6gbpsCount, int gen2USBCount, ArrayList<CountedString> gen32USBcount, boolean hasWireless) {
		this(model, manufacturer);
		this.ecc = ecc;
		this.chipset = chipset;
		this.formFactor = formFactor;
		this.m2slots = m2slots;
		this.maxMemSupport = maxMemSupport;
		this.memSlots = memSlots;
		this.compatibleMem = compatibleMem;
		this.memType = memType;
		this.mSATA_slotCount = mSATA_slotCount;
		this.incEthernetSupp = incEthernetSupp;
		this.incVideo = incVideo;
		this.PCISlotList = PCISlotList;
		this.hasRAID = hasRAID;
		this.sata6gbpsCount = sata6gbpsCount;
		this.gen2USBCount = gen2USBCount;
		this.gen32USBcount = gen32USBcount;
		this.isWireless = hasWireless;
	}

	public boolean isEcc() {
		return ecc;
	}

	public void setEcc(boolean ecc) {
		this.ecc = ecc;
	}

	public String getChipset() {
		return chipset;
	}

	public void setChipset(String chipset) {
		this.chipset = chipset;
	}

	public String getFormFactor() {
		return formFactor;
	}

	public void setFormFactor(String formFactor) {
		this.formFactor = formFactor;
	}

	public ArrayList<String> getM2slots() {
		return m2slots;
	}

	public void setM2slots(ArrayList<String> m2slots) {
		this.m2slots = m2slots;
	}

	public int getMaxMemSupport() {
		return maxMemSupport;
	}

	public void setMaxMemSupport(int maxMemSupport) {
		this.maxMemSupport = maxMemSupport;
	}

	public int getMemSlots() {
		return memSlots;
	}

	public void setMemSlots(int memSlots) {
		this.memSlots = memSlots;
	}

	public ArrayList<String> getCompatibleMem() {
		return compatibleMem;
	}

	public void setCompatibleMem(ArrayList<String> compatibleMem) {
		this.compatibleMem = compatibleMem;
	}

	public String getMemType() {
		return memType;
	}

	public void setMemType(String memType) {
		this.memType = memType;
	}

	public int getmSATA_slotCount() {
		return mSATA_slotCount;
	}

	public void setmSATA_slotCount(int mSATA_slotCount) {
		this.mSATA_slotCount = mSATA_slotCount;
	}

	public ArrayList<String> getIncEthernetSupp() {
		return incEthernetSupp;
	}

	public void setIncEthernetSupp(ArrayList<String> incEthernetSupp) {
		this.incEthernetSupp = incEthernetSupp;
	}

	public String getIncVideo() {
		return incVideo;
	}

	public void setIncVideo(String incVideo) {
		this.incVideo = incVideo;
	}

	public ArrayList<CountedString> getPciSlotList() {
		return PCISlotList;
	}

	public void setPciSlotList(ArrayList<CountedString> PCISlotList) {
		this.PCISlotList = PCISlotList;
	}

	public boolean isHasRAID() {
		return hasRAID;
	}

	public void setHasRAID(boolean hasRAID) {
		this.hasRAID = hasRAID;
	}

	public int getSata6gbpsCount() {
		return sata6gbpsCount;
	}

	public void setSata6gbpsCount(int sata6gbpsCount) {
		this.sata6gbpsCount = sata6gbpsCount;
	}

	public int getGen2USBCount() {
		return gen2USBCount;
	}

	public void setGen2USBCount(int gen2USBCount) {
		this.gen2USBCount = gen2USBCount;
	}

	public ArrayList<CountedString> getGen32USBcount() {
		return gen32USBcount;
	}

	public void setGen32USBcount(ArrayList<CountedString> gen32USBcount) {
		this.gen32USBcount = gen32USBcount;
	}

	public boolean isWireless() {
		return isWireless;
	}

	public void setWireless(boolean wireless) {
		isWireless = wireless;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Motherboard that = (Motherboard) o;

		if (isEcc() != that.isEcc()) return false;
		if (getMaxMemSupport() != that.getMaxMemSupport()) return false;
		if (getMemSlots() != that.getMemSlots()) return false;
		if (getmSATA_slotCount() != that.getmSATA_slotCount()) return false;
		if (isHasRAID() != that.isHasRAID()) return false;
		if (getSata6gbpsCount() != that.getSata6gbpsCount()) return false;
		if (getGen2USBCount() != that.getGen2USBCount()) return false;
		if (isWireless() != that.isWireless()) return false;
		if (getChipset() != null ? !getChipset().equals(that.getChipset()) : that.getChipset() != null)
			return false;
		if (getFormFactor() != null ? !getFormFactor().equals(that.getFormFactor()) : that.getFormFactor() != null)
			return false;
		if (getM2slots() != null ? !getM2slots().equals(that.getM2slots()) : that.getM2slots() != null)
			return false;
		if (getCompatibleMem() != null ? !getCompatibleMem().equals(that.getCompatibleMem()) : that.getCompatibleMem() != null)
			return false;
		if (getMemType() != null ? !getMemType().equals(that.getMemType()) : that.getMemType() != null)
			return false;
		if (getIncEthernetSupp() != null ? !getIncEthernetSupp().equals(that.getIncEthernetSupp()) : that.getIncEthernetSupp() != null)
			return false;
		if (getIncVideo() != null ? !getIncVideo().equals(that.getIncVideo()) : that.getIncVideo() != null)
			return false;
		if (!Objects.equals(PCISlotList, that.PCISlotList))
			return false;
		return getGen32USBcount() != null ? getGen32USBcount().equals(that.getGen32USBcount()) : that.getGen32USBcount() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (isEcc() ? 1 : 0);
		result = 31 * result + (getChipset() != null ? getChipset().hashCode() : 0);
		result = 31 * result + (getFormFactor() != null ? getFormFactor().hashCode() : 0);
		result = 31 * result + (getM2slots() != null ? getM2slots().hashCode() : 0);
		result = 31 * result + getMaxMemSupport();
		result = 31 * result + getMemSlots();
		result = 31 * result + (getCompatibleMem() != null ? getCompatibleMem().hashCode() : 0);
		result = 31 * result + (getMemType() != null ? getMemType().hashCode() : 0);
		result = 31 * result + getmSATA_slotCount();
		result = 31 * result + (getIncEthernetSupp() != null ? getIncEthernetSupp().hashCode() : 0);
		result = 31 * result + (getIncVideo() != null ? getIncVideo().hashCode() : 0);
		result = 31 * result + (PCISlotList != null ? PCISlotList.hashCode() : 0);
		result = 31 * result + (isHasRAID() ? 1 : 0);
		result = 31 * result + getSata6gbpsCount();
		result = 31 * result + getGen2USBCount();
		result = 31 * result + (getGen32USBcount() != null ? getGen32USBcount().hashCode() : 0);
		result = 31 * result + (isWireless() ? 1 : 0);
		return result;
	}

	@Override
	public void writeToParcel(@NotNull Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeBoolean(ecc);
		dest.writeString(chipset);
		dest.writeString(formFactor);
		dest.writeStringList(m2slots);
		dest.writeInt(maxMemSupport);
		dest.writeInt(memSlots);
		dest.writeStringList(compatibleMem);
		dest.writeString(memType);
		dest.writeInt(mSATA_slotCount);
		dest.writeStringList(incEthernetSupp);
		dest.writeString(incVideo);
		dest.writeParcelableList(PCISlotList, flags);
		dest.writeBoolean(hasRAID);
		dest.writeInt(sata6gbpsCount);
		dest.writeInt(gen2USBCount);
		dest.writeParcelableList(gen32USBcount, flags);
		dest.writeBoolean(isWireless);
	}

	@RequiresApi(Q)
	public Motherboard(@NotNull Parcel in) {
		super(in.readString(), in.readString());
		ecc = in.readBoolean();
		chipset = in.readString();
		formFactor = in.readString();
		m2slots=in.createStringArrayList();
		maxMemSupport = in.readInt();
		memSlots = in.readInt();
		compatibleMem=in.readStringList(compatibleMem);
		memType = in.readString();
		mSATA_slotCount = in.readInt();
		in.readStringList(incEthernetSupp);
		incVideo = in.readString();
		in.readParcelableList(PCISlotList, PCISlotList.getClass().getClassLoader());
		hasRAID = in.readBoolean();
		sata6gbpsCount = in.readInt();
		gen2USBCount = in.readInt();
		in.readParcelableList(gen32USBcount, gen32USBcount.getClass().getClassLoader());
		isWireless = in.readBoolean();
	}

	@NonNull
	@NotNull
	@Override
	public String toString() {
		return "Motherboard " + getModel() + " " + getManufacturer();
	}
}
