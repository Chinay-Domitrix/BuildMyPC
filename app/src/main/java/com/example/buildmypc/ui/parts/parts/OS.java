package com.example.buildmypc.ui.parts.parts;

public class OS extends Accessory {

	private String bitMode;
	private double maxMemSupport;
	private String type;
	private String edition;
	private String oem_retail;

	public OS(String model, String manufacturer) {
		super(model, manufacturer);
	}

	public String getBitMode() {
		return bitMode;
	}

	public void setBitMode(String bitMode) {
		this.bitMode = bitMode;
	}

	public double getMaxMemSupport() {
		return maxMemSupport;
	}

	public void setMaxMemSupport(double maxMemSupport) {
		this.maxMemSupport = maxMemSupport;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getOem_retail() {
		return oem_retail;
	}

	public void setOem_retail(String oem_retail) {
		this.oem_retail = oem_retail;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		OS os = (OS) o;

		if (Double.compare(os.getMaxMemSupport(), getMaxMemSupport()) != 0) return false;
		if (getBitMode() != null ? !getBitMode().equals(os.getBitMode()) : os.getBitMode() != null)
			return false;
		if (getType() != null ? !getType().equals(os.getType()) : os.getType() != null)
			return false;
		if (getEdition() != null ? !getEdition().equals(os.getEdition()) : os.getEdition() != null)
			return false;
		return getOem_retail() != null ? getOem_retail().equals(os.getOem_retail()) : os.getOem_retail() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		long temp;
		result = 31 * result + (getBitMode() != null ? getBitMode().hashCode() : 0);
		temp = Double.doubleToLongBits(getMaxMemSupport());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (getType() != null ? getType().hashCode() : 0);
		result = 31 * result + (getEdition() != null ? getEdition().hashCode() : 0);
		result = 31 * result + (getOem_retail() != null ? getOem_retail().hashCode() : 0);
		return result;
	}
}
