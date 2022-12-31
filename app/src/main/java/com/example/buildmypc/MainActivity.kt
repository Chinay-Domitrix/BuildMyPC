package com.example.buildmypc

import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.AppBarConfiguration.Builder
import androidx.navigation.ui.NavigationUI.*
import com.example.buildmypc.R.id.*
import com.example.buildmypc.R.menu.main
import com.example.buildmypc.R.string.parts_list
import com.example.buildmypc.databinding.ActivityMainBinding.inflate
import com.example.buildmypc.ui.build.PCBuild
import com.example.buildmypc.ui.parts.parts.Part
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar.make
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.atomic.AtomicReference

class MainActivity : AppCompatActivity() {
	private var mAppBarConfiguration: AppBarConfiguration? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding = inflate(layoutInflater)
		setContentView(binding.root)
		setSupportActionBar(binding.appBarMain.toolbar)
		binding.appBarMain.fab.setOnClickListener {
			make(
				it,
				"Replace with your own action",
				LENGTH_LONG
			).setAction("Action", null).show()
		}
		binding.appBarMain.fab.visibility = GONE
		//		Passing each menu ID as a set of IDs because each menu should be considered as top-level destinations.
		mAppBarConfiguration =
			Builder(nav_home, nav_parts, nav_newsfeed).setOpenableLayout(binding.drawerLayout)
				.build()
		val navController = findNavController(this, nav_host_fragment_content_main)
		setupActionBarWithNavController(this, navController, mAppBarConfiguration!!)
		setupWithNavController(binding.navView, navController)
		//		d("TAG", "onCreate: " + database.get().getReference("case").child("0").toString());
		// the code to parse the JSON parts file into usable stuff
		parts.set(JSONObject())
		d("PARSER", "MainActivity runs")
		if (parts.get().toString().length < 10) { // if there's nothing inside of it
			d("PARSER", "if-statement runs")
			/*ConnectivityManager cm = ((ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE));
			if (checkSelfPermission(ACCESS_NETWORK_STATE) != PERMISSION_GRANTED)
				requestPermissions(new String[]{ACCESS_NETWORK_STATE}, 0);
			NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
			if ((activeNetwork != null) && activeNetwork.isConnected() && !cm.isActiveNetworkMetered()) {
				d("PARSER", "second if-statement runs");
				newRequestQueue(this).add(new StringRequest(GET,
						"https://firebasestorage.googleapis.com/v0/b/buildmypc-ac8c3.appspot.com/o/part_data.json?alt=media&token=" + getString(firebase_key),
						response -> {
							try {
								Log.d("PARSER", "try-block runs");
								parts.set(new JSONObject(response));
								Log.d("PARSER", parts.toString().substring(0, 200));
							} catch (JSONException e) {
								e.printStackTrace();
							}
						},
						Throwable::printStackTrace));
				new PartsAsync(this).execute();
				new PartsAsync(this).run();
			} else */try {
				parts.set(JSONObject(getString(parts_list)))
				d("PARSER", "resulted in using the file")
			} catch (e: JSONException) {
				e.printStackTrace()
			}
			PartsJSONParse().start()
		}
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
//		Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(main, menu)
		return true
	}

	override fun onSupportNavigateUp() = navigateUp(
		findNavController(this, nav_host_fragment_content_main),
		mAppBarConfiguration!!
	) || super.onSupportNavigateUp()

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		return mAppBarConfiguration == (other as MainActivity).mAppBarConfiguration
	}

	override fun hashCode() =
		if (mAppBarConfiguration != null) mAppBarConfiguration.hashCode() else 0

	companion object {
		val parts = AtomicReference<JSONObject>()
		val cpus = AtomicReference(ArrayList<Part>())
		val coolers = AtomicReference(ArrayList<Part>())
		val motherboards = AtomicReference(ArrayList<Part>())
		val memory = AtomicReference(ArrayList<Part>())
		val storage = AtomicReference(ArrayList<Part>())
		val gpus = AtomicReference(ArrayList<Part>())
		val pcCases = AtomicReference(ArrayList<Part>())
		val psus = AtomicReference(ArrayList<Part>())
		val oss = AtomicReference(ArrayList<Part>())
		val monitors = AtomicReference(ArrayList<Part>())
		val personalBuildList = AtomicReference(ArrayList<PCBuild>())
	}
}