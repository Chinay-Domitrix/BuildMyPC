package com.example.buildmypc.ui.currentBuild;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.buildmypc.MainActivity;
import com.example.buildmypc.databinding.FragmentHomeBinding;
import com.example.buildmypc.ui.build.BuildFragment;
import com.example.buildmypc.ui.build.PCBuild;
import com.example.buildmypc.ui.parts.parts.Part;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.widget.AdapterView.OnItemSelectedListener;
import static com.example.buildmypc.R.anim.enter_from_right;
import static com.example.buildmypc.R.anim.exit_to_right;
import static com.example.buildmypc.R.id.editorFrag_caseSpinner;
import static com.example.buildmypc.R.id.editorFrag_coolerSpinner;
import static com.example.buildmypc.R.id.editorFrag_cpuSpinner;
import static com.example.buildmypc.R.id.editorFrag_gpuSpinner;
import static com.example.buildmypc.R.id.editorFrag_memorySpinner;
import static com.example.buildmypc.R.id.editorFrag_monitorSpinner;
import static com.example.buildmypc.R.id.editorFrag_motherboardSpinner;
import static com.example.buildmypc.R.id.editorFrag_osSpinner;
import static com.example.buildmypc.R.id.editorFrag_psuSpinner;
import static com.example.buildmypc.R.id.editorFrag_storageSpinner;
import static com.example.buildmypc.R.id.editorFragmentGoBackButton;
import static com.example.buildmypc.R.id.nav_host_fragment_content_main;
import static com.example.buildmypc.R.layout.fragment_editor;

public class EditorFragment extends Fragment {
	public static final String BACK = "buildbackbetter";
	private static final String BUILD = "pcbuild";
	private EditorViewModel mViewModel;
	private PCBuild currentBuild;
	private boolean isEditing;
	private FragmentHomeBinding binding;
	private Button goBackButton;
	private TextView myTextView;

	public EditorFragment(PCBuild currentBuild, boolean isEditing) {
		this.currentBuild = currentBuild;
		this.isEditing = isEditing;
	}

	public EditorFragment() {
	}

	public static EditorFragment newInstance(PCBuild build, boolean isEditing) {
		EditorFragment editorFragment = new EditorFragment();
		Bundle args = new Bundle();
		args.putParcelable(BUILD, build);
		args.putBoolean(BUILD, isEditing);
		return new EditorFragment(build, isEditing);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		PCBuild build = getArguments().getParcelable(BUILD);
	}

	@Override
	public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		if(((AppCompatActivity) getActivity()).getActionBar() != null) ((AppCompatActivity) getActivity()).getSupportActionBar().set;
		View root = inflater.inflate(fragment_editor, container, false);
		goBackButton = root.findViewById(editorFragmentGoBackButton);
		// TODO make it so that you can't edit pre builts
		goBackButton.setText(!currentBuild.getName().equals("ADD BUILD") ? "OVERWRITE" : "ADD");
		goBackButton.setOnClickListener(v -> { // THIS WORKS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// create the bundle
			PCBuild updatedBuild = currentBuild;
//				updatedBuild.setName("funny");
//				Bundle result = new Bundle();
//				result.putParcelable(BACK, updatedBuild);
			requireActivity().getSupportFragmentManager().beginTransaction().replace(((ViewGroup) requireView().getParent()).getId(), new BuildFragment(updatedBuild), "findThisOtherFragment").addToBackStack(null).commit();
		});
//		myTextView = (TextView) root.findViewById(R.id.editorFragment_textView);
//		myTextView.setText(currentBuild.getName());

//		ArrayList<Part> customList = new ArrayList<Part>();

		// spinners!
		Spinner caseSpinner = root.findViewById(editorFrag_caseSpinner);
		ArrayList<Part> caseList = MainActivity.pcCases.get();
		caseList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", " ") : currentBuild.getPcCase());
		caseSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), caseList));
		caseSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO have this set the newly selected part as a replacement
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Spinner coolerSpinner = root.findViewById(editorFrag_coolerSpinner);
		ArrayList<Part> coolerList = MainActivity.coolers.get();
		coolerList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", " ") : currentBuild.getCooler());
		coolerSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), coolerList));

		Spinner cpuSpinner = root.findViewById(editorFrag_cpuSpinner);
		ArrayList<Part> cpuList = MainActivity.cpus.get();
		cpuList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", " ") : currentBuild.getCpu());
		cpuSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), cpuList));

		Spinner gpuSpinner = root.findViewById(editorFrag_gpuSpinner);
		ArrayList<Part> gpuList = MainActivity.gpus.get();
		gpuList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", " ") : currentBuild.getGpu());
		gpuSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), gpuList));

		Spinner memorySpinner = root.findViewById(editorFrag_memorySpinner);
		ArrayList<Part> memoryList = MainActivity.memory.get();
		memoryList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", " ") : currentBuild.getMemory());
		memorySpinner.setAdapter(new PartsSpinnerAdapter(getContext(), memoryList));

		Spinner monitorSpinner = root.findViewById(editorFrag_monitorSpinner);
		ArrayList<Part> monitorList = MainActivity.monitors.get();
		monitorList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", " ") : currentBuild.getMonitor());
		monitorSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), monitorList));

		Spinner motherboardSpinner = root.findViewById(editorFrag_motherboardSpinner);
		ArrayList<Part> motherboardList = MainActivity.motherboards.get();
		motherboardList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", " ") : currentBuild.getMotherboard());
		motherboardSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), motherboardList));

		Spinner osSpinner = root.findViewById(editorFrag_osSpinner);
		ArrayList<Part> osList = MainActivity.oss.get();
		osList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", " ") : currentBuild.getOs());
		osSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), osList));

		Spinner psuSpinner = root.findViewById(editorFrag_psuSpinner);
		ArrayList<Part> psuList = MainActivity.psus.get();
		psuList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", " ") : currentBuild.getPsu());
		psuSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), psuList));

		Spinner storageSpinner = root.findViewById(editorFrag_storageSpinner);
		ArrayList<Part> storageList = MainActivity.storage.get();
		storageList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", " ") : currentBuild.getStorage());
		storageSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), storageList));


		return root;
	}

	@Override
	public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
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
		getParentFragmentManager().beginTransaction().setCustomAnimations(enter_from_right, exit_to_right, enter_from_right, exit_to_right).addToBackStack(null).add(nav_host_fragment_content_main, fragment, BUILD).commit();
	}

//	@Override
//	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//	}
//
//	@Override
//	public void onNothingSelected(AdapterView<?> parent) {
//
//	}
}