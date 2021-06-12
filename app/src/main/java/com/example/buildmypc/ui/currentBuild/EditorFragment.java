package com.example.buildmypc.ui.currentBuild;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.buildmypc.MainActivity;
import com.example.buildmypc.R;
import com.example.buildmypc.databinding.FragmentHomeBinding;
import com.example.buildmypc.ui.build.BuildFragment;
import com.example.buildmypc.ui.build.PCBuild;
import com.example.buildmypc.ui.parts.parts.Part;

import java.util.ArrayList;

public class EditorFragment extends Fragment implements AdapterView.OnItemSelectedListener {

	public static final String BACK = "buildbackbetter";
	private static final String BUILD = "pcbuild";
	private EditorViewModel mViewModel;
	private PCBuild currentBuild;
	private FragmentHomeBinding binding;

	private Button goBackButton;
	private TextView myTextView;


	public EditorFragment(PCBuild currentBuild) {
		this.currentBuild = currentBuild;
	}

	public EditorFragment() {
	}

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
				String random = String.valueOf((int) (Math.random() * 10));
//				myTextView.setText(random);

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

//		ArrayList<Part> customList = new ArrayList<Part>();

		// spinners!
		Spinner caseSpinner = root.findViewById(R.id.editorFrag_caseSpinner);
		ArrayList<Part> caseList = MainActivity.pcCases.get();
		if (currentBuild.toString().trim().length() == 0)
			caseList.add(0, new Part("", " "));
		else
			caseList.add(0, currentBuild.getPcCase());
		caseSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), caseList));

		Spinner coolerSpinner = root.findViewById(R.id.editorFrag_coolerSpinner);
		ArrayList<Part> coolerList = MainActivity.coolers.get();
		if (currentBuild.toString().trim().length() == 0)
			coolerList.add(0, new Part("", " "));
		else
			coolerList.add(0, currentBuild.getCooler());
		coolerSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), coolerList));

		Spinner cpuSpinner = root.findViewById(R.id.editorFrag_cpuSpinner);
		ArrayList<Part> cpuList = MainActivity.cpus.get();
		if (currentBuild.toString().trim().length() == 0)
			cpuList.add(0, new Part("", " "));
		else
			cpuList.add(0, currentBuild.getCpu());
		cpuSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), cpuList));

		Spinner gpuSpinner = root.findViewById(R.id.editorFrag_gpuSpinner);
		ArrayList<Part> gpuList = MainActivity.gpus.get();
		if (currentBuild.toString().trim().length() == 0)
			gpuList.add(0, new Part("", " "));
		else
			gpuList.add(0, currentBuild.getGpu());
		gpuSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), gpuList));

		Spinner memorySpinner = root.findViewById(R.id.editorFrag_memorySpinner);
		ArrayList<Part> memoryList = MainActivity.memory.get();
		if (currentBuild.toString().trim().length() == 0)
			memoryList.add(0, new Part("", " "));
		else
			memoryList.add(0, currentBuild.getMemory());
		memorySpinner.setAdapter(new PartsSpinnerAdapter(getContext(), memoryList));

		Spinner monitorSpinner = root.findViewById(R.id.editorFrag_monitorSpinner);
		ArrayList<Part> monitorList = MainActivity.monitors.get();
		if (currentBuild.toString().trim().length() == 0)
			monitorList.add(0, new Part("", " "));
		else
			monitorList.add(0, currentBuild.getMonitor());
		monitorSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), monitorList));

		Spinner motherboardSpinner = root.findViewById(R.id.editorFrag_motherboardSpinner);
		ArrayList<Part> motherboardList = MainActivity.motherboards.get();
		if (currentBuild.toString().trim().length() == 0)
			motherboardList.add(0, new Part("", " "));
		else
			motherboardList.add(0, currentBuild.getMotherboard());
		motherboardSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), motherboardList));

		Spinner osSpinner = root.findViewById(R.id.editorFrag_osSpinner);
		ArrayList<Part> osList = MainActivity.memory.get();
		if (currentBuild.toString().trim().length() == 0)
			osList.add(0, new Part("", " "));
		else
			osList.add(0, currentBuild.getOs());
		osSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), osList));

		Spinner psuSpinner = root.findViewById(R.id.editorFrag_psuSpinner);
		ArrayList<Part> psuList = MainActivity.psus.get();
		if (currentBuild.toString().trim().length() == 0)
			psuList.add(0, new Part("", " "));
		else
			psuList.add(0, currentBuild.getPsu());
		psuSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), psuList));

		Spinner storageSpinner = root.findViewById(R.id.editorFrag_storageSpinner);
		ArrayList<Part> storageList = MainActivity.storage.get();
		if (currentBuild.toString().trim().length() == 0)
			storageList.add(0, new Part("", " "));
		else
			storageList.add(0, currentBuild.getStorage());
		storageSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), storageList));


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