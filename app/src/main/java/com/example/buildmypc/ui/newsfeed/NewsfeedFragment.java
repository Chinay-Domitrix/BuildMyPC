package com.example.buildmypc.ui.newsfeed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.buildmypc.databinding.FragmentNewsfeedBinding;

import static com.example.buildmypc.databinding.FragmentNewsfeedBinding.*;

public class NewsfeedFragment extends Fragment {
	private FragmentNewsfeedBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		NewsfeedViewModel newsfeedViewModel = new ViewModelProvider(this).get(NewsfeedViewModel.class);
		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
		final TextView textView = binding.textSlideshow;
		newsfeedViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
		// TODO parsing newsfeeds
		// ending return
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}