package com.example.buildmypc.ui.currentBuild;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.buildmypc.MainActivity;
import com.example.buildmypc.R;
import com.example.buildmypc.databinding.FragmentHomeBinding;
import com.example.buildmypc.ui.build.BuildFragment;
import com.example.buildmypc.ui.build.PCBuild;
import com.example.buildmypc.ui.parts.parts.CPU;
import com.example.buildmypc.ui.parts.parts.Case;
import com.example.buildmypc.ui.parts.parts.Cooler;
import com.example.buildmypc.ui.parts.parts.GPU;
import com.example.buildmypc.ui.parts.parts.Memory;
import com.example.buildmypc.ui.parts.parts.Monitor;
import com.example.buildmypc.ui.parts.parts.Motherboard;
import com.example.buildmypc.ui.parts.parts.OS;
import com.example.buildmypc.ui.parts.parts.PSU;
import com.example.buildmypc.ui.parts.parts.Part;
import com.example.buildmypc.ui.parts.parts.Storage;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.widget.AdapterView.OnItemSelectedListener;
import static androidx.core.content.res.ResourcesCompat.getDrawable;
import static com.example.buildmypc.R.anim.enter_from_right;
import static com.example.buildmypc.R.anim.exit_to_right;
import static com.example.buildmypc.R.drawable.a_275r_black;
import static com.example.buildmypc.R.drawable.a_4000d_airflow_black;
import static com.example.buildmypc.R.drawable.h510_elite_black;
import static com.example.buildmypc.R.drawable.h510_elite_white;
import static com.example.buildmypc.R.drawable.pc011dx_black;
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

	private ArrayList<Part> cpuList;
	private ArrayList<Part> gpuList;
	private ArrayList<Part> memoryList;
	private ArrayList<Part> motherboardList;
	private ArrayList<Part> osList;
	private ArrayList<Part> psuList;
	private ArrayList<Part> coolerList;
	private ArrayList<Part> storageList;
	private ArrayList<Part> monitorList;
	private ArrayList<Part> caseList;

	private EditText nameEditText;

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

		// EditText
		nameEditText = root.findViewById(R.id.editorFrag_editText);
		if(currentBuild != null && currentBuild.getName().length() > 0) {
			nameEditText.setText(currentBuild.getName());
		}
		nameEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		}); // empty because it doesn't need to have a listener



		// go back button code
		goBackButton.setText(!currentBuild.getName().equals("ADD BUILD") ? "OVERWRITE" : "ADD");
		goBackButton.setOnClickListener(v -> { // THIS WORKS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// create the bundle
			PCBuild updatedBuild = currentBuild;
			switch (currentBuild.getPcCase().toString()) {
			/*  White NZXT H510 ATX Mid Tower
				Black NZXT H510 ATX Mid Tower
				Black Corsair 4000D Airflow ATX Mid Tower
				Black Corsair 275R Airflow AIX Mid Tower
				Black Lian Li PC-O11DX ATX Full Tower   */
				// TODO fix this
				case "White NZXT H510 ATX Mid Tower":
					updatedBuild.setLogo(getDrawable(getResources(), h510_elite_white, requireActivity().getTheme()));
					break;

				case "Black NZXT H510 ATX Mid Tower":
					updatedBuild.setLogo((getDrawable(getResources(), h510_elite_black, requireActivity().getTheme())));
					break;

				case "Black Corsair 4000D Airflow ATX Mid Tower":
					updatedBuild.setLogo(getDrawable(getResources(), a_4000d_airflow_black, requireActivity().getTheme()));
					break;

				case "Black Corsair 275R Airflow AIX Mid Tower":
					updatedBuild.setLogo(getDrawable(getResources(), a_275r_black, requireActivity().getTheme()));
					break;

				case "Black Lian Li PC-O11DX ATX Full Tower":
					updatedBuild.setLogo(getDrawable(getResources(), pc011dx_black, requireActivity().getTheme()));
					break;


			}
			updatedBuild.setName(nameEditText.getText().toString());
			Bundle result = new Bundle();
			result.putParcelable(BACK, updatedBuild);
			// destroy the lists
			cpuList.remove(0);
			gpuList.remove(0);
			memoryList.remove(0);
			motherboardList.remove(0);
//			osList.remove(0);
//			psuList.remove(0);
			coolerList.remove(0);
			storageList.remove(0);
//			monitorList.remove(0);
			caseList.remove(0);

			// switch fragments
			requireActivity()
					.getSupportFragmentManager()
					.beginTransaction()
					.replace(((ViewGroup) requireView().getParent()).getId(), new BuildFragment(updatedBuild), "findThisOtherFragment")
					.addToBackStack(null)
					.commit();
		});
//		myTextView = (TextView) root.findViewById(R.id.editorFragment_textView);
//		myTextView.setText(currentBuild.getName());

