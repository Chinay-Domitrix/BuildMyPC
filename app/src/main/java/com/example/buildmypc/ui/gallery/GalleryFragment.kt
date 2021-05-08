package com.example.buildmypc.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.buildmypc.databinding.FragmentGalleryBinding
import com.example.buildmypc.databinding.FragmentGalleryBinding.inflate

class GalleryFragment : Fragment() {
	private lateinit var galleryViewModel: GalleryViewModel
	private var _binding: FragmentGalleryBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
		_binding = inflate(inflater, container, false)
		val root = binding.root
		val textView = binding.textGallery
		galleryViewModel.text.observe(viewLifecycleOwner, { textView.text = it })
		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}