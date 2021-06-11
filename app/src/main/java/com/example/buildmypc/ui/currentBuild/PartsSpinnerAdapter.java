package com.example.buildmypc.ui.currentBuild;

import android.content.Context;
import android.database.DataSetObserver;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.buildmypc.R;
import com.example.buildmypc.ui.parts.parts.Part;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PartsSpinnerAdapter extends ArrayAdapter<Part>{

	ArrayList<Part> internalList;

	public PartsSpinnerAdapter(@NonNull Context context, @NonNull List<Part> objects) {
		super(context, 0, objects);
		internalList = (ArrayList<Part>) objects;
	}

	@Override
	public View getDropDownView(int position, @Nullable @org.jetbrains.annotations.Nullable View convertView, @NonNull @NotNull ViewGroup parent) {
		return super.getDropDownView(position, convertView, parent);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		return startView(position, convertView, parent);
	}

	@Nullable
	@Override
	public Part getItem(int position) {
		return internalList.get(position);
	}

	private View startView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.spinner_custom, parent, false
			);
		}

		TextView text = convertView.findViewById(R.id.spinner_internalTextView);
		text.setText(getItem(position).toString());

		return convertView;
	}
}
