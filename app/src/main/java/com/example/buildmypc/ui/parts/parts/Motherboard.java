package com.example.buildmypc.ui.parts.parts;

import java.util.ArrayList;

public class Motherboard extends InternalPart {

	private String chipset;
//	private boolean hasEcc; // has error correction coding (all chips should tbh)
	private String formFactor;
	private ArrayList<String> m2slots; // no clue what this is
	private int maxMemSupport;
	private int memSlots;
	private ArrayList<String> compatibleMem;
	private String memType; // pretty much always DDR4
	private int mSATA_slotCount; // love SSDs
	private ArrayList<String> incEthernetSupp;
	private String incVideo; // no idea what this is
	private ArrayList<pciSlot> pciSlotList; // had to create a subobject for this
	private boolean hasRAID; // has "Redundant Array of Inexpensive Disks"
	private int sata6gbpsCount; // no clue
	private int gen1USBCount;
	private int gen2USBcount; // I don't feel like making a new USB object to only be used once
	private boolean isWireless;

	public Motherboard(String model, String manufacturer, boolean hasEcc) {
		super(model, manufacturer, hasEcc);
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

	public ArrayList<pciSlot> getPciSlotList() {
		return pciSlotList;
	}

	public void setPciSlotList(ArrayList<pciSlot> pciSlotList) {
		this.pciSlotList = pciSlotList;
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

	public int getGen1USBCount() {
		return gen1USBCount;
	}

	public void setGen1USBCount(int gen1USBCount) {
		this.gen1USBCount = gen1USBCount;
	}

	public int getGen2USBcount() {
		return gen2USBcount;
	}

	public void setGen2USBcount(int gen2USBcount) {
		this.gen2USBcount = gen2USBcount;
	}

	public boolean isWireless() {
		return isWireless;
	}

	public void setWireless(boolean wireless) {
		isWireless = wireless;
	}

	public class pciSlot {
		private String name;
		private int count;

		public pciSlot(String name, int count){
			this.name = name;
			this.count = count;
		}

		public String getName() {
			return name;
		}

		public int getCount() {
			return count;
		}
	}
}
