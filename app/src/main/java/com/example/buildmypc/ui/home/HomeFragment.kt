package com.example.buildmypc.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.buildmypc.databinding.FragmentHomeBinding
import com.example.buildmypc.databinding.FragmentHomeBinding.inflate

class HomeFragment : Fragment() {
	private lateinit var homeViewModel: HomeViewModel
	private var _binding: FragmentHomeBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
		_binding = inflate(inflater, container, false)
		val root = binding.root
		val textView = binding.textHome
		homeViewModel.text.observe(viewLifecycleOwner, { textView.text = it })
		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}