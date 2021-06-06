package com.example.buildmypc.ui.build;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildmypc.R;
import com.example.buildmypc.databinding.FragmentHomeBinding;
import com.example.buildmypc.ui.parts.parts.CPU;
import com.example.buildmypc.ui.parts.parts.Cooler;
import com.example.buildmypc.ui.parts.parts.GPU;
import com.example.buildmypc.ui.parts.parts.Memory;
import com.example.buildmypc.ui.parts.parts.Monitor;
import com.example.buildmypc.ui.parts.parts.Motherboard;
import com.example.buildmypc.ui.parts.parts.OS;
import com.example.buildmypc.ui.parts.parts.PSU;
import com.example.buildmypc.ui.parts.parts.Part;
import com.example.buildmypc.ui.parts.parts.StorageComp;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.buildmypc.databinding.FragmentHomeBinding.inflate;

public class BuildFragment extends Fragment {
	ArrayList<PCBuild> displayedBuilds;
	private FragmentHomeBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		BuildViewModel buildViewModel = new ViewModelProvider(this).get(BuildViewModel.class);
		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
//		final TextView textView = binding.textHome;
//		buildViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

		// building myBuilds
		displayedBuilds = new ArrayList<PCBuild>();
		displayedBuilds.addAll(new Prebuilds().getPrebuiltList());
		// TODO add adding custom builds, saving them, and accessing them in the future (prob Sunday/Monday/Tuesday if we're that late)

		RecyclerView recyclerView = binding.buildFragRecyclerView;
		RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
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

	private class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridHolder> {

		private Context context;
		private ArrayList<PCBuild> buildList;

		public GridAdapter(Context context, ArrayList<PCBuild> buildList) {
			this.context = context;
			this.buildList = buildList;
		}


		@NonNull
		@NotNull
		@Override
		public GridHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
			View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, null);
			GridHolder holder = new GridHolder(layout);

			return holder;
		}

		@Override
		public void onBindViewHolder(@NonNull @NotNull GridHolder holder, int position) {
			PCBuild currentBuild = buildList.get(position);

			holder.image.setImageDrawable(currentBuild.getLogo());
			holder.text.setText(currentBuild.getName());
		}

		@Override
		public int getItemCount() {
			return buildList.size();
		}

		public class GridHolder extends RecyclerView.ViewHolder{

			ImageView image;
			TextView text;

			public GridHolder(@NonNull @NotNull View itemView) {
				super(itemView);
				image = itemView.findViewById(R.id.gridView_imageView);
				text = itemView.findViewById(R.id.gridView_textView);
			}
		}
	}

	public class Prebuilds {
		private ArrayList<PCBuild> builds;

		public ArrayList<PCBuild> getPrebuiltList() {
			return builds;
		}

		public Prebuilds() {
			builds = new ArrayList<PCBuild>();
			builds.add(new PCBuild(
					"Build 1",
					ResourcesCompat.getDrawable(getResources(), R.drawable.engadget, getResources().newTheme()),
					new Cooler("Hyper 212 EVO", "Cooler Master"),
					new CPU("Ryzen 5 3600", "AMD"),
					new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
					new Memory("Vengeance LPX 16 GB", "Corsair"),
					new Monitor("ROG Swift PG65UQ", "Asus"),
					new Motherboard("B450 TOMAHAWK MAX", "MSI"),
					new OS("Windows 10 Pro", "Microsoft"),
					new PSU("RM750 (2019)", "Corsair"),
					new StorageComp("Barracuda Compute 2 TB", "Seagate"),
					new ArrayList<Part>()
			));
			builds.add(new PCBuild(
					"Build 2",
					ResourcesCompat.getDrawable(getResources(), R.drawable.engadget, getResources().newTheme()),
					new Cooler("Hyper 212 EVO", "Cooler Master"),
					new CPU("Ryzen 5 3600", "AMD"),
					new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
					new Memory("Vengeance LPX 16 GB", "Corsair"),
					new Monitor("ROG Swift PG65UQ", "Asus"),
					new Motherboard("B450 TOMAHAWK MAX", "MSI"),
					new OS("Windows 10 Pro", "Microsoft"),
					new PSU("RM750 (2019)", "Corsair"),
					new StorageComp("Barracuda Compute 2 TB", "Seagate"),
					new ArrayList<Part>()
			));
			builds.add(new PCBuild(
					"Build 3",
					ResourcesCompat.getDrawable(getResources(), R.drawable.engadget, getResources().newTheme()),
					new Cooler("Hyper 212 EVO", "Cooler Master"),
					new CPU("Ryzen 5 3600", "AMD"),
					new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
					new Memory("Vengeance LPX 16 GB", "Corsair"),
					new Monitor("ROG Swift PG65UQ", "Asus"),
					new Motherboard("B450 TOMAHAWK MAX", "MSI"),
					new OS("Windows 10 Pro", "Microsoft"),
					new PSU("RM750 (2019)", "Corsair"),
					new StorageComp("Barracuda Compute 2 TB", "Seagate"),
					new ArrayList<Part>()
			));
			builds.add(new PCBuild(
					"Build 4",
					ResourcesCompat.getDrawable(getResources(), R.drawable.engadget, getResources().newTheme()),
					new Cooler("Hyper 212 EVO", "Cooler Master"),
					new CPU("Ryzen 5 3600", "AMD"),
					new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
					new Memory("Vengeance LPX 16 GB", "Corsair"),
					new Monitor("ROG Swift PG65UQ", "Asus"),
					new Motherboard("B450 TOMAHAWK MAX", "MSI"),
					new OS("Windows 10 Pro", "Microsoft"),
					new PSU("RM750 (2019)", "Corsair"),
					new StorageComp("Barracuda Compute 2 TB", "Seagate"),
					new ArrayList<Part>()
			));
			builds.add(new PCBuild(
					"Build 5",
					ResourcesCompat.getDrawable(getResources(), R.drawable.engadget, getResources().newTheme()),
					new Cooler("Hyper 212 EVO", "Cooler Master"),
					new CPU("Ryzen 5 3600", "AMD"),
					new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
					new Memory("Vengeance LPX 16 GB", "Corsair"),
					new Monitor("ROG Swift PG65UQ", "Asus"),
					new Motherboard("B450 TOMAHAWK MAX", "MSI"),
					new OS("Windows 10 Pro", "Microsoft"),
					new PSU("RM750 (2019)", "Corsair"),
					new StorageComp("Barracuda Compute 2 TB", "Seagate"),
					new ArrayList<Part>()
			));
			builds.add(new PCBuild(
					"Build 6",
					ResourcesCompat.getDrawable(getResources(), R.drawable.engadget, getResources().newTheme()),
					new Cooler("Hyper 212 EVO", "Cooler Master"),
					new CPU("Ryzen 5 3600", "AMD"),
					new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
					new Memory("Vengeance LPX 16 GB", "Corsair"),
					new Monitor("ROG Swift PG65UQ", "Asus"),
					new Motherboard("B450 TOMAHAWK MAX", "MSI"),
					new OS("Windows 10 Pro", "Microsoft"),
					new PSU("RM750 (2019)", "Corsair"),
					new StorageComp("Barracuda Compute 2 TB", "Seagate"),
					new ArrayList<Part>()
			));


		}
	}
}