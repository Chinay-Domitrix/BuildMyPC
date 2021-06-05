package com.example.buildmypc.ui;

public class InternalPart extends Part {

	private boolean hasEcc;

	public InternalPart(String model, String manufacturer, Boolean hasEcc) {
		super(model, manufacturer);
		this.hasEcc = hasEcc;
	}

	public boolean isHasEcc() {
		return hasEcc;
	}

	public void setHasEcc(boolean hasEcc) {
		this.hasEcc = hasEcc;
	}
}
