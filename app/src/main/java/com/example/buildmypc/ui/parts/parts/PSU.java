package com.example.buildmypc.ui.parts.parts;

import java.util.ArrayList;

public class PSU extends Part { // stands for "Portable Communication Unit"

	private ArrayList<Connector> connectorList;
	private String efficiencyRating;
	private boolean hasFan;
	private String formFactor;
	private int length; // in millimeters
	private String modular;
	private int wattage;

	public PSU(String model, String manufacturer) {
		super(model, manufacturer);
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

	public class Connector {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		PSU psu = (PSU) o;

		if (isHasFan() != psu.isHasFan()) return false;
		if (getLength() != psu.getLength()) return false;
		if (getWattage() != psu.getWattage()) return false;
		if (getConnectorList() != null ? !getConnectorList().equals(psu.getConnectorList()) : psu.getConnectorList() != null)
			return false;
		if (getEfficiencyRating() != null ? !getEfficiencyRating().equals(psu.getEfficiencyRating()) : psu.getEfficiencyRating() != null)
			return false;
		if (getFormFactor() != null ? !getFormFactor().equals(psu.getFormFactor()) : psu.getFormFactor() != null)
			return false;
		return getModular() != null ? getModular().equals(psu.getModular()) : psu.getModular() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getConnectorList() != null ? getConnectorList().hashCode() : 0);
		result = 31 * result + (getEfficiencyRating() != null ? getEfficiencyRating().hashCode() : 0);
		result = 31 * result + (isHasFan() ? 1 : 0);
		result = 31 * result + (getFormFactor() != null ? getFormFactor().hashCode() : 0);
		result = 31 * result + getLength();
		result = 31 * result + (getModular() != null ? getModular().hashCode() : 0);
		result = 31 * result + getWattage();
		return result;
	}
}
