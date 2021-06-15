package com.example.buildmypc.ui.build;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

// le comment'
import com.example.buildmypc.MainActivity;
import com.example.buildmypc.databinding.FragmentHomeBinding;
import com.example.buildmypc.ui.currentBuild.EditorFragment;
import com.example.buildmypc.ui.parts.parts.CPU;
import com.example.buildmypc.ui.parts.parts.Case;
import com.example.buildmypc.ui.parts.parts.Cooler;
import com.example.buildmypc.ui.parts.parts.GPU;
import com.example.buildmypc.ui.parts.parts.Memory;
import com.example.buildmypc.ui.parts.parts.Monitor;
import com.example.buildmypc.ui.parts.parts.Motherboard;
import com.example.buildmypc.ui.parts.parts.OS;
import com.example.buildmypc.ui.parts.parts.PSU;
import com.example.buildmypc.ui.parts.parts.Storage;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static android.util.Log.d;
import static android.view.LayoutInflater.from;
import static androidx.core.content.res.ResourcesCompat.getDrawable;
import static com.example.buildmypc.R.anim.enter_from_right;
import static com.example.buildmypc.R.anim.exit_to_right;
import static com.example.buildmypc.R.drawable.a_275r_black;
import static com.example.buildmypc.R.drawable.a_4000d_airflow_black;
import static com.example.buildmypc.R.drawable.h510_elite_black;
import static com.example.buildmypc.R.drawable.h510_elite_white;
import static com.example.buildmypc.R.drawable.plus_logo;
import static com.example.buildmypc.R.id.gridView_imageView;
import static com.example.buildmypc.R.id.gridView_textView;
import static com.example.buildmypc.R.id.nav_host_fragment_content_main;
import static com.example.buildmypc.R.layout.row_item;
import static com.example.buildmypc.databinding.FragmentHomeBinding.inflate;
import static java.lang.Integer.MAX_VALUE;

public class BuildFragment extends Fragment {
	private static final String BUILD = "pcbuild";
	ArrayList<PCBuild> displayedBuilds;
	PCBuild currentEditedBuild;
	private FragmentHomeBinding binding;

	public BuildFragment(PCBuild build) {
		this.currentEditedBuild = build;
	}

	public BuildFragment() {
	}

	public static BuildFragment newInstance(PCBuild build) {
		BuildFragment fragment = new BuildFragment();
		Bundle args = new Bundle();
		args.putParcelable(BUILD, build);
		return new BuildFragment(build);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getParentFragmentManager().setFragmentResultListener(BUILD, this, (requestKey, result) -> currentEditedBuild = result.getParcelable(BUILD));
	}

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		BuildViewModel buildViewModel = new ViewModelProvider(this).get(BuildViewModel.class);

		PCBuild basicAddBuild = new PCBuild(
				"ADD BUILD", // TODO institute a check for this name
				getDrawable(getResources(), plus_logo, getResources().newTheme()),
				new Case(" ", " ", " "),
				new Cooler(" ", " "),
				new CPU(" ", " "),
				new GPU(" ", " "),
				new Memory(" ", " "),
				new Monitor(" ", " "),
				new Motherboard(" ", " "),
				new OS(" ", " "),
				new PSU(" ", " "),
				new Storage(" ", " "),
				new ArrayList<>(),
				-11 // ID number for the specific entry
		);


//		if(((AppCompatActivity) getActivity()).getActionBar() != null)
//			((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
//		final TextView textView = binding.textHome;
//		buildViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

		// building myBuilds
		displayedBuilds = new ArrayList<>();
		displayedBuilds.addAll(MainActivity.personalBuildList.get());
//		displayedBuilds.addAll(new Prebuilds().getPrebuiltList());
		if (currentEditedBuild != null) {
			int[] idList;
			// checks if currentEditedBuild is an edited version of an existing build
			// find the position of the build with this ID number and replace it
			if (displayedBuilds.size() > 0 && isIncluded(currentEditedBuild.getIdNumber(), idList(displayedBuilds)))
				for (int i = 0; i < displayedBuilds.size(); i++) {
					if (displayedBuilds.get(i).getIdNumber() == currentEditedBuild.getIdNumber()) {
						displayedBuilds.set(i, currentEditedBuild);
						break;
					}
					if (i == displayedBuilds.size() - 1)
						d("TAG", "Error in isIncluded statement around line 91 -?> for loop didn't find any entries");
				}
			else if(!currentEditedBuild.equals(basicAddBuild)) {
				displayedBuilds.add(currentEditedBuild);
			}

			// check to remove all basicAddBuild objects from personalBuildList
			for(int i = displayedBuilds.size() - 1; i >= 0; i--){
				PCBuild test = displayedBuilds.get(i);
				Log.d("NAME", String.valueOf(test.getName().equalsIgnoreCase("ADD BUILD")));
				if(test.getName().equalsIgnoreCase("ADD BUILD")) {
					displayedBuilds.remove(i);
				}
			}

			MainActivity.personalBuildList.set(displayedBuilds);
		}
		// adding the (ADD BUILD) entry
		displayedBuilds.add(new PCBuild(
				"ADD BUILD", // TODO institute a check for this name
				getDrawable(getResources(), plus_logo, getResources().newTheme()),
				new Case(" ", " ", " "),
				new Cooler(" ", " "),
				new CPU(" ", " "),
				new GPU(" ", " "),
				new Memory(" ", " "),
				new Monitor(" ", " "),
				new Motherboard(" ", " "),
				new OS(" ", " "),
				new PSU(" ", " "),
				new Storage(" ", " "),
				new ArrayList<>(),
				-11 // ID number for the specific entry
		));
//		TODO add adding custom builds, saving them, and accessing them in the future (prob Sunday/Monday/Tuesday if we're that late)

