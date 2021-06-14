package com.example.buildmypc.ui.parts;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.buildmypc.databinding.FragmentPartsBinding;
import com.example.buildmypc.ui.parts.parts.*;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.util.Log.d;
import static android.view.LayoutInflater.from;
import static android.view.View.GONE;
import static android.view.View.OnClickListener;
import static com.example.buildmypc.MainActivity.*;
import static com.example.buildmypc.R.id.*;
import static com.example.buildmypc.R.layout.parts_list;
import static com.example.buildmypc.R.layout.popup;
import static com.example.buildmypc.databinding.FragmentPartsBinding.inflate;

public class PartsFragment extends Fragment {
	private FragmentPartsBinding binding;

	// popup window declarations
	private AlertDialog dialog;
	private Builder dialogBuilder;
//	private TextView nameView;


	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
//		new PartsJSONParse().start();

		// RecyclerView time
		// --> using the same XML for each

		// CPU, Cooler, Motherboard Memory, Storage, GPU, Case, PSU, OS, Monitor, extraParts

		RecyclerView cpuRecyclerView = binding.partsCpuRecyclerview;
		cpuRecyclerView.setAdapter(new PartsRecyclerViewAdapter(getContext(), cpus.get()));
		cpuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		RecyclerView coolerRecycle = binding.partsCoolerRecyclerview;
		coolerRecycle.setAdapter(new PartsRecyclerViewAdapter(getContext(), coolers.get()));
		coolerRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
		d("PARTSFRAG", coolers.get().toString());

		RecyclerView mbRecyclerView = binding.partsMotherboardRecyclerview;
		mbRecyclerView.setAdapter(new PartsRecyclerViewAdapter(getContext(), motherboards.get()));
		mbRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		RecyclerView storageRecyclerView = binding.partsStorageRecyclerview;
		storageRecyclerView.setAdapter(new PartsRecyclerViewAdapter(getContext(), storage.get()));
		storageRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		RecyclerView gpuRecyclerView = binding.partsGpuRecyclerview;
		gpuRecyclerView.setAdapter(new PartsRecyclerViewAdapter(getContext(), gpus.get()));
		gpuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		RecyclerView caseRecyclerView = binding.partsCaseRecyclerview;
		caseRecyclerView.setAdapter(new PartsRecyclerViewAdapter(getContext(), pcCases.get()));
		caseRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		RecyclerView psuRecyclerView = binding.partsPsuRecyclerview; // power supplies
		psuRecyclerView.setAdapter(new PartsRecyclerViewAdapter(getContext(), psus.get()));
		psuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		RecyclerView osRecyclerView = binding.partsOsRecyclerview;
		osRecyclerView.setAdapter(new PartsRecyclerViewAdapter(getContext(), oss.get()));
		osRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		RecyclerView monitorRecyclerView = binding.partsMonitorRecyclerview;
		monitorRecyclerView.setAdapter(new PartsRecyclerViewAdapter(getContext(), monitors.get()));
		monitorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		RecyclerView memoryRecyclerView = binding.partsMemoryRecyclerview;
		memoryRecyclerView.setAdapter(new PartsRecyclerViewAdapter(getContext(), memory.get()));
		memoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		// in case we ever want accessories
//		RecyclerView accessoriesRecyclerView = binding.partsAccessoryRecyclerview; // power supplies
//		accessoriesRecyclerView.setAdapter(new PartsRecyclerViewAdapter(getContext(), MainActivity.accessories.get()));
//		accessoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

//	public ArrayList<Part> sortList(ArrayList<Part> partList, Class type) throws Exception {
//		ArrayList<Part> newList = new ArrayList<Part>();
//
//		Class[] validTypes = {Accessory.class, Case.class, Cooler.class, CPU.class, GPU.class, Memory.class,
//				Monitor.class, Motherboard.class, OS.class, PSU.class, Storage.class};
//
//		// checking if its valid
//		boolean isValid = false;
//		for(Class c : validTypes){
//			if(type.equals(c)) isValid = true;
//		}
//		if(!isValid) throw new Exception("partsList was not given one of the valid types");
//
//		// the sorting
//		for(Part p : partList){
//			if(p.getClass().equals(type)) newList.add(p);
//		}
//		if(newList != null) return newList ;
//		return null;
//
//	}

