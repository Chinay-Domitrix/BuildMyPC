package com.example.buildmypc.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.buildmypc.databinding.FragmentSlideshowBinding
import com.example.buildmypc.databinding.FragmentSlideshowBinding.*

class SlideshowFragment : Fragment() {
	private lateinit var slideshowViewModel: SlideshowViewModel
	private var _binding: FragmentSlideshowBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		slideshowViewModel = ViewModelProvider(this).get(SlideshowViewModel::class.java)
		_binding = inflate(inflater, container, false)
		val root = binding.root
		val textView = binding.textSlideshow
		slideshowViewModel.text.observe(viewLifecycleOwner, { textView.text = it })
		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}