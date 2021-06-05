package com.example.buildmypc.ui;

import java.util.ArrayList;

public class PSU extends InternalPart { // stands for "Portable Communication Unit"

	private ArrayList<Connector> connectorList;
	private String efficiencyRating;
	private boolean hasFan;
	private String formFactor;
	private int length; // in millimeters
	private String modular;
	private int wattage;

	public PSU(String model, String manufacturer, Boolean hasEcc) {
		super(model, manufacturer, hasEcc);
	}

	public ArrayList<Connector> getConnectorList() {
		return connectorList;
	}

	public void setConnectorList(ArrayList<Connector> connectorList) {
		this.connectorList = connectorList;
	}

	public String getEfficiencyRating() {
		return efficiencyRating;
	}

	public void setEfficiencyRating(String efficiencyRating) {
		this.efficiencyRating = efficiencyRating;
	}

	public boolean isHasFan() {
		return hasFan;
	}

	public void setHasFan(boolean hasFan) {
		this.hasFan = hasFan;
	}

	public String getFormFactor() {
		return formFactor;
	}

	public void setFormFactor(String formFactor) {
		this.formFactor = formFactor;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getModular() {
		return modular;
	}

	public void setModular(String modular) {
		this.modular = modular;
	}

	public int getWattage() {
		return wattage;
	}

	public void setWattage(int wattage) {
		this.wattage = wattage;
	}

	public class Connector{

		private String name;
		private int count;


		public Connector(String name, int count) {
			this.name = name;
			this.count = count;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
	}
}
