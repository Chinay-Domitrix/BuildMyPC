package com.example.buildmypc

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.buildmypc.R.id.*
import com.example.buildmypc.databinding.ActivityMainBinding
import com.example.buildmypc.databinding.ActivityMainBinding.inflate
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar.make

class MainActivity : AppCompatActivity() {
	private lateinit var appBarConfiguration: AppBarConfiguration
	private lateinit var binding: ActivityMainBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = inflate(layoutInflater)
		setContentView(binding.root)
		setSupportActionBar(binding.appBarMain.toolbar)
		binding.appBarMain.fab.setOnClickListener {
			make(
				it,
				"Replace with your own action",
				LENGTH_LONG
			).setAction("Action", null).show()
		}
		val drawerLayout = binding.drawerLayout
		val navView = binding.navView
		val navController = findNavController(nav_host_fragment_content_main)
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		appBarConfiguration =
			AppBarConfiguration(setOf(nav_home, nav_gallery, nav_slideshow), drawerLayout)
		setupActionBarWithNavController(navController, appBarConfiguration)
		navView.setupWithNavController(navController)
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.main, menu)
		return true
	}

	override fun onSupportNavigateUp(): Boolean {
		val navController = findNavController(nav_host_fragment_content_main)
		return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
	}
}