package com.example.buildmypc.ui.parts;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.toolbox.StringRequest;
import com.example.buildmypc.PartsJSONParse;
import com.example.buildmypc.databinding.FragmentPartsBinding;

import org.json.JSONException;
import org.json.JSONObject;

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
		ConnectivityManager cm = ((ConnectivityManager) root.getContext().getSystemService(CONNECTIVITY_SERVICE));
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if ((activeNetwork != null) && activeNetwork.isConnected() && !cm.isActiveNetworkMetered())
			newRequestQueue(root.getContext()).add(new StringRequest(GET,
					"https://firebasestorage.googleapis.com/v0/b/buildmypc-ac8c3.appspot.com/o/part_data.json?alt=media&token="+getString(firebase_key),
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
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}