		RecyclerView recyclerView = binding.buildFragRecyclerView;
		LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(layoutManager);
		GridAdapter gridAdapter = new GridAdapter(getContext(), displayedBuilds);
		recyclerView.setAdapter(gridAdapter);

		// this decouples the fragment | DO NOT TOUCH
//		if(MainActivity.personalBuildList.get().size() > 2 && MainActivity.personalBuildList.get().get(MainActivity.personalBuildList.get().size() - 1).getName().equals("ADD BUILD") && currentEditedBuild == null)
//			MainActivity.personalBuildList.get().remove(MainActivity.personalBuildList.get().size() - 1);

		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

	private void openEditorFragment(PCBuild currentBuild, boolean isEditing) {
		EditorFragment fragment = EditorFragment.newInstance(currentBuild, isEditing);
		getParentFragmentManager().beginTransaction().setCustomAnimations(enter_from_right, exit_to_right, enter_from_right, exit_to_right).addToBackStack(null).add(nav_host_fragment_content_main, fragment, BUILD).commit();
	}

	public int[] idList(ArrayList<PCBuild> builds) {
		if (builds == null || builds.size() == 0)
			return null; // throwing a wrench into the system | might make it throw an exception
		int[] result = new int[builds.size()];
		for (int i = 0; i < builds.size(); i++) {
			result[i] = builds.get(i).getIdNumber();
		}
		return result;
	}

	public boolean isIncluded(int testInt, int[] arr) {
		for (int entry : arr) {
			if (testInt == entry) return true;
		}
		return false;
	}

	private class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridHolder> {

		private final ArrayList<PCBuild> buildList;

		public GridAdapter(Context context, ArrayList<PCBuild> buildList) {
			this.buildList = buildList;
		}

		@NonNull
		@NotNull
		@Override
		public GridHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
			View layout = from(parent.getContext()).inflate(row_item, null);
			GridHolder holder = new GridHolder(layout);

			return holder;
		}

		@Override
		public void onBindViewHolder(@NonNull @NotNull GridHolder holder, int position) {
			PCBuild currentBuild = buildList.get(position);

			d("DEBUGSTR", "pos: " + position);

			holder.image.setImageDrawable(currentBuild.getLogo());
			holder.text.setText(currentBuild.getName());
			// setting the OnClickListener to start a new fragment while passing in the currentBuild object
			holder.image.setOnClickListener(v -> {
				// check if we're using the (+) button
				boolean isEditing = true;
				if (buildList.get(position).getIdNumber() == -11) {
					currentBuild.setIdNumber(new Random().nextInt(MAX_VALUE)); // id generator
					isEditing = false;
				}
				// generating and stuffing the new fragment into the current view
				requireActivity().
						getSupportFragmentManager().
						beginTransaction().
						replace(((ViewGroup) requireView().
										getParent()).
										getId(),
								new EditorFragment(currentBuild,
										true),
								"findThisFragment").
						addToBackStack(null).
						commit();
//					openEditorFragment(currentBuild);

			});
		}

		@Override
		public int getItemCount() {
			return buildList.size();
		}

		public class GridHolder extends RecyclerView.ViewHolder {

			final ImageView image;
			final TextView text;

