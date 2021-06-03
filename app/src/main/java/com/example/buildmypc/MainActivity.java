package com.example.buildmypc;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.buildmypc.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
	private AppBarConfiguration mAppBarConfiguration;
	public static AtomicReference<JSONObject> parts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityMainBinding binding = inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		setSupportActionBar(binding.appBarMain.toolbar);
		binding.appBarMain.fab.setOnClickListener(view -> make(view, "Replace with your own action", LENGTH_LONG).setAction("Action", null).show());
		// Passing each menu ID as a set of IDs because each menu should be considered as top-level destinations.
		mAppBarConfiguration = new Builder(nav_home, nav_gallery, nav_newsfeed).setOpenableLayout(binding.drawerLayout).build();
		NavController navController = findNavController(this, nav_host_fragment_content_main);
		setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		setupWithNavController(binding.navView, navController);
		try {
			StringBuilder jsonRaw = new StringBuilder();
			Scanner scanner = new Scanner(new File("part_data.json"));
			while (scanner.hasNextLine()) jsonRaw.append(scanner.nextLine().trim());
			parts.set(new JSONObject(jsonRaw.toString()));
		} catch (FileNotFoundException | JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(main, menu);
		return true;
	}

	@Override
	public boolean onSupportNavigateUp() {
		return navigateUp(findNavController(this, nav_host_fragment_content_main), mAppBarConfiguration) || super.onSupportNavigateUp();
	}
}