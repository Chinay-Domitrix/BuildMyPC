package com.example.buildmypc.ui.currentBuild;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.buildmypc.R;
import com.example.buildmypc.ui.build.PCBuild;

public class EditorFragment extends Fragment {

	private static final String BUILD = "pcbuild";

	private EditorViewModel mViewModel;
	private PCBuild currentBuild;
	private OnFragmentInteractionListener mListener;

	private Button goBackButton;
	private TextView textView;


	public EditorFragment(PCBuild currentBuild) {
		this.currentBuild = currentBuild;
	}

	public EditorFragment() {}

	public static EditorFragment newInstance(PCBuild build) {
		EditorFragment editorFragment = new EditorFragment();
		Bundle args = new Bundle();
		args.putParcelable(BUILD, build);
		return new EditorFragment(build);
	}

	@Override
	public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		PCBuild build = getArguments().getParcelable(BUILD);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		goBackButton = container.findViewById(R.id.editorFragmentGoBackButton);
		textView = container.findViewById(R.id.editorFragment_textView);
		textView.setText(String.valueOf((int)(Math.random)));
		return inflater.inflate(R.layout.fragment_editor, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		mViewModel = new ViewModelProvider(this).get(EditorViewModel.class);
		// do stuff
		PCBuild finalResult = new PCBuild();
		/* this should all go in a button that only activates once all fields are filled */
		Bundle result = new Bundle();
		result.putParcelable("bundleKey", finalResult);
		getParentFragmentManager().setFragmentResult(BUILD, result);
		// TODO: Use the ViewModel
	}

//	public void sendBack(PCBuild updatedBuild){
//		if(mListener != null){
//			mListener.onFragmentInteraction(updatedBuild);
//		}
//	}
//
//	@Override
//	public void onAttach(Context context) {
//		super.onAttach(context);
//		if (context instanceof OnFragmentInteractionListener) {
//			mListener = (OnFragmentInteractionListener) context;
//		} else {
//			throw new RuntimeException(context.toString()
//					+ " must implement OnFragmentInteractionListener");
//		}
//	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(PCBuild sendBackText);
	}

//	public void testFragmentResultListener(){
//		scenario = launchFragmentIn
//	}
}