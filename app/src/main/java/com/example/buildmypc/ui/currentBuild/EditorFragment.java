package com.example.buildmypc.ui.currentBuild;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buildmypc.MainActivity;
import com.example.buildmypc.R;
import com.example.buildmypc.databinding.FragmentHomeBinding;
import com.example.buildmypc.ui.build.BuildFragment;
import com.example.buildmypc.ui.build.PCBuild;
import com.example.buildmypc.ui.parts.parts.Part;

import java.util.ArrayList;

import static com.example.buildmypc.databinding.FragmentHomeBinding.inflate;

public class EditorFragment extends Fragment implements AdapterView.OnItemSelectedListener  {

	private static final String BUILD = "pcbuild";
	public static final String BACK = "buildbackbetter";

	private EditorViewModel mViewModel;
	private PCBuild currentBuild;
	private FragmentHomeBinding binding;

	private Button goBackButton;
	private TextView myTextView;


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

//		if(((AppCompatActivity) getActivity()).getActionBar() != null) ((AppCompatActivity) getActivity()).getSupportActionBar().set;

		View root = inflater.inflate(R.layout.fragment_editor, container, false);
		goBackButton = root.findViewById(R.id.editorFragmentGoBackButton);
		goBackButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { // THIS WORKS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				String random = String.valueOf((int)(Math.random() * 10));
				myTextView.setText(random);

				// create the bundle
				PCBuild updatedBuild = new PCBuild();
				updatedBuild.setName(random);
//				Bundle result = new Bundle();
//				result.putParcelable(BACK, updatedBuild);

				int id = ((ViewGroup) getView().getParent()).getId();
				getActivity().getSupportFragmentManager().beginTransaction()
						.replace(id, new BuildFragment(currentBuild), "findThisOtherFragment")
						.addToBackStack(null)
						.commit();
			}
		});
//		myTextView = (TextView) root.findViewById(R.id.editorFragment_textView);
//		myTextView.setText(currentBuild.getName());

		ArrayList<Part> customList = new ArrayList<Part>();

		// spinners!
		Spinner caseSpinner = (Spinner) root.findViewById(R.id.editorFrag_caseSpinner);
		customList = MainActivity.pcCases.get();
		customList.add(0, new Part(" ", " "));
		caseSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), customList));

		Spinner coolerSpinner = (Spinner) root.findViewById(R.id.editorFrag_coolerSpinner);
		customList = MainActivity.coolers.get();
		customList.add(0, new Part(" ", " "));
		caseSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), customList));

		Spinner cpuSpinner = (Spinner) root.findViewById(R.id.editorFrag_cpuSpinner);
		customList = MainActivity.cpus.get();
		customList.add(0, new Part(" ", " "));
		caseSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), customList));

		Spinner gpuSpinner = (Spinner) root.findViewById(R.id.editorFrag_gpuSpinner);
		customList = MainActivity.gpus.get();
		customList.add(0, new Part(" ", " "));
		caseSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), customList));

		Spinner memorySpinner = (Spinner) root.findViewById(R.id.editorFrag_memorySpinner);
		customList = MainActivity.memory.get();
		customList.add(0, new Part(" ", " "));
		caseSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), customList));

		Spinner monitorSpinner = (Spinner) root.findViewById(R.id.editorFrag_monitorSpinner);
		customList = MainActivity.monitors.get();
		customList.add(0, new Part(" ", " "));
		caseSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), customList));

		Spinner motherboardSpinner = (Spinner) root.findViewById(R.id.editorFrag_motherboardSpinner);
		customList = MainActivity.motherboards.get();
		customList.add(0, new Part(" ", " "));
		caseSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), customList));

		Spinner osSpinner = (Spinner) root.findViewById(R.id.editorFrag_osSpinner);
		customList = MainActivity.memory.get();
		customList.add(0, new Part(" ", " "));
		caseSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), customList));

		Spinner psuSpinner = (Spinner) root.findViewById(R.id.editorFrag_psuSpinner);
		customList = MainActivity.psus.get();
		customList.add(0, new Part(" ", " "));
		caseSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), customList));

		Spinner storageSpinner = (Spinner) root.findViewById(R.id.editorFrag_storageSpinner);
		customList = MainActivity.storage.get();
		customList.add(0, new Part(" ", " "));
		caseSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), customList));


		return root;
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

//	@Override
//	public void onDetach() {
//		super.onDetach();
//		mListener = null;
//	}

//	public interface OnFragmentInteractionListener {
//		// TODO: Update argument type and name
//		void onFragmentInteraction(PCBuild sendBackText);
//	}

	private void openBuildFragment(PCBuild build) {
		BuildFragment fragment = BuildFragment.newInstance(currentBuild);
		getParentFragmentManager().beginTransaction()
				.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right)
				.addToBackStack(null)
				.add(R.id.nav_host_fragment_content_main, fragment, BUILD)
				.commit();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}
}