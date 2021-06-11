package com.example.buildmypc;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.buildmypc.databinding.ActivityMainBinding;
import com.example.buildmypc.ui.build.PCBuild;
import com.example.buildmypc.ui.currentBuild.EditorFragment;
import com.example.buildmypc.ui.parts.parts.CPU;
import com.example.buildmypc.ui.parts.parts.Case;
import com.example.buildmypc.ui.parts.parts.Cooler;
import com.example.buildmypc.ui.parts.parts.GPU;
import com.example.buildmypc.ui.parts.parts.Memory;
import com.example.buildmypc.ui.parts.parts.Monitor;
import com.example.buildmypc.ui.parts.parts.Motherboard;
import com.example.buildmypc.ui.parts.parts.OS;
import com.example.buildmypc.ui.parts.parts.PSU;
import com.example.buildmypc.ui.parts.parts.Part;
import com.example.buildmypc.ui.parts.parts.Storage;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static androidx.navigation.Navigation.findNavController;
import static androidx.navigation.ui.AppBarConfiguration.Builder;
import static androidx.navigation.ui.NavigationUI.navigateUp;
import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;
import static androidx.navigation.ui.NavigationUI.setupWithNavController;
import static com.example.buildmypc.R.id.nav_gallery;
import static com.example.buildmypc.R.id.nav_home;
import static com.example.buildmypc.R.id.nav_host_fragment_content_main;
import static com.example.buildmypc.R.id.nav_newsfeed;
import static com.example.buildmypc.R.menu.main;
import static com.example.buildmypc.databinding.ActivityMainBinding.inflate;
import static com.google.android.material.snackbar.Snackbar.LENGTH_LONG;
import static com.google.android.material.snackbar.Snackbar.make;

public class MainActivity extends AppCompatActivity {
	public static final AtomicReference<JSONObject> parts = new AtomicReference<>();
//	public static final AtomicReference<FirebaseDatabase> database = new AtomicReference<>(getInstance());
	private AppBarConfiguration mAppBarConfiguration;
	public static final AtomicReference<ArrayList<CPU>> cpus = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Cooler>> coolers = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Motherboard>> motherboards = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Memory>> memory = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Storage>> storage = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<GPU>> gpus = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Case>> cases = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<PSU>> psus = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<OS>> oss = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Monitor>> monitors = new AtomicReference<>(new ArrayList<>());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityMainBinding binding = inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		setSupportActionBar(binding.appBarMain.toolbar);
		binding.appBarMain.fab.setOnClickListener(view -> make(view, "Replace with your own action", LENGTH_LONG).setAction("Action", null).show());
//		Passing each menu ID as a set of IDs because each menu should be considered as top-level destinations.
		mAppBarConfiguration = new Builder(nav_home, nav_gallery, nav_newsfeed).setOpenableLayout(binding.drawerLayout).build();
		NavController navController = findNavController(this, nav_host_fragment_content_main);
		setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		setupWithNavController(binding.navView, navController);
//		d("TAG", "onCreate: " + database.get().getReference("case").child("0").toString());
		// the code to parse the JSON parts file into usable stuff
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(main, menu);
		return true;
	}

	@Override
	public boolean onSupportNavigateUp() {
		return navigateUp(findNavController(this, nav_host_fragment_content_main), mAppBarConfiguration) || super.onSupportNavigateUp();
	}

	public ArrayList<Part> parsePartsJSON(JSONObject jsonObject) {
		// funny
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MainActivity that = (MainActivity) o;

		return Objects.equals(mAppBarConfiguration, that.mAppBarConfiguration);
	}

	@Override
	public int hashCode() {
		return mAppBarConfiguration != null ? mAppBarConfiguration.hashCode() : 0;
	}
}