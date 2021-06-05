package com.example.buildmypc.ui;

// the start of the part hierarchy
public class Part {

	 private String model;
	 private String manufacturer;

	 public Part(String model, String manufacturer){
	 	this.model = model;
	 	this.manufacturer = manufacturer;
	 }

	 public String getName(){
	 	return manufacturer + " " + model;
	 }

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
}
