package com.example.buildmypc.ui.currentBuild;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buildmypc.R;
import com.example.buildmypc.ui.build.PCBuild;

public class EditorFragment extends Fragment {

	private static final String BUILD = "pcbuild";

	private EditorViewModel mViewModel;
	private PCBuild currentBuild;

	public EditorFragment() {}

	public static EditorFragment newInstance(PCBuild build) {
		EditorFragment editorFragment = new EditorFragment();
		Bundle args = new Bundle();
//		args.putParcelable(build); TODO MAKE EVERYTHING PARCELABLE
		return new EditorFragment();
	}

	@Override
	public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.editor_fragment, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mViewModel = new ViewModelProvider(this).get(EditorViewModel.class);
		// TODO: Use the ViewModel
	}

}