			public GridHolder(@NonNull @NotNull View itemView) {
				super(itemView);
				image = itemView.findViewById(gridView_imageView);
				text = itemView.findViewById(gridView_textView);

//				image.setOnClickListener(new View.OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						EditorFragment editor = new EditorFragment()
//					}
//				});
			}
		}
	}

	public class Prebuilds {
		private final ArrayList<PCBuild> builds;

		public Prebuilds() {
			builds = new ArrayList<>(Arrays.asList(new PCBuild("Build 1",
							getDrawable(getResources(), h510_elite_black, getResources().newTheme()),
							new Case("H510 Elite Tower", "Corsair", "Black"),
							new Cooler("Hyper 212 EVO", "Cooler Master"),
							new CPU("Ryzen 5 3600", "AMD"),
							new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
							new Memory("Vengeance LPX 16 GB", "Corsair"),
							new Monitor("ROG Swift PG65UQ", "Asus"),
							new Motherboard("B450 TOMAHAWK MAX", "MSI"),
							new OS("Windows 10 Pro", "Microsoft"),
							new PSU("RM750 (2019)", "Corsair"),
							new Storage("Barracuda Compute 2 TB", "Seagate"),
							new ArrayList<>(),
							-1),
					new PCBuild("Build 2",
							getDrawable(getResources(), h510_elite_white, getResources().newTheme()),
							new Case("H510 Elite Tower", "Corsair", "White"),
							new Cooler("Hyper 212 EVO", "Cooler Master"),
							new CPU("Ryzen 5 3600", "AMD"),
							new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
							new Memory("Vengeance LPX 16 GB", "Corsair"),
							new Monitor("ROG Swift PG65UQ", "Asus"),
							new Motherboard("B450 TOMAHAWK MAX", "MSI"),
							new OS("Windows 10 Pro", "Microsoft"),
							new PSU("RM750 (2019)", "Corsair"),
							new Storage("Barracuda Compute 2 TB", "Seagate"),
							new ArrayList<>(),
							-2),
					new PCBuild("Build 3",
							getDrawable(getResources(), h510_elite_black, getResources().newTheme()),
							new Case("H510 Elite Tower", "Corsair", "Black"),
							new Cooler("Hyper 212 EVO", "Cooler Master"),
							new CPU("Ryzen 5 3600", "AMD"),
							new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
							new Memory("Vengeance LPX 16 GB", "Corsair"),
							new Monitor("ROG Swift PG65UQ", "Asus"),
							new Motherboard("B450 TOMAHAWK MAX", "MSI"),
							new OS("Windows 10 Pro", "Microsoft"),
							new PSU("RM750 (2019)", "Corsair"),
							new Storage("Barracuda Compute 2 TB", "Seagate"),
							new ArrayList<>(),
							-3),
					new PCBuild("Build 4",
							getDrawable(getResources(), a_275r_black, getResources().newTheme()),
							new Case("275R Tower", "Corsair", "Black"),
							new Cooler("Hyper 212 EVO", "Cooler Master"),
							new CPU("Ryzen 5 3600", "AMD"),
							new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
							new Memory("Vengeance LPX 16 GB", "Corsair"),
							new Monitor("ROG Swift PG65UQ", "Asus"),
							new Motherboard("B450 TOMAHAWK MAX", "MSI"),
							new OS("Windows 10 Pro", "Microsoft"),
							new PSU("RM750 (2019)", "Corsair"),
							new Storage("Barracuda Compute 2 TB", "Seagate"),
							new ArrayList<>(),
							-4),
					new PCBuild("Build 5",
							getDrawable(getResources(), a_4000d_airflow_black, getResources().newTheme()),
							new Case("4000D Airflow", "Corsair", "Black"),
							new Cooler("Hyper 212 EVO", "Cooler Master"),
							new CPU("Ryzen 5 3600", "AMD"),
							new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
							new Memory("Vengeance LPX 16 GB", "Corsair"),
							new Monitor("ROG Swift PG65UQ", "Asus"),
							new Motherboard("B450 TOMAHAWK MAX", "MSI"),
							new OS("Windows 10 Pro", "Microsoft"),
							new PSU("RM750 (2019)", "Corsair"),
							new Storage("Barracuda Compute 2 TB", "Seagate"),
							new ArrayList<>(),
							-5),
					new PCBuild("Build 6",
							getDrawable(getResources(), h510_elite_black, getResources().newTheme()),
							new Case("H510 Elite Tower", "Corsair", "Black"),
							new Cooler("Hyper 212 EVO", "Cooler Master"),
							new CPU("Ryzen 5 3600", "AMD"),
							new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
							new Memory("Vengeance LPX 16 GB", "Corsair"),
							new Monitor("ROG Swift PG65UQ", "Asus"),
							new Motherboard("B450 TOMAHAWK MAX", "MSI"),
							new OS("Windows 10 Pro", "Microsoft"),
							new PSU("RM750 (2019)", "Corsair"),
							new Storage("Barracuda Compute 2 TB", "Seagate"),
							new ArrayList<>(),
							-6)));
		}

		public ArrayList<PCBuild> getPrebuiltList() {
			return builds;
		}
	}
}