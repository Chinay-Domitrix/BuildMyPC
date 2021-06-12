package com.example.buildmypc.ui.build;

import android.content.Context;
import android.os.Bundle;
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

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.view.LayoutInflater.from;
import static androidx.core.content.res.ResourcesCompat.getDrawable;
import static com.example.buildmypc.R.anim.enter_from_right;
import static com.example.buildmypc.R.anim.exit_to_right;
import static com.example.buildmypc.R.drawable.a_275r_black;
import static com.example.buildmypc.R.drawable.a_4000d_airflow_black;
import static com.example.buildmypc.R.drawable.h510_elite_black;
import static com.example.buildmypc.R.drawable.h510_elite_white;
import static com.example.buildmypc.R.id.gridView_imageView;
import static com.example.buildmypc.R.id.gridView_textView;
import static com.example.buildmypc.R.id.nav_host_fragment_content_main;
import static com.example.buildmypc.R.layout.row_item;
import static com.example.buildmypc.databinding.FragmentHomeBinding.inflate;

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

	@NotNull
	@Contract("_ -> new")
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

//		if(((AppCompatActivity) getActivity()).getActionBar() != null)
//			((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
//		final TextView textView = binding.textHome;
//		buildViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

		// building myBuilds
		displayedBuilds = new ArrayList<>();
		displayedBuilds.addAll(new Prebuilds().getPrebuiltList());
		if (currentEditedBuild != null) displayedBuilds.add(currentEditedBuild);
//		TODO add adding custom builds, saving them, and accessing them in the future (prob Sunday/Monday/Tuesday if we're that late)

		RecyclerView recyclerView = binding.buildFragRecyclerView;
		LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(layoutManager);
		GridAdapter gridAdapter = new GridAdapter(getContext(), displayedBuilds);
		recyclerView.setAdapter(gridAdapter);

		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

	private void openEditorFragment(PCBuild currentBuild) {
		EditorFragment fragment = EditorFragment.newInstance(currentBuild);
		getParentFragmentManager().beginTransaction().setCustomAnimations(enter_from_right, exit_to_right, enter_from_right, exit_to_right).addToBackStack(null).add(nav_host_fragment_content_main, fragment, BUILD).commit();
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

			holder.image.setImageDrawable(currentBuild.getLogo());
			holder.text.setText(currentBuild.getName());
			// setting the OnClickListener to start a new fragment while passing in the currentBuild object
			holder.image.setOnClickListener(v -> {
				int id = ((ViewGroup) requireView().getParent()).getId();
				requireActivity().getSupportFragmentManager().beginTransaction()
						.replace(id, new EditorFragment(currentBuild), "findThisFragment")
						.addToBackStack(null)
						.commit();
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
			builds = new ArrayList<>();
			builds.add(new PCBuild(
					"Build 1",
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
					new ArrayList<>()
			));
			builds.add(new PCBuild(
					"Build 2",
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
					new ArrayList<>()
			));
			builds.add(new PCBuild(
					"Build 3",
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
					new ArrayList<>()
			));
			builds.add(new PCBuild(
					"Build 4",
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
					new ArrayList<>()
			));
			builds.add(new PCBuild(
					"Build 5",
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
					new ArrayList<>()
			));
			builds.add(new PCBuild(
					"Build 6",
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
					new ArrayList<>()
			));
		}

		public ArrayList<PCBuild> getPrebuiltList() {
			return builds;
		}
	}
}