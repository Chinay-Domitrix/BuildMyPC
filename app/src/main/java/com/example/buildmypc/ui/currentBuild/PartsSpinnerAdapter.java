package com.example.buildmypc.ui.currentBuild;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.buildmypc.R;
import com.example.buildmypc.ui.parts.parts.Part;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.view.LayoutInflater.from;
import static com.example.buildmypc.R.layout.spinner_custom;

public class PartsSpinnerAdapter extends ArrayAdapter<Part> {

	ArrayList<Part> internalList;
//	Part currentPart;

	public PartsSpinnerAdapter(Context context, List<Part> objects) {
		super(context, 0, objects);
		internalList = (ArrayList<Part>) objects;
//		this.currentPart = currentPart;
	}

	@Override
	public View getDropDownView(int position, View convertView, @NotNull ViewGroup parent) {
		return startView(position, convertView, parent);
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return startView(position, convertView, parent);
	}

	@Nullable
	@Override
	public Part getItem(int position) {
		return internalList.get(position);
	}

	@NotNull
	private View startView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView = from(getContext()).inflate(spinner_custom, parent, false);

		TextView text = convertView.findViewById(R.id.spinner_internalTextView);
		text.setText(Objects.requireNonNull(getItem(position)).toString());

		return convertView;
	}
}
