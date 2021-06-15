package com.example.buildmypc;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.buildmypc.databinding.ActivityMainBinding;
import com.example.buildmypc.ui.build.PCBuild;
import com.example.buildmypc.ui.parts.parts.Part;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static android.util.Log.d;
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
import static com.example.buildmypc.R.string.parts_list;
import static com.example.buildmypc.databinding.ActivityMainBinding.inflate;
import static com.google.android.material.snackbar.Snackbar.LENGTH_LONG;
import static com.google.android.material.snackbar.Snackbar.make;

public class MainActivity extends AppCompatActivity {
	public static final AtomicReference<JSONObject> parts = new AtomicReference<>();
	public static final AtomicReference<ArrayList<Part>> cpus = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Part>> coolers = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Part>> motherboards = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Part>> memory = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Part>> storage = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Part>> gpus = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Part>> pcCases = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Part>> psus = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Part>> oss = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<ArrayList<Part>> monitors = new AtomicReference<>(new ArrayList<>());
	public static final AtomicReference<StorageReference> storageReference = new AtomicReference<>(FirebaseStorage.getInstance().getReference());
	public static final AtomicReference<ArrayList<PCBuild>> personalBuildList = new AtomicReference<>(new ArrayList<>());
	private AppBarConfiguration mAppBarConfiguration;

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
//		The code to parse the JSON parts data into usable stuff
		parts.set(new JSONObject());
		d("PARSER", "MainActivity runs");
		if (parts.get().toString().length() < 10) { // if there's nothing inside of it
			d("PARSER", "if-statement runs");
			try {
				parts.set(new JSONObject(getString(parts_list)));
				d("PARSER", "resulted in using the file");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			new PartsJSONParse().start();
		}
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