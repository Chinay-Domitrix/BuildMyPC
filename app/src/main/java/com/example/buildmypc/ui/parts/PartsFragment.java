package com.example.buildmypc.ui.parts;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.buildmypc.databinding.FragmentPartsBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static com.example.buildmypc.MainActivity.database;
import static com.example.buildmypc.databinding.FragmentPartsBinding.inflate;

public class PartsFragment extends Fragment {
	private FragmentPartsBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		PartsViewModel partsViewModel = new ViewModelProvider(this).get(PartsViewModel.class);
		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
		final TextView textView = binding.textGallery;
		ConnectivityManager cm = ((ConnectivityManager) root.getContext().getSystemService(CONNECTIVITY_SERVICE));
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (activeNetwork != null && activeNetwork.isConnected() && !cm.isActiveNetworkMetered()) {
			FirebaseDatabase firebaseDatabase = database.get();
			DatabaseReference cpu = firebaseDatabase.getReference("cpu"),
					cooler = firebaseDatabase.getReference("cooler"),
					motherboard = firebaseDatabase.getReference("motherboard"),
					memory = firebaseDatabase.getReference("memory"),
					storage = firebaseDatabase.getReference("storage"),
					gpu = firebaseDatabase.getReference("gpu"),
					pcCase = firebaseDatabase.getReference("case"),
					psu = firebaseDatabase.getReference("psu"),
					os = firebaseDatabase.getReference("os"),
					monitor = firebaseDatabase.getReference("monitor");
			new Thread() {

			}.start();
		} else {

		}

		partsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}