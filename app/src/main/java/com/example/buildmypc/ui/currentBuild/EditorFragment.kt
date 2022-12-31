package com.example.buildmypc.ui.currentBuild

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.core.content.res.ResourcesCompat.getDrawable
import androidx.fragment.app.Fragment
import com.example.buildmypc.MainActivity.*
import com.example.buildmypc.R.anim
import com.example.buildmypc.R.drawable.*
import com.example.buildmypc.R.id.*
import com.example.buildmypc.R.layout.fragment_editor
import com.example.buildmypc.ui.build.BuildFragment
import com.example.buildmypc.ui.build.PCBuild
import com.example.buildmypc.ui.parts.parts.*
import java.util.*
import java.util.Locale.getDefault

class EditorFragment(private var currentBuild: PCBuild, private var isEditing: Boolean) :
	Fragment() {
	private lateinit var goBackButton: Button
	private lateinit var cpuList: ArrayList<Part>
	private lateinit var gpuList: ArrayList<Part>
	private lateinit var memoryList: ArrayList<Part>
	private lateinit var motherboardList: ArrayList<Part>
	private lateinit var osList: ArrayList<Part>
	private lateinit var psuList: ArrayList<Part>
	private lateinit var coolerList: ArrayList<Part>
	private lateinit var storageList: ArrayList<Part>
	private lateinit var monitorList: ArrayList<Part>
	private lateinit var caseList: ArrayList<Part>
	private lateinit var nameEditText: EditText

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
//		PCBuild build = getArguments().getParcelable(BUILD);
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
//		if(((AppCompatActivity) getActivity()).getActionBar() != null) ((AppCompatActivity) getActivity()).getSupportActionBar().set;
		val root = inflater.inflate(fragment_editor, container, false)
		goBackButton = root.findViewById(editorFragmentGoBackButton)
		// EditText
		nameEditText = root.findViewById(editorFrag_editText)
		if (currentBuild!!.name.isNotEmpty()) nameEditText!!.setText(currentBuild!!.name)
		nameEditText!!.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
			override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
			override fun afterTextChanged(s: Editable) {}
		}) // empty because it doesn't need to have a listener


		// go back button code
		goBackButton!!.setText(if (currentBuild!!.name != "ADD BUILD") "OVERWRITE" else "ADD")
		goBackButton!!.setOnClickListener(View.OnClickListener {
			// create the bundle
			val updatedBuild = currentBuild
			when (currentBuild!!.pcCase.toString()) {
				"White NZXT H510 ATX Mid Tower" -> updatedBuild!!.logo =
					getDrawable(
						resources, h510_elite_white, requireActivity().theme
					)
				"Black NZXT H510 ATX Mid Tower" -> updatedBuild!!.logo =
					getDrawable(
						resources, h510_elite_black, requireActivity().theme
					)
				"Black Corsair 4000D Airflow ATX Mid Tower" -> updatedBuild!!.logo =
					getDrawable(
						resources, a_4000d_airflow_black, requireActivity().theme
					)
				"Black Corsair 275R Airflow AIX Mid Tower" -> updatedBuild!!.logo =
					getDrawable(
						resources, a_275r_black, requireActivity().theme
					)
				"Black Lian Li PC-O11DX ATX Full Tower" -> updatedBuild!!.logo =
					getDrawable(
						resources, pc011dx_black, requireActivity().theme
					)
			}
			updatedBuild!!.name = nameEditText!!.getText().toString()
			val result = Bundle()
			result.putParcelable(BACK, updatedBuild)
			// destroy the lists
			cpuList!!.removeAt(0)
			gpuList!!.removeAt(0)
			memoryList!!.removeAt(0)
			motherboardList!!.removeAt(0)
			//			osList.remove(0);
//			psuList.remove(0);
			coolerList!!.removeAt(0)
			storageList!!.removeAt(0)
			//			monitorList.remove(0);
			caseList!!.removeAt(0)

			// switch fragments
			requireActivity()
				.supportFragmentManager
				.beginTransaction()
				.replace(
					(requireView().parent as ViewGroup).id,
					BuildFragment(updatedBuild),
					"findThisOtherFragment"
				)
				.addToBackStack(null)
				.commit()
		})
		//		myTextView = (TextView) root.findViewById(R.id.editorFragment_textView);
//		myTextView.setText(currentBuild.getName());

