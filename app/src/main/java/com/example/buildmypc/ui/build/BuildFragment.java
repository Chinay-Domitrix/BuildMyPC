package com.example.buildmypc.ui.build;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.buildmypc.databinding.FragmentHomeBinding;

import static com.example.buildmypc.databinding.FragmentHomeBinding.*;

public class BuildFragment extends Fragment {
	private FragmentHomeBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		BuildViewModel buildViewModel = new ViewModelProvider(this).get(BuildViewModel.class);
		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
		final TextView textView = binding.textHome;
		buildViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}