package com.example.buildmypc.ui.parts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.buildmypc.databinding.FragmentGalleryBinding;

import static com.example.buildmypc.databinding.FragmentGalleryBinding.*;

public class PartsFragment extends Fragment {
	private FragmentGalleryBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		PartsViewModel partsViewModel = new ViewModelProvider(this).get(PartsViewModel.class);
		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
		final TextView textView = binding.textGallery;
		partsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}