//		ArrayList<Part> customList = new ArrayList<Part>();

		// spinners!
		// TODO gray out the add/overwrite button if only the "Part" elements are selected instead of the subclass elements
		Spinner caseSpinner = root.findViewById(editorFrag_caseSpinner);
		caseList = MainActivity.pcCases.get();
		caseList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", "") : currentBuild.getPcCase());
		caseSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), caseList));
		caseSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (caseList.get(position) instanceof Case) {
					currentBuild.setPcCase((Case) caseList.get(position));
					Drawable newLogo = null;
					switch (caseList.get(position).getName().toUpperCase()){
						case "BLACK CORSAIR 275R AIRFLOW ATX MID TOWER":
							newLogo = getDrawable(getResources(), a_275r_black, getResources().newTheme());
							break;

						case "WHITE NZXT H510 ATX MID TOWER":
							newLogo = getDrawable(getResources(), h510_elite_white, getResources().newTheme());
							break;

						case "BLACK NZXT H510 ATX MID TOWER":
							newLogo = getDrawable(getResources(), h510_elite_black, getResources().newTheme());
							break;
						case "BLACK CORSAIR 4000D AIRFLOW ATX MID TOWER":
							newLogo = getDrawable(getResources(), a_4000d_airflow_black, getResources().newTheme());
							break;
						case "BLACK LIAN LI PC-O11DX ATX FULL TOWER":
							newLogo = getDrawable(getResources(), pc011dx_black, getResources().newTheme());
							break;
//						default:
//							throw new IllegalStateException("Unexpected value: " + caseList.get(position).getName().toUpperCase());
					}
					if(newLogo != null) currentBuild.setLogo(newLogo);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Spinner coolerSpinner = root.findViewById(editorFrag_coolerSpinner);
		coolerList = MainActivity.coolers.get();
		coolerList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", "") : currentBuild.getCooler());
		coolerSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), coolerList));
		coolerSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (coolerList.get(position) instanceof Cooler)
					currentBuild.setCooler((Cooler)coolerList.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});


		Spinner cpuSpinner = root.findViewById(editorFrag_cpuSpinner);
		cpuList = MainActivity.cpus.get();
		cpuList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", "") : currentBuild.getCpu());
		cpuSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), cpuList));
		cpuSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (cpuList.get(position) instanceof CPU)
					currentBuild.setCpu((CPU) cpuList.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Spinner gpuSpinner = root.findViewById(editorFrag_gpuSpinner);
		gpuList = MainActivity.gpus.get();
		gpuList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", "") : currentBuild.getGpu());
		gpuSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), gpuList));
		gpuSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (gpuList.get(position) instanceof GPU)
					currentBuild.setGpu((GPU) gpuList.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Spinner memorySpinner = root.findViewById(editorFrag_memorySpinner);
		memoryList = MainActivity.memory.get();
		memoryList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", "") : currentBuild.getMemory());
		memorySpinner.setAdapter(new PartsSpinnerAdapter(getContext(), memoryList));
		memorySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (memoryList.get(position) instanceof Memory)
					currentBuild.setMemory((Memory) memoryList.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Spinner monitorSpinner = root.findViewById(editorFrag_monitorSpinner);
		monitorList = MainActivity.monitors.get();
		monitorList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", "") : currentBuild.getMonitor());
		monitorSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), monitorList));
		monitorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (monitorList.get(position) instanceof Monitor)
					currentBuild.setMonitor((Monitor) monitorList.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Spinner motherboardSpinner = root.findViewById(editorFrag_motherboardSpinner);
		motherboardList = MainActivity.motherboards.get();
		motherboardList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", "") : currentBuild.getMotherboard());
		motherboardSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), motherboardList));
		motherboardSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (motherboardList.get(position) instanceof Motherboard)
					currentBuild.setMotherboard((Motherboard) motherboardList.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Spinner osSpinner = root.findViewById(editorFrag_osSpinner);
		osList = MainActivity.oss.get();
		osList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", "") : currentBuild.getOs());
		osSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), osList));
		osSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (osList.get(position) instanceof OS)
					currentBuild.setOs((OS) osList.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Spinner psuSpinner = root.findViewById(editorFrag_psuSpinner);
		psuList = MainActivity.psus.get();
		psuList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", "") : currentBuild.getPsu());
		psuSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), psuList));
		psuSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (psuList.get(position) instanceof PSU)
					currentBuild.setPsu((PSU) psuList.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Spinner storageSpinner = root.findViewById(editorFrag_storageSpinner);
		storageList = MainActivity.storage.get();
		storageList.add(0, (currentBuild.toString().trim().length() == 0) ? new Part("", "") : currentBuild.getStorage());
		storageSpinner.setAdapter(new PartsSpinnerAdapter(getContext(), storageList));
		storageSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (storageList.get(position) instanceof Storage)
					currentBuild.setStorage((Storage) storageList.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});


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