//		ArrayList<Part> customList = new ArrayList<Part>();

		// spinners!
		// TODO gray out the add/overwrite button if only the "Part" elements are selected instead of the subclass elements
		val caseSpinner = root.findViewById<Spinner>(editorFrag_caseSpinner)
		caseList = pcCases.get()
		caseList?.add(
			0,
			if (currentBuild.toString().trim { it <= ' ' }.isEmpty()) Part(
				"",
				""
			) else currentBuild!!.pcCase
		)
		caseSpinner.adapter = caseList?.let {
			PartsSpinnerAdapter(context, it)
		}
		caseSpinner.onItemSelectedListener = object : OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View,
				position: Int,
				id: Long
			) {
				if (caseList?.get(position) is Case) {
					currentBuild!!.pcCase = caseList?.get(position) as Case
					var newLogo: Drawable? = null
					when (caseList?.get(position)?.name?.uppercase(getDefault())) {
						"BLACK CORSAIR 275R AIRFLOW ATX MID TOWER" -> newLogo =
							getDrawable(
								resources, a_275r_black, resources.newTheme()
							)
						"WHITE NZXT H510 ATX MID TOWER" -> newLogo = getDrawable(
							resources, h510_elite_white, resources.newTheme()
						)
						"BLACK NZXT H510 ATX MID TOWER" -> newLogo = getDrawable(
							resources, h510_elite_black, resources.newTheme()
						)
						"BLACK CORSAIR 4000D AIRFLOW ATX MID TOWER" -> newLogo =
							getDrawable(
								resources, a_4000d_airflow_black, resources.newTheme()
							)
						"BLACK LIAN LI PC-O11DX ATX FULL TOWER" -> newLogo =
							getDrawable(
								resources, pc011dx_black, resources.newTheme()
							)
					}
					if (newLogo != null) currentBuild!!.logo = newLogo
				}
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {}
		}
		val coolerSpinner = root.findViewById<Spinner>(editorFrag_coolerSpinner)
		coolerList = coolers.get()
		coolerList?.add(
			0,
			if (currentBuild.toString().trim { it <= ' ' }.isEmpty()) Part(
				"",
				""
			) else currentBuild!!.cooler
		)
		coolerSpinner.adapter =
			(coolerList as ArrayList<Part>?)?.let { PartsSpinnerAdapter(context, it) }
		coolerSpinner.onItemSelectedListener = object : OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View,
				position: Int,
				id: Long
			) {
				if (coolerList?.get(position) is Cooler) currentBuild!!.cooler =
					coolerList?.get(position) as Cooler
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {}
		}
		val cpuSpinner = root.findViewById<Spinner>(editorFrag_cpuSpinner)
		cpuList = cpus.get()
		cpuList.add(
			0,
			if (currentBuild.toString().trim { it <= ' ' }.length == 0) Part(
				"",
				""
			) else currentBuild!!.cpu
		)
		cpuSpinner.adapter = PartsSpinnerAdapter(context, cpuList)
		cpuSpinner.onItemSelectedListener = object : OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View,
				position: Int,
				id: Long
			) {
				if (cpuList.get(position) is CPU) currentBuild!!.cpu = cpuList.get(position) as CPU
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {}
		}
		val gpuSpinner = root.findViewById<Spinner>(editorFrag_gpuSpinner)
		gpuList = gpus.get()
		gpuList.add(
			0,
			if (currentBuild.toString().trim { it <= ' ' }.length == 0) Part(
				"",
				""
			) else currentBuild!!.gpu
		)
		gpuSpinner.adapter = PartsSpinnerAdapter(context, gpuList)
		gpuSpinner.onItemSelectedListener = object : OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View,
				position: Int,
				id: Long
			) {
				if (gpuList.get(position) is GPU) currentBuild!!.gpu = gpuList.get(position) as GPU
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {}
		}
		val memorySpinner = root.findViewById<Spinner>(editorFrag_memorySpinner)
		memoryList = memory.get()
		memoryList.add(
			0,
			if (currentBuild.toString().trim { it <= ' ' }.length == 0) Part(
				"",
				""
			) else currentBuild!!.memory
		)
		memorySpinner.adapter = PartsSpinnerAdapter(context, memoryList)
		memorySpinner.onItemSelectedListener = object : OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View,
				position: Int,
				id: Long
			) {
				if (memoryList.get(position) is Memory) currentBuild!!.memory =
					memoryList.get(position) as Memory
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {}
		}
		val monitorSpinner = root.findViewById<Spinner>(editorFrag_monitorSpinner)
		monitorList = monitors.get()
		monitorList.add(
			0,
			if (currentBuild.toString().trim { it <= ' ' }.length == 0) Part(
				"",
				""
			) else currentBuild!!.monitor
		)
		monitorSpinner.adapter = PartsSpinnerAdapter(context, monitorList)
		monitorSpinner.onItemSelectedListener = object : OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View,
				position: Int,
				id: Long
			) {
				if (monitorList.get(position) is Monitor) currentBuild!!.monitor =
					monitorList.get(position) as Monitor
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {}
		}
		val motherboardSpinner = root.findViewById<Spinner>(editorFrag_motherboardSpinner)
		motherboardList = motherboards.get()
		motherboardList.add(
			0,
			if (currentBuild.toString().trim { it <= ' ' }.length == 0) Part(
				"",
				""
			) else currentBuild!!.motherboard
		)
		motherboardSpinner.adapter = PartsSpinnerAdapter(context, motherboardList)
		motherboardSpinner.onItemSelectedListener = object : OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View,
				position: Int,
				id: Long
			) {
				if (motherboardList.get(position) is Motherboard) currentBuild!!.motherboard =
					motherboardList.get(position) as Motherboard
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {}
		}
		val osSpinner = root.findViewById<Spinner>(editorFrag_osSpinner)
		osList = oss.get()
		osList.add(
			0,
			if (currentBuild.toString().trim { it <= ' ' }.length == 0) Part(
				"",
				""
			) else currentBuild!!.os
		)
		osSpinner.adapter = PartsSpinnerAdapter(context, osList)
		osSpinner.onItemSelectedListener = object : OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View,
				position: Int,
				id: Long
			) {
				if (osList.get(position) is OS) currentBuild!!.os = osList.get(position) as OS
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {}
		}
		val psuSpinner = root.findViewById<Spinner>(editorFrag_psuSpinner)
		psuList = psus.get()
		psuList.add(
			0,
			if (currentBuild.toString().trim { it <= ' ' }.length == 0) Part(
				"",
				""
			) else currentBuild!!.psu
		)
		psuSpinner.adapter = PartsSpinnerAdapter(context, psuList)
		psuSpinner.onItemSelectedListener = object : OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View,
				position: Int,
				id: Long
			) {
				if (psuList.get(position) is PSU) currentBuild!!.psu = psuList.get(position) as PSU
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {}
		}
		val storageSpinner = root.findViewById<Spinner>(editorFrag_storageSpinner)
		storageList = storage.get()
		storageList.add(
			0,
			if (currentBuild.toString().trim { it <= ' ' }.length == 0) Part(
				"",
				""
			) else currentBuild!!.storage
		)
		storageSpinner.adapter = PartsSpinnerAdapter(context, storageList)
		storageSpinner.onItemSelectedListener = object : OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View,
				position: Int,
				id: Long
			) {
				if (storageList.get(position) is Storage) currentBuild!!.storage =
					storageList.get(position) as Storage
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {}
		}
		return root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		//		mViewModel = new ViewModelProvider(this).get(EditorViewModel.class);
		// do stuff
		val finalResult = PCBuild()
		/* this should all go in a button that only activates once all fields are filled */
		val result = Bundle()
		result.putParcelable("bundleKey", finalResult)
		parentFragmentManager.setFragmentResult(BUILD, result)
		// TODO: Use the ViewModel
	}

	private fun openBuildFragment(build: PCBuild) {
		val fragment = BuildFragment.newInstance(currentBuild)
		parentFragmentManager.beginTransaction().setCustomAnimations(
			anim.enter_from_right,
			anim.exit_to_right,
			anim.enter_from_right,
			anim.exit_to_right
		).addToBackStack(null).add(nav_host_fragment_content_main, fragment, BUILD).commit()
	} //	@Override

	//	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
	//
	//	}
	//
	//	@Override
	//	public void onNothingSelected(AdapterView<?> parent) {
	//
	//	}
	companion object {
		const val BACK = "buildbackbetter"
		private const val BUILD = "pcbuild"

		@JvmStatic
		fun newInstance(build: PCBuild, isEditing: Boolean): EditorFragment {
			val editorFragment = EditorFragment(build, isEditing)
			val args = Bundle()
			args.putParcelable(BUILD, build)
			args.putBoolean(BUILD, isEditing)
			return editorFragment
		}
	}
}