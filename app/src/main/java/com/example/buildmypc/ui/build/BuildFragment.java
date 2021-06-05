package com.example.buildmypc.ui.build;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.buildmypc.MainActivity;
import com.example.buildmypc.R;
import com.example.buildmypc.databinding.FragmentHomeBinding;
import com.example.buildmypc.ui.parts.parts.Part;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.buildmypc.databinding.FragmentHomeBinding.inflate;

public class BuildFragment extends Fragment {
	private FragmentHomeBinding binding;

	ArrayList<PCBuild> displayedBuilds;

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

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getContext(), "activated " + String.valueOf(position), Toast.LENGTH_SHORT).show();
			}
		});




		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

	private static class GridAdapter extends BaseAdapter {
		private Context context;
		private LayoutInflater inflater;
		private ArrayList<Part> list;

		public GridAdapter(Context c, ArrayList<?> l) {
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
			if(inflater == null){
				inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			if(convertView == null){
				convertView = inflater.inflate(R.layout.row_item, null);
			}

			// do stuff here
			return null;
		}
	}
}