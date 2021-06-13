package com.example.buildmypc.ui.parts;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.buildmypc.R;
import com.example.buildmypc.databinding.FragmentPartsBinding;
import com.example.buildmypc.ui.build.PCBuild;
import com.example.buildmypc.ui.parts.parts.CPU;
import com.example.buildmypc.ui.parts.parts.Part;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.view.LayoutInflater.from;
import static com.example.buildmypc.MainActivity.coolers;
import static com.example.buildmypc.MainActivity.cpus;
import static com.example.buildmypc.MainActivity.gpus;
import static com.example.buildmypc.MainActivity.memory;
import static com.example.buildmypc.MainActivity.monitors;
import static com.example.buildmypc.MainActivity.motherboards;
import static com.example.buildmypc.MainActivity.oss;
import static com.example.buildmypc.MainActivity.pcCases;
import static com.example.buildmypc.MainActivity.psus;
import static com.example.buildmypc.MainActivity.storage;
import static com.example.buildmypc.R.id.partsList_addButton;
import static com.example.buildmypc.R.id.partsList_nameTextView;
import static com.example.buildmypc.databinding.FragmentPartsBinding.inflate;

public class PartsFragment extends Fragment {
	private FragmentPartsBinding binding;

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
		Log.d("PARTSFRAG", coolers.get().toString());

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
			return new RecyclerViewHolder(from(getContext()).inflate(R.layout.parts_list, parent, false));
		}

		@Override
		public void onBindViewHolder(@NonNull @NotNull RecyclerViewHolder holder, int position) {
			// the method where info is set for EACH individual layout element for each list entry
			Part currentPart = internalList.get(position);

			// since only the basic data is displayed, we'll do that first
			holder.nameTextView.setText(currentPart.toString());
			holder.internalButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// this is where the pop-up window'll be
					// debug statement to test out getSimpleName
					Log.d("PARTSFRAG", currentPart.getClass().getSimpleName());
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

			private AlertDialog dialog;
			private AlertDialog.Builder dialogBuilder;
			private TextView nameView;


			public RecyclerViewHolder(@NonNull View itemView) {
				super(itemView);
				// this is where the findViewById stuff goes for each element, and only the findViewByID
				nameTextView = itemView.findViewById(partsList_nameTextView);
				internalButton = itemView.findViewById(partsList_addButton);
			}

			public void createNewPopupWindow(Part part, RecyclerViewHolder holder){
				dialogBuilder = new AlertDialog.Builder(getContext());
				final View infoPopUp = getLayoutInflater().inflate(R.layout.popup, null);
				/* Automatically generate all the textviews needed
				 *
				 */

				ArrayList<TextView> tvList = new ArrayList<>();
				ConstraintLayout baseLayout = infoPopUp.findViewById(R.id.popup_constraintLayout);
				switch(part.getClass().getSimpleName()){
					case "CPU":
						for(int i = 0; i < part.getParamCount(); i++){

						}
						break;

					case "Cooler":
						break;

					case "Motherboard":
						break;

					case "Memory":
						break;

					case "Storage":
						break;

					case "GPU":
						break;

					case "Case":
						break;

					case "PSU":
						break;

					case "OS":
						break;

					case "Monitor":
						break;
				}
			}
		}
	}
}