package com.example.buildmypc.ui.build;

import com.example.buildmypc.ui.CPU;
import com.example.buildmypc.ui.Cooler;
import com.example.buildmypc.ui.GPU;
import com.example.buildmypc.ui.Memory;
import com.example.buildmypc.ui.Monitor;
import com.example.buildmypc.ui.Motherboard;
import com.example.buildmypc.ui.OS;
import com.example.buildmypc.ui.PSU;
import com.example.buildmypc.ui.Part;
import com.example.buildmypc.ui.StorageComp;

import java.util.ArrayList;

public class Prebuilds {
	private ArrayList<PCBuild> builds;

	public ArrayList<PCBuild> getPrebuiltList() {
		return builds;
	}

	public Prebuilds(){
		builds.add(new PCBuild(
				new Cooler("Hyper 212 EVO", "Cooler Master"),
				new CPU("Ryzen 5 3600", "AMD", false),
				new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA", false),
				new Memory("Vengeance LPX 16 GB", "Corsair", null),
				new Monitor("ROG Swift PG65UQ", "Asus"),
				new Motherboard("B450 TOMAHAWK MAX", "MSI", false),
				new OS("Windows 10 Pro", "Microsoft"),
				new PSU("RM750 (2019)", "Corsair", null),
				new StorageComp("Barracuda Compute 2 TB", "Seagate", null),
				new ArrayList<Part>()
		));
		builds.add(new PCBuild(
				new Cooler("Hyper 212 EVO", "Cooler Master"),
				new CPU("Ryzen 5 3600", "AMD", false),
				new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA", false),
				new Memory("Vengeance LPX 16 GB", "Corsair", null),
				new Monitor("ROG Swift PG65UQ", "Asus"),
				new Motherboard("B450 TOMAHAWK MAX", "MSI", false),
				new OS("Windows 10 Pro", "Microsoft"),
				new PSU("RM750 (2019)", "Corsair", null),
				new StorageComp("Barracuda Compute 2 TB", "Seagate", null),
				new ArrayList<Part>()
		));
		builds.add(new PCBuild(
				new Cooler("Hyper 212 EVO", "Cooler Master"),
				new CPU("Ryzen 5 3600", "AMD", false),
				new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA", false),
				new Memory("Vengeance LPX 16 GB", "Corsair", null),
				new Monitor("ROG Swift PG65UQ", "Asus"),
				new Motherboard("B450 TOMAHAWK MAX", "MSI", false),
				new OS("Windows 10 Pro", "Microsoft"),
				new PSU("RM750 (2019)", "Corsair", null),
				new StorageComp("Barracuda Compute 2 TB", "Seagate", null),
				new ArrayList<Part>()
		));
		builds.add(new PCBuild(
				new Cooler("Hyper 212 EVO", "Cooler Master"),
				new CPU("Ryzen 5 3600", "AMD", false),
				new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA", false),
				new Memory("Vengeance LPX 16 GB", "Corsair", null),
				new Monitor("ROG Swift PG65UQ", "Asus"),
				new Motherboard("B450 TOMAHAWK MAX", "MSI", false),
				new OS("Windows 10 Pro", "Microsoft"),
				new PSU("RM750 (2019)", "Corsair", null),
				new StorageComp("Barracuda Compute 2 TB", "Seagate", null),
				new ArrayList<Part>()
		));
		builds.add(new PCBuild(
				new Cooler("Hyper 212 EVO", "Cooler Master"),
				new CPU("Ryzen 5 3600", "AMD", false),
				new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA", false),
				new Memory("Vengeance LPX 16 GB", "Corsair", null),
				new Monitor("ROG Swift PG65UQ", "Asus"),
				new Motherboard("B450 TOMAHAWK MAX", "MSI", false),
				new OS("Windows 10 Pro", "Microsoft"),
				new PSU("RM750 (2019)", "Corsair", null),
				new StorageComp("Barracuda Compute 2 TB", "Seagate", null),
				new ArrayList<Part>()
		));
		builds.add(new PCBuild(
				new Cooler("Hyper 212 EVO", "Cooler Master"),
				new CPU("Ryzen 5 3600", "AMD", false),
				new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA", false),
				new Memory("Vengeance LPX 16 GB", "Corsair", null),
				new Monitor("ROG Swift PG65UQ", "Asus"),
				new Motherboard("B450 TOMAHAWK MAX", "MSI", false),
				new OS("Windows 10 Pro", "Microsoft"),
				new PSU("RM750 (2019)", "Corsair", null),
				new StorageComp("Barracuda Compute 2 TB", "Seagate", null),
				new ArrayList<Part>()
		));


	}
}
