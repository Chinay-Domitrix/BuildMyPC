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
import androidx.recyclerview.widget.RecyclerView;

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
import static com.android.volley.Request.Method.GET;
import static com.android.volley.toolbox.Volley.newRequestQueue;
import static com.example.buildmypc.MainActivity.parts;
import static com.example.buildmypc.R.string.firebase_key;
import static com.example.buildmypc.R.string.parts_list;
import static com.example.buildmypc.databinding.FragmentPartsBinding.inflate;

public class PartsFragment extends Fragment {
	private FragmentPartsBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
		if (parts.get().equals(new JSONObject())){
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

		RecyclerView cpuRecyclerView = binding.partsCpuRecyclerview;
//		PartsRecyclerViewAdapter cpuAdapter = new PartsRecyclerViewAdapter();
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

	public class PartsRecyclerViewAdapter extends RecyclerView.Adapter{
		Context parentContext;
		ArrayList<Part> internalList;

		public PartsRecyclerViewAdapter(Context c, ArrayList<Part> list){
			parentContext = c;
			internalList = list;
		}

		@NonNull
		@NotNull
		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
			// where the XML is inflated
			View view = LayoutInflater.from(parentContext).inflate(R.layout.parts_list, parent, false);
			RecyclerViewHolder holder = new RecyclerViewHolder(view);
			return holder;
		}

		@Override
		public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
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
				nameTextView = itemView.findViewById(R.id.partsList_nameTextView);
				internalButton = itemView.findViewById(R.id.partsList_addButton);
			}
		}
	}
}