	public class PartsRecyclerViewAdapter extends Adapter<PartsRecyclerViewAdapter.RecyclerViewHolder> {
		Context parentContext;
		ArrayList<Part> internalList;

		public PartsRecyclerViewAdapter(Context c, ArrayList<Part> list) {
			parentContext = c;
			internalList = list;
		}

		@NonNull
		@NotNull
		@Override
		public RecyclerViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
			// where the XML is inflated
			return new RecyclerViewHolder(from(getContext()).inflate(parts_list, parent, false));
		}

		@Override
		public void onBindViewHolder(@NonNull @NotNull RecyclerViewHolder holder, int position) {
			// the method where info is set for EACH individual layout element for each list entry
			Part currentPart = internalList.get(position);

			// since only the basic data is displayed, we'll do that first
			holder.nameTextView.setText(currentPart.toString());
			holder.internalButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// this is where the pop-up window'll be
					// debug statement to test out getSimpleName
					d("PARTSFRAG", currentPart.getClass().getSimpleName());
					createNewPopupWindow(currentPart);
				}
			});
			// TODO write this and write the clickable code to make a popup window (specifics of the display determined by a switch(getCLass().getName()))
		}

		@Override
		public int getItemCount() {
			return internalList.size();
		}

		public class RecyclerViewHolder extends RecyclerView.ViewHolder {
			TextView nameTextView;
			Button internalButton;

			public RecyclerViewHolder(@NonNull View itemView) {
				super(itemView);
				// this is where the findViewById stuff goes for each element, and only the findViewByID
				nameTextView = itemView.findViewById(partsList_nameTextView);
				internalButton = itemView.findViewById(partsList_addButton);
			}
		}

		public void createNewPopupWindow(Part part) {
			dialogBuilder = new Builder(getContext());
			final View infoPopUp = getLayoutInflater().inflate(popup, null);
			TextView[][] tvs = new TextView[20][2];
			// I don't know how to automatically do this so it's manual time
			tvs[0][0] = infoPopUp.findViewById(popup_title1);
			tvs[0][1] = infoPopUp.findViewById(popup_message1);

			tvs[1][0] = infoPopUp.findViewById(popup_title2);
			tvs[1][1] = infoPopUp.findViewById(popup_message2);

			tvs[2][0] = infoPopUp.findViewById(popup_title3);
			tvs[2][1] = infoPopUp.findViewById(popup_message3);

			tvs[3][0] = infoPopUp.findViewById(popup_title4);
			tvs[3][1] = infoPopUp.findViewById(popup_message4);

			tvs[4][0] = infoPopUp.findViewById(popup_title5);
			tvs[4][1] = infoPopUp.findViewById(popup_message5);

			tvs[5][0] = infoPopUp.findViewById(popup_title6);
			tvs[5][1] = infoPopUp.findViewById(popup_message6);

			tvs[6][0] = infoPopUp.findViewById(popup_title7);
			tvs[6][1] = infoPopUp.findViewById(popup_message7);

			tvs[7][0] = infoPopUp.findViewById(popup_title8);
			tvs[7][1] = infoPopUp.findViewById(popup_message8);

			tvs[8][0] = infoPopUp.findViewById(popup_title9);
			tvs[8][1] = infoPopUp.findViewById(popup_message9);

			tvs[9][0] = infoPopUp.findViewById(popup_title10);
			tvs[9][1] = infoPopUp.findViewById(popup_message10);

			tvs[10][0] = infoPopUp.findViewById(popup_title11);
			tvs[10][1] = infoPopUp.findViewById(popup_message11);

			tvs[11][0] = infoPopUp.findViewById(popup_title12);
			tvs[11][1] = infoPopUp.findViewById(popup_message12);

			tvs[12][0] = infoPopUp.findViewById(popup_title13);
			tvs[12][1] = infoPopUp.findViewById(popup_message13);

			tvs[13][0] = infoPopUp.findViewById(popup_title14);
			tvs[13][1] = infoPopUp.findViewById(popup_message14);

			tvs[14][0] = infoPopUp.findViewById(popup_title15);
			tvs[14][1] = infoPopUp.findViewById(popup_message15);

			tvs[15][0] = infoPopUp.findViewById(popup_title16);
			tvs[15][1] = infoPopUp.findViewById(popup_message16);

			tvs[16][0] = infoPopUp.findViewById(popup_title17);
			tvs[16][1] = infoPopUp.findViewById(popup_message17);

			tvs[17][0] = infoPopUp.findViewById(popup_title18);
			tvs[17][1] = infoPopUp.findViewById(popup_message18);

			tvs[18][0] = infoPopUp.findViewById(popup_title19);
			tvs[18][1] = infoPopUp.findViewById(popup_message19);

			tvs[19][0] = infoPopUp.findViewById(popup_title20);
			tvs[19][1] = infoPopUp.findViewById(popup_message20);

			/* Automatically generate all the textviews needed
			 *
			 */

			ArrayList<LinearLayout> linearLayouts = new ArrayList<>();
			ConstraintLayout baseLayout = infoPopUp.findViewById(popup_constraintLayout);
			switch (part.getClass().getSimpleName()) {
				case "CPU": // jank setup of autogenerating textviews failed, so I just hardcoded it like a junior dev should
//					linearLayouts.add(infoPopUp.findViewById(R.id.popup_layout1));
//					Log.d("PARTSFRAG", String.valueOf(part.getParamCount()));
//						for(int i = 1; i < part.getParamCount(); i++){
//							// generating the linear layout TODO fix this
//							LinearLayout currentLinearLayout = new LinearLayout(getContext());
//							currentLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
//							currentLinearLayout.setPadding(10, 0, 10, 0);
//							ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//							ConstraintSet set = new ConstraintSet();
//							Log.d("PARTSFRAG", String.valueOf(linearLayouts.size()));
//							if(i > 0) {
//								set.connect(linearLayouts.get(i-1).getId(), ConstraintSet.BOTTOM, currentLinearLayout.getId(), ConstraintSet.TOP);
//								set.connect(linearLayouts.get(i-1).getId(), ConstraintSet.LEFT, currentLinearLayout.getId(), ConstraintSet.LEFT);
//								set.connect(linearLayouts.get(i-1).getId(), ConstraintSet.RIGHT, currentLinearLayout.getId(), ConstraintSet.RIGHT);
//							}
//							else { set.clone(); }
//							set.constrainMinHeight(currentLinearLayout.getId(), 15); // assuming height is in density pixels
//							set.applyTo(baseLayout);
//
//							// inserting the two text views
//							TextView tv1 = new TextView(getContext());
//							tv1.setId(2 * i); // adding constant noise as to not interfere with previous IDs
//							tv1.setLayoutParams(new LinearLayout.LayoutParams(30, LinearLayout.LayoutParams.WRAP_CONTENT));
//							currentLinearLayout.addView(tv1);
//
//							TextView tv2 = new TextView(getContext());
//							tv1.setId(2*i + 1); // +1 added to the previous ID
//							tv2.setLayoutParams(new LinearLayout.LayoutParams(90, LinearLayout.LayoutParams.WRAP_CONTENT));
//							currentLinearLayout.addView(tv2);
//
//							// adding the LinearLayout to the bunch
//							linearLayouts.add(currentLinearLayout);
//						}
					TextView name = infoPopUp.findViewById(popup_superTitleTextView); // the name text view in a series
					name.setText(part.getName());

					tvs[0][0].setText("Manufacturer:");
					tvs[0][1].setText(part.getManufacturer());

					tvs[1][0].setText("# of Cores:");
					tvs[1][1].setText(String.valueOf(((CPU) part).getCoreCount()));

					tvs[2][0].setText("Clock Speed:");
					tvs[2][1].setText(((CPU) part).getCoreClock() + " GHz");

					tvs[3][0].setText("Boost Clock Speed:");
					tvs[3][1].setText(((CPU) part).getBoostClock() + " GHz");

					tvs[4][0].setText("Thermal Design Power:");
					tvs[4][1].setText(((CPU) part).getTdp() + " W");

					tvs[5][0].setText("Microarchitecture:");
					tvs[5][1].setText(((CPU) part).getMicroarchitecture());

					tvs[6][0].setText("Core Family:");
					tvs[6][1].setText(((CPU) part).getCoreFamily());

					tvs[7][0].setText("Socket Type");
					tvs[7][1].setText(((CPU) part).getSocket());

					tvs[8][0].setText("Has Integrated GPU?: ");
					tvs[8][1].setText(((CPU) part).isiGPU() ? "Yes" : "No");

					tvs[9][0].setText("Max Supported Memory: ");
					tvs[9][1].setText(((CPU) part).getMaxMemory() + " GB");

					tvs[10][0].setText("Can error-correct?: ");
					tvs[10][1].setText(((CPU) part).isEcc() ? "Yes" : "No");

					tvs[11][0].setText("Has an internal cooler?: ");
					tvs[11][1].setText(((CPU) part).isCooler() ? "Yes" : "No");

					tvs[12][0].setText("Has s.\"multithreading\"?: ");
					tvs[12][1].setText(((CPU) part).isSmt() ? "Yes" : "No");

					for (int i = 13; i < tvs.length; i++) {
						tvs[i][0].setVisibility(GONE);
						tvs[i][1].setVisibility(GONE);
					}

					break;

				case "Cooler":
					name = infoPopUp.findViewById(popup_superTitleTextView); // the name text view in a series
					name.setText(part.getName());

					tvs[0][0].setText("Manufacturer:");
					tvs[0][1].setText(part.getManufacturer());

					tvs[1][0].setText("Watercooled or Fan?: ");
					tvs[1][1].setText(((Cooler) part).isWaterCooled() ? "Watercooled" : "Fan");

					tvs[2][0].setText("Includes Fan: ");
					tvs[2][1].setText(((Cooler) part).isFanless() ? "Yes" : "No");

					tvs[3][0].setText("Height: ");
					tvs[3][1].setText(((Cooler) part).getHeight() + " mm");

					tvs[4][0].setText("Noise Level: ");
					tvs[4][1].setText(((Cooler) part).getNoiseLevel() + " dB");

					tvs[5][0].setText("Supported Sockets: \n");
					// build the string of all of the supported sockets
					StringBuilder str = new StringBuilder();
					for (String s : ((Cooler) part).getSocketSupport()) str.append(s).append("\n");
					str.substring(str.length() - 2);
					tvs[5][1].setText(str.toString());

					if (!((Cooler) part).isFanless()) {
						tvs[6][0].setText("Fan RPM:");
						tvs[6][1].setText(((Cooler) part).getRpm() + " rpm");
					} else {
						tvs[6][0].setVisibility(GONE);
						tvs[6][1].setVisibility(GONE);
					}

					for (int i = 7; i < tvs.length; i++) {
						tvs[i][0].setVisibility(GONE);
						tvs[i][1].setVisibility(GONE);
					}

					break;

				case "Motherboard":
					name = infoPopUp.findViewById(popup_superTitleTextView); // the name text view in a series
					name.setText(part.getName());

					tvs[0][0].setText("Manufacturer:");
					tvs[0][1].setText(part.getManufacturer());

					tvs[1][0].setText("Can error-correct?: ");
					tvs[1][1].setText(((Motherboard) part).isEcc() ? "Yes" : "No");

					tvs[2][0].setText("Chipset");
					tvs[2][1].setText(((Motherboard) part).getChipset());

					tvs[3][0].setText("Form Factor:");
					tvs[3][1].setText(((Motherboard) part).getFormFactor());

					// TODO format this properly
					tvs[4][0].setText("M.2 slots:"); // m2slots
					str = new StringBuilder();
					for (String s : ((Motherboard) part).getM2slots()) str.append(s).append("\n");
					str.substring(str.length() - 2);
					tvs[4][1].setText(str.toString());

					tvs[5][0].setText("Max Supported Memory:");
					tvs[5][1].setText(((Motherboard) part).getMaxMemSupport() + " GB");

					tvs[6][0].setText("# of Memory Slots: ");
					tvs[6][1].setText(String.valueOf(((Motherboard) part).getMemSlots()));

					tvs[7][0].setText("Compatible Memory Types: ");
					str = new StringBuilder();
					for (String s : ((Motherboard) part).getCompatibleMem())
						str.append(s).append("\n");
					str.substring(str.length() - 2);
					tvs[7][1].setText(str.toString());

					tvs[8][0].setText("mSATA Slot Count:");
					tvs[8][1].setText(String.valueOf(((Motherboard) part).getmSATA_slotCount()));

					tvs[9][0].setText("Compatible Ethernet Types: ");
					str = new StringBuilder();
					for (String s : ((Motherboard) part).getIncEthernetSupp())
						str.append(s).append("\n");
					str.substring(str.length() - 2);
					tvs[9][1].setText(str.toString());

					tvs[10][0].setText("Included GPU: ");
					tvs[10][1].setText(((Motherboard) part).getIncVideo());

					tvs[11][0].setText("PCI Slot List: ");
					str = new StringBuilder();
					for (CountedString cstr : ((Motherboard) part).getPciSlotList())
						str.append(cstr.getName()).append(", ").append(cstr.getAmount()).append("\n");
					str.substring(str.length() - 2);
					tvs[11][1].setText(str.toString());

					tvs[12][0].setText("Has RAID?: ");
					tvs[12][1].setText(((Motherboard) part).isHasRAID() ? "Yes" : "No");

					tvs[13][0].setText("SATA 6GB/s Count: ");
					tvs[13][1].setText(String.valueOf(((Motherboard) part).getSata6gbpsCount()));

					tvs[14][0].setText("Gen 2 USB Count: ");
					tvs[14][1].setText(String.valueOf(((Motherboard) part).getGen2USBCount()));

					tvs[15][0].setText("Gen 3.2 USB Count: ");
					str = new StringBuilder();
					for (CountedString cstr : ((Motherboard) part).getGen32USBcount())
						str.append(cstr.getName()).append(", ").append(cstr.getAmount()).append("\n");
					str.substring(str.length() - 2);
					tvs[15][1].setText(str.toString());

					tvs[16][0].setText("Wireless Type: ");
					tvs[16][1].setText(((Motherboard) part).getWireless());

					for (int i = 17; i < tvs.length; i++) {
						tvs[i][0].setVisibility(GONE);
						tvs[i][1].setVisibility(GONE);
					}

					break;

				case "Memory":
					// ((Memory) part).

					name = infoPopUp.findViewById(popup_superTitleTextView); // the name text view in a series
					name.setText(part.getName());

					tvs[0][0].setText("Manufacturer:");
					tvs[0][1].setText(part.getManufacturer());

					tvs[1][0].setText("Can error-correct?:");
					tvs[1][1].setText(((Memory) part).getHasECC() ? "Yes" : "No");

					tvs[2][0].setText("CAS Latency:");
					tvs[2][1].setText(((Memory) part).getLatencyCAS() + " cycles");

					tvs[3][0].setText("DDR Generation:");
					tvs[3][1].setText(String.valueOf(((Memory) part).getDdrGen()));

					tvs[4][0].setText("First Word Latency");
					tvs[4][1].setText(((Memory) part).getFirstWordLatency() + " ns");

					tvs[5][0].setText("Form Factor");
					tvs[5][1].setText(((Memory) part).getFormFactor());

					tvs[6][0].setText("Has a Heat Spreader?:");
					tvs[6][1].setText(((Memory) part).getHeatSpreader() ? "Yes" : "No");

					tvs[7][0].setText("Module Size:");
					tvs[7][1].setText(((Memory) part).getModuleSize() + " GB");

					tvs[8][0].setText("Module Count:");
					tvs[8][1].setText(String.valueOf(((Memory) part).getModuleCount()));

					tvs[9][0].setText("RAM Speed:");
					tvs[9][1].setText(((Memory) part).getModuleSize() + " MHz");

					tvs[10][0].setText("Timing:");
					tvs[10][1].setText(((Memory) part).getTiming());

					tvs[11][0].setText("Voltage:");
					tvs[11][1].setText(String.valueOf(((Memory) part).getVoltage()));
					if (tvs[11][1].getText().equals("null")) tvs[11][1].setText("N/A");

					for (int i = 12; i < tvs.length; i++) {
						tvs[i][0].setVisibility(GONE);
						tvs[i][1].setVisibility(GONE);
					}

					break;

				case "Storage":
					name = infoPopUp.findViewById(popup_superTitleTextView); // the name text view in a series
					name.setText(part.getName());

					tvs[0][0].setText("Manufacturer:");
					tvs[0][1].setText(part.getManufacturer());

					tvs[1][0].setText("Type:");
					tvs[1][1].setText(((Storage) part).getType());

					tvs[2][0].setText("Form Factor:");
					tvs[2][1].setText(((Storage) part).getFormFactor());

					tvs[3][0].setText("Cache Size:");
					tvs[3][1].setText(((Storage) part).getCacheSizeMB() + " MB");

					tvs[4][0].setText("Capacity:");
					tvs[4][1].setText(((Storage) part).getCapacity());

					tvs[5][0].setText("SATA Interface:");
					tvs[5][1].setText(((Storage) part).getSataInterface());

					tvs[6][0].setText("Has NVM Express?:");
					tvs[6][1].setText(((Storage) part).getNvme() ? "Yes" : "No");

					tvs[7][0].setText("RPM:");
					tvs[7][1].setText(((Storage) part).getRpm() + " rpm");
					if (tvs[7][1].getText().equals("null")) tvs[6][1].setText("N/A");

					tvs[8][0].setText("Capacity:");
					tvs[8][1].setText(((Storage) part).getCapacity());

					for (int i = 9; i < tvs.length; i++) {
						tvs[i][0].setVisibility(GONE);
						tvs[i][1].setVisibility(GONE);
					}

					break;

				case "GPU":
					name = infoPopUp.findViewById(popup_superTitleTextView); // the name text view in a series
					name.setText(part.getName());

					tvs[0][0].setText("Manufacturer:");
					tvs[0][1].setText(part.getManufacturer());
					break;

				case "Case":
					name = infoPopUp.findViewById(popup_superTitleTextView); // the name text view in a series
					name.setText(part.getName());

					tvs[0][0].setText("Manufacturer:");
					tvs[0][1].setText(part.getManufacturer());
					break;

				case "PSU":
					name = infoPopUp.findViewById(popup_superTitleTextView); // the name text view in a series
					name.setText(part.getName());

					tvs[0][0].setText("Manufacturer:");
					tvs[0][1].setText(part.getManufacturer());
					break;

				case "OS":
					name = infoPopUp.findViewById(popup_superTitleTextView); // the name text view in a series
					name.setText(part.getName());

					tvs[0][0].setText("Manufacturer:");
					tvs[0][1].setText(part.getManufacturer());
					break;

				case "Monitor":
					name = infoPopUp.findViewById(popup_superTitleTextView); // the name text view in a series
					name.setText(part.getName());

					tvs[0][0].setText("Manufacturer:");
					tvs[0][1].setText(part.getManufacturer());
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + part.getClass().getSimpleName());
			}

			dialogBuilder.setView(infoPopUp);
			dialog = dialogBuilder.create();
			dialog.show();

			// not needed
//			baseLayout.setOnLongClickListener(new View.OnLongClickListener() {
//				@Override
//				public boolean onLongClick(View v) {
//					// writing the cancel stuff
//					dialog.dismiss();
//					return true;
//				}
//			});
		}

		public int getIDFromFormula() {
			return (int) (Math.random() * (10 ^ 7));
		}
	}
}