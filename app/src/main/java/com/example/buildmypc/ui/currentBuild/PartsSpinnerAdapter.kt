package com.example.buildmypc.ui.currentBuild

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.buildmypc.R.id.spinner_internalTextView
import com.example.buildmypc.ui.parts.parts.Part
import java.util.Objects.requireNonNull

class PartsSpinnerAdapter(context: Context?, objects: List<Part>) :
	ArrayAdapter<Part?>(context!!, 0, objects) {
	var internalList: ArrayList<Part> = objects as ArrayList<Part>
	override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View? =
		convertView?.let { startView(position, it) }

	override fun getView(position: Int, convertView: View?, parent: ViewGroup) =
		startView(position, convertView!!)

	override fun getItem(position: Int) = internalList[position]

	private fun startView(position: Int, convertView: View): View {
		convertView.findViewById<TextView>(spinner_internalTextView).text =
			requireNonNull(getItem(position)).toString()
		return convertView
	}
}