package com.example.buildmypc.ui.build;

import android.content.Context;
import android.os.Bundle;
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
		GridView gridView = binding.buildfragGridView;
//		buildViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
		// building myBuilds
		displayedBuilds.addAll(new Prebuilds().getPrebuiltList());
		GridAdapter adapter = new GridAdapter(getActivity(), displayedBuilds);
		gridView.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(getContext(), "activated " + position, Toast.LENGTH_SHORT).show());
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

	private class GridAdapter extends BaseAdapter {
		private final Context context;
		private LayoutInflater inflater;
		private final ArrayList<PCBuild> list;

		public GridAdapter(Context c, ArrayList<PCBuild> l) {
			context = c;
			list = l;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (inflater == null)
				inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (convertView == null) convertView = inflater.inflate(R.layout.row_item, null);
			// do stuff here

			PCBuild currentBuild = displayedBuilds.get(position);

			ImageView imageView = convertView.findViewById(R.id.gridView_imageView);
			TextView titleTextView = convertView.findViewById(R.id.gridView_textView);

			imageView.setImageDrawable(currentBuild.getLogo());
			titleTextView.setText(currentBuild.getName());

			return null;
		}
	}

	public class Prebuilds {
		private ArrayList<PCBuild> builds;

		public ArrayList<PCBuild> getPrebuiltList() {
			return builds;
		}

		public Prebuilds() {
			builds.add(new PCBuild(
					"Build 1",
					ResourcesCompat.getDrawable(getResources(), R.drawable.engadget, getResources().newTheme()),
					new Cooler("Hyper 212 EVO", "Cooler Master"),
					new CPU("Ryzen 5 3600", "AMD"),
					new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA", false),
					new Memory("Vengeance LPX 16 GB", "Corsair", null),
					new Monitor("ROG Swift PG65UQ", "Asus"),
					new Motherboard("B450 TOMAHAWK MAX", "MSI", false),
					new OS("Windows 10 Pro", "Microsoft"),
					new PSU("RM750 (2019)", "Corsair", null),
					new StorageComp("Barracuda Compute 2 TB", "Seagate", null),
					new ArrayList<Part>()
			));
			builds.add(new PCBuild(
					"Build 2",
					ResourcesCompat.getDrawable(getResources(), R.drawable.engadget, getResources().newTheme()),
					new Cooler("Hyper 212 EVO", "Cooler Master"),
					new CPU("Ryzen 5 3600", "AMD"),
					new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA", false),
					new Memory("Vengeance LPX 16 GB", "Corsair", null),
					new Monitor("ROG Swift PG65UQ", "Asus"),
					new Motherboard("B450 TOMAHAWK MAX", "MSI", false),
					new OS("Windows 10 Pro", "Microsoft"),
					new PSU("RM750 (2019)", "Corsair", null),
					new StorageComp("Barracuda Compute 2 TB", "Seagate", null),
					new ArrayList<Part>()
			));
			builds.add(new PCBuild(
					"Build 3",
					ResourcesCompat.getDrawable(getResources(), R.drawable.engadget, getResources().newTheme()),
					new Cooler("Hyper 212 EVO", "Cooler Master"),
					new CPU("Ryzen 5 3600", "AMD"),
					new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA", false),
					new Memory("Vengeance LPX 16 GB", "Corsair", null),
					new Monitor("ROG Swift PG65UQ", "Asus"),
					new Motherboard("B450 TOMAHAWK MAX", "MSI", false),
					new OS("Windows 10 Pro", "Microsoft"),
					new PSU("RM750 (2019)", "Corsair", null),
					new StorageComp("Barracuda Compute 2 TB", "Seagate", null),
					new ArrayList<Part>()
			));
			builds.add(new PCBuild(
					"Build 4",
					ResourcesCompat.getDrawable(getResources(), R.drawable.engadget, getResources().newTheme()),
					new Cooler("Hyper 212 EVO", "Cooler Master"),
					new CPU("Ryzen 5 3600", "AMD"),
					new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA", false),
					new Memory("Vengeance LPX 16 GB", "Corsair", null),
					new Monitor("ROG Swift PG65UQ", "Asus"),
					new Motherboard("B450 TOMAHAWK MAX", "MSI", false),
					new OS("Windows 10 Pro", "Microsoft"),
					new PSU("RM750 (2019)", "Corsair", null),
					new StorageComp("Barracuda Compute 2 TB", "Seagate", null),
					new ArrayList<Part>()
			));
			builds.add(new PCBuild(
					"Build 5",
					ResourcesCompat.getDrawable(getResources(), R.drawable.engadget, getResources().newTheme()),
					new Cooler("Hyper 212 EVO", "Cooler Master"),
					new CPU("Ryzen 5 3600", "AMD"),
					new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA", false),
					new Memory("Vengeance LPX 16 GB", "Corsair", null),
					new Monitor("ROG Swift PG65UQ", "Asus"),
					new Motherboard("B450 TOMAHAWK MAX", "MSI", false),
					new OS("Windows 10 Pro", "Microsoft"),
					new PSU("RM750 (2019)", "Corsair", null),
					new StorageComp("Barracuda Compute 2 TB", "Seagate", null),
					new ArrayList<Part>()
			));
			builds.add(new PCBuild(
					"Build 6",
					ResourcesCompat.getDrawable(getResources(), R.drawable.engadget, getResources().newTheme()),
					new Cooler("Hyper 212 EVO", "Cooler Master"),
					new CPU("Ryzen 5 3600", "AMD"),
					new GPU("GeForce RTX 3070 Founders Edition", "NVIDIA", false),
					new Memory("Vengeance LPX 16 GB", "Corsair", null),
					new Monitor("ROG Swift PG65UQ", "Asus"),
					new Motherboard("B450 TOMAHAWK MAX", "MSI", false),
					new OS("Windows 10 Pro", "Microsoft"),
					new PSU("RM750 (2019)", "Corsair", null),
					new StorageComp("Barracuda Compute 2 TB", "Seagate", null),
					new ArrayList<Part>()
			));
		}
	}
}