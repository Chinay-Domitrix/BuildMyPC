package com.example.buildmypc.ui.build

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat.getDrawable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.buildmypc.MainActivity.Companion.personalBuildList
import com.example.buildmypc.R.anim.enter_from_right
import com.example.buildmypc.R.anim.exit_to_right
import com.example.buildmypc.R.drawable.*
import com.example.buildmypc.R.id.*
import com.example.buildmypc.R.layout.row_item
import com.example.buildmypc.databinding.FragmentHomeBinding
import com.example.buildmypc.databinding.FragmentHomeBinding.inflate
import com.example.buildmypc.ui.build.BuildFragment.GridAdapter.GridHolder
import com.example.buildmypc.ui.currentBuild.EditorFragment
import com.example.buildmypc.ui.currentBuild.EditorFragment.Companion.newInstance
import com.example.buildmypc.ui.parts.parts.*
import java.util.*
import java.util.Arrays.stream
import kotlin.Int.Companion.MAX_VALUE

class BuildFragment(var currentEditedBuild: PCBuild?) : Fragment() {
	private lateinit var displayedBuilds: ArrayList<PCBuild>
	private var binding: FragmentHomeBinding? = null
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		parentFragmentManager.setFragmentResultListener(
			BUILD,
			this
		) { _: String?, result: Bundle -> currentEditedBuild = result.getParcelable(BUILD) }
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val basicAddBuild = PCBuild(
			"ADD BUILD",  // TODO institute a check for this name
			getDrawable(resources, plus_logo, resources.newTheme()),
			Case(" ", " ", " "),
			Cooler(" ", " "),
			CPU(" ", " "),
			GPU(" ", " "),
			Memory(" ", " "),
			Monitor(" ", " "),
			Motherboard(" ", " "),
			OS(" ", " "),
			PSU(" ", " "),
			Storage(" ", " "),
			ArrayList(),
			-11 // ID number for the specific entry
		)
//		if(((AppCompatActivity) getActivity()).getActionBar() != null)
//			((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
		binding = inflate(inflater, container, false)
		val root = binding!!.root
//		final TextView textView = binding.textHome;
//		buildViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
		// building myBuilds
		displayedBuilds = ArrayList()
		displayedBuilds.addAll(personalBuildList.get())
//		displayedBuilds.addAll(new Prebuilds().getPrebuiltList());
		if (currentEditedBuild != null) {
			// checks if currentEditedBuild is an edited version of an existing build
			// find the position of the build with this ID number and replace it
			if (displayedBuilds.size > 0 && isIncluded(
					currentEditedBuild!!.idNumber,
					idList(displayedBuilds)
				)
			) for (i in displayedBuilds.indices) {
				if (displayedBuilds[i].idNumber == currentEditedBuild!!.idNumber) {
					displayedBuilds[i] = currentEditedBuild!!
					break
				}
				if (i == displayedBuilds.size - 1) d(
					"TAG",
					"Error in isIncluded statement around line 91 -?> for loop didn't find any entries"
				)
			} else if (currentEditedBuild != basicAddBuild) displayedBuilds.add(
				currentEditedBuild!!
			)
			// check to remove all basicAddBuild objects from personalBuildList
			for (i in displayedBuilds.indices.reversed()) {
				val test = displayedBuilds[i]
				d("NAME", test.name.equals("ADD BUILD", ignoreCase = true).toString())
				if (test.name.equals("ADD BUILD", ignoreCase = true)) displayedBuilds.removeAt(i)
			}
			personalBuildList.set(displayedBuilds)
		}
		// adding the (ADD BUILD) entry
		displayedBuilds.add(
			PCBuild(
				"ADD BUILD",  // TODO institute a check for this name
				getDrawable(resources, plus_logo, resources.newTheme()),
				Case(" ", " ", " "),
				Cooler(" ", " "),
				CPU(" ", " "),
				GPU(" ", " "),
				Memory(" ", " "),
				Monitor(" ", " "),
				Motherboard(" ", " "),
				OS(" ", " "),
				PSU(" ", " "),
				Storage(" ", " "),
				ArrayList(),
				-11 // ID number for the specific entry
			)
		)
		val recyclerView = binding!!.buildFragRecyclerView
		val layoutManager = GridLayoutManager(activity, 3)
		recyclerView.setHasFixedSize(true)
		recyclerView.layoutManager = layoutManager
		val gridAdapter = GridAdapter(displayedBuilds)
		recyclerView.adapter = gridAdapter
		// this decouples the fragment | DO NOT TOUCH
//		if(MainActivity.personalBuildList.get().size() > 2 && MainActivity.personalBuildList.get().get(MainActivity.personalBuildList.get().size() - 1).getName().equals("ADD BUILD") && currentEditedBuild == null)
//			MainActivity.personalBuildList.get().remove(MainActivity.personalBuildList.get().size() - 1);
		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		binding = null
	}

	private fun openEditorFragment(currentBuild: PCBuild, isEditing: Boolean) {
		parentFragmentManager.beginTransaction()
			.setCustomAnimations(enter_from_right, exit_to_right, enter_from_right, exit_to_right)
			.addToBackStack(null)
			.add(nav_host_fragment_content_main, newInstance(currentBuild, isEditing), BUILD)
			.commit()
	}

	private fun idList(builds: ArrayList<PCBuild>): IntArray {
		if (builds.size == 0) throw IndexOutOfBoundsException() // throwing a wrench into the system | might make it throw an exception
		val result = IntArray(builds.size)
		for (i in builds.indices) result[i] = builds[i].idNumber
		return result
	}

	private fun isIncluded(testInt: Int, arr: IntArray): Boolean {
		return stream(arr).anyMatch { testInt == it }
	}

	private inner class GridAdapter(private val buildList: ArrayList<PCBuild>) :
		Adapter<GridHolder>() {
		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
			GridHolder(from(parent.context).inflate(row_item, parent))

		override fun onBindViewHolder(holder: GridHolder, position: Int) {
			val currentBuild = buildList[position]
			d("DEBUGSTR", "pos: $position")
			holder.image.setImageDrawable(currentBuild.logo)
			holder.text.text = currentBuild.name
			// setting the OnClickListener to start a new fragment while passing in the currentBuild object
			holder.image.setOnClickListener {
				// check if we're using the (+) button
				var isEditing = true
				if (buildList[position].idNumber == -11) {
					currentBuild.idNumber = Random().nextInt(MAX_VALUE) // id generator
					isEditing = false
				}
				// generating and stuffing the new fragment into the current view
				requireActivity().supportFragmentManager.beginTransaction().replace(
					(requireView().parent as ViewGroup).id,
					EditorFragment(currentBuild, true),
					"findThisFragment"
				).addToBackStack(null).commit()
			}
		}

		override fun getItemCount(): Int {
			return buildList.size
		}

		inner class GridHolder(itemView: View) : ViewHolder(itemView) {
			val image: ImageView = itemView.findViewById(gridView_imageView)
			val text: TextView = itemView.findViewById(gridView_textView)
		}
	}

	inner class Prebuilds {
		val prebuiltList = ArrayList(
			listOf(
				PCBuild(
					"Build 1",
					getDrawable(
						resources,
						h510_elite_black,
						resources.newTheme()
					),
					Case("H510 Elite Tower", "Corsair", "Black"),
					Cooler("Hyper 212 EVO", "Cooler Master"),
					CPU("Ryzen 5 3600", "AMD"),
					GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
					Memory("Vengeance LPX 16 GB", "Corsair"),
					Monitor("ROG Swift PG65UQ", "Asus"),
					Motherboard("B450 TOMAHAWK MAX", "MSI"),
					OS("Windows 10 Pro", "Microsoft"),
					PSU("RM750 (2019)", "Corsair"),
					Storage("Barracuda Compute 2 TB", "Seagate"),
					ArrayList(),
					-1
				),
				PCBuild(
					"Build 2",
					getDrawable(
						resources,
						h510_elite_white,
						resources.newTheme()
					),
					Case("H510 Elite Tower", "Corsair", "White"),
					Cooler("Hyper 212 EVO", "Cooler Master"),
					CPU("Ryzen 5 3600", "AMD"),
					GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
					Memory("Vengeance LPX 16 GB", "Corsair"),
					Monitor("ROG Swift PG65UQ", "Asus"),
					Motherboard("B450 TOMAHAWK MAX", "MSI"),
					OS("Windows 10 Pro", "Microsoft"),
					PSU("RM750 (2019)", "Corsair"),
					Storage("Barracuda Compute 2 TB", "Seagate"),
					ArrayList(),
					-2
				),
				PCBuild(
					"Build 3",
					getDrawable(
						resources,
						h510_elite_black,
						resources.newTheme()
					),
					Case("H510 Elite Tower", "Corsair", "Black"),
					Cooler("Hyper 212 EVO", "Cooler Master"),
					CPU("Ryzen 5 3600", "AMD"),
					GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
					Memory("Vengeance LPX 16 GB", "Corsair"),
					Monitor("ROG Swift PG65UQ", "Asus"),
					Motherboard("B450 TOMAHAWK MAX", "MSI"),
					OS("Windows 10 Pro", "Microsoft"),
					PSU("RM750 (2019)", "Corsair"),
					Storage("Barracuda Compute 2 TB", "Seagate"),
					ArrayList(),
					-3
				),
				PCBuild(
					"Build 4",
					getDrawable(
						resources,
						a_275r_black,
						resources.newTheme()
					),
					Case("275R Tower", "Corsair", "Black"),
					Cooler("Hyper 212 EVO", "Cooler Master"),
					CPU("Ryzen 5 3600", "AMD"),
					GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
					Memory("Vengeance LPX 16 GB", "Corsair"),
					Monitor("ROG Swift PG65UQ", "Asus"),
					Motherboard("B450 TOMAHAWK MAX", "MSI"),
					OS("Windows 10 Pro", "Microsoft"),
					PSU("RM750 (2019)", "Corsair"),
					Storage("Barracuda Compute 2 TB", "Seagate"),
					ArrayList(),
					-4
				),
				PCBuild(
					"Build 5",
					getDrawable(
						resources,
						a_4000d_airflow_black,
						resources.newTheme()
					),
					Case("4000D Airflow", "Corsair", "Black"),
					Cooler("Hyper 212 EVO", "Cooler Master"),
					CPU("Ryzen 5 3600", "AMD"),
					GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
					Memory("Vengeance LPX 16 GB", "Corsair"),
					Monitor("ROG Swift PG65UQ", "Asus"),
					Motherboard("B450 TOMAHAWK MAX", "MSI"),
					OS("Windows 10 Pro", "Microsoft"),
					PSU("RM750 (2019)", "Corsair"),
					Storage("Barracuda Compute 2 TB", "Seagate"),
					ArrayList(),
					-5
				),
				PCBuild(
					"Build 6",
					getDrawable(
						resources,
						h510_elite_black,
						resources.newTheme()
					),
					Case("H510 Elite Tower", "Corsair", "Black"),
					Cooler("Hyper 212 EVO", "Cooler Master"),
					CPU("Ryzen 5 3600", "AMD"),
					GPU("GeForce RTX 3070 Founders Edition", "NVIDIA"),
					Memory("Vengeance LPX 16 GB", "Corsair"),
					Monitor("ROG Swift PG65UQ", "Asus"),
					Motherboard("B450 TOMAHAWK MAX", "MSI"),
					OS("Windows 10 Pro", "Microsoft"),
					PSU("RM750 (2019)", "Corsair"),
					Storage("Barracuda Compute 2 TB", "Seagate"),
					ArrayList(),
					-6
				)
			)
		)

	}

	companion object {
		private const val BUILD = "pcbuild"
		fun newInstance(build: PCBuild?): BuildFragment {
			Bundle().putParcelable(BUILD, build)
			return BuildFragment(build)
		}
	}
}