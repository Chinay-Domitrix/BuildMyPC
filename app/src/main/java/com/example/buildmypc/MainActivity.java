package com.example.buildmypc;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.buildmypc.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import static androidx.navigation.Navigation.findNavController;
import static androidx.navigation.ui.AppBarConfiguration.Builder;
import static androidx.navigation.ui.NavigationUI.navigateUp;
import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;
import static androidx.navigation.ui.NavigationUI.setupWithNavController;
import static com.example.buildmypc.R.id.nav_gallery;
import static com.example.buildmypc.R.id.nav_home;
import static com.example.buildmypc.R.id.nav_host_fragment_content_main;
import static com.example.buildmypc.R.id.nav_slideshow;
import static com.example.buildmypc.R.menu.main;
import static com.example.buildmypc.databinding.ActivityMainBinding.inflate;
import static com.google.android.material.snackbar.Snackbar.LENGTH_LONG;
import static com.google.android.material.snackbar.Snackbar.make;

public class MainActivity extends AppCompatActivity {
	private AppBarConfiguration mAppBarConfiguration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityMainBinding binding = inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		setSupportActionBar(binding.appBarMain.toolbar);
		binding.appBarMain.fab.setOnClickListener(view -> make(view, "Replace with your own action", LENGTH_LONG).setAction("Action", null).show());
		DrawerLayout drawer = binding.drawerLayout;
		NavigationView navigationView = binding.navView;
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top-level destinations.
		mAppBarConfiguration = new Builder(nav_home, nav_gallery, nav_slideshow).setOpenableLayout(drawer).build();
		NavController navController = findNavController(this, nav_host_fragment_content_main);
		setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		setupWithNavController(navigationView, navController);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(main, menu);
		return true;
	}

	@Override
	public boolean onSupportNavigateUp() {
		NavController navController = findNavController(this, nav_host_fragment_content_main);
		return navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
	}


}