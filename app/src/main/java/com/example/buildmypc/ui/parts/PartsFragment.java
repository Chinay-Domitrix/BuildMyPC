package com.example.buildmypc.ui.parts;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.android.volley.toolbox.StringRequest;
import com.example.buildmypc.PartsJSONParse;
import com.example.buildmypc.R;
import com.example.buildmypc.databinding.FragmentPartsBinding;
import com.example.buildmypc.ui.parts.parts.Part;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.view.LayoutInflater.from;
import static com.android.volley.Request.Method.GET;
import static com.android.volley.toolbox.Volley.newRequestQueue;
import static com.example.buildmypc.MainActivity.coolers;
import static com.example.buildmypc.MainActivity.cpus;
import static com.example.buildmypc.MainActivity.gpus;
import static com.example.buildmypc.MainActivity.monitors;
import static com.example.buildmypc.MainActivity.motherboards;
import static com.example.buildmypc.MainActivity.oss;
import static com.example.buildmypc.MainActivity.parts;
import static com.example.buildmypc.MainActivity.pcCases;
import static com.example.buildmypc.MainActivity.psus;
import static com.example.buildmypc.MainActivity.storage;
import static com.example.buildmypc.R.id.partsList_addButton;
import static com.example.buildmypc.R.id.partsList_nameTextView;
import static com.example.buildmypc.R.string.firebase_key;
import static com.example.buildmypc.R.string.parts_list;
import static com.example.buildmypc.databinding.FragmentPartsBinding.inflate;

public class PartsFragment extends Fragment {
	private FragmentPartsBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
		if (parts.get().equals(new JSONObject())) {
			ConnectivityManager cm = ((ConnectivityManager) root.getContext().getSystemService(CONNECTIVITY_SERVICE));
			NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
			if ((activeNetwork != null) && activeNetwork.isConnected() && !cm.isActiveNetworkMetered())
				newRequestQueue(root.getContext()).add(new StringRequest(GET,
						"https://firebasestorage.googleapis.com/v0/b/buildmypc-ac8c3.appspot.com/o/part_data.json?alt=media&token=" + getString(firebase_key),
						response -> {
							try {
								parts.set(new JSONObject(response));
							} catch (JSONException e) {
								e.printStackTrace();
							}
						},
						Throwable::printStackTrace));
			else try {
				parts.set(new JSONObject(getString(parts_list)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			new PartsJSONParse().start();
		}
//		new PartsJSONParse().start();

		// RecyclerView time
		// --> using the same XML for each

		// CPU, Cooler, Motherboard Memory, Storage, GPU, Case, PSU, OS, Monitor, extraParts

		RecyclerView cpuRecyclerView = binding.partsCpuRecyclerview;
		cpuRecyclerView.setAdapter(new PartsRecyclerViewAdapter(getContext(), cpus.get()));
		cpuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		RecyclerView coolerRecycle = binding.partsCpuRecyclerview;
		coolerRecycle.setAdapter(new PartsRecyclerViewAdapter(getContext(), coolers.get()));
		coolerRecycle.setLayoutManager(new LinearLayoutManager(getContext()));

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

		RecyclerView osRecyclerView = binding.partsOsRecyclerview; // power supplies
		osRecyclerView.setAdapter(new PartsRecyclerViewAdapter(getContext(), oss.get()));
		osRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		RecyclerView monitorRecyclerView = binding.partsMonitorRecyclerview; // power supplies
		monitorRecyclerView.setAdapter(new PartsRecyclerViewAdapter(getContext(), monitors.get()));
		monitorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
	}
}