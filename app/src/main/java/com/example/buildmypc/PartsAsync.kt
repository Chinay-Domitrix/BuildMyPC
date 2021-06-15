@file:Suppress("DEPRECATION")

package com.example.buildmypc

import android.Manifest.permission.INTERNET
import android.content.pm.PackageManager.PERMISSION_GRANTED
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class PartsAsync(private val mainActivity: MainActivity) {
	fun run() {
		CoroutineScope(IO).launch {
			onPreExecute()

		}
	}

	private fun onPreExecute() {
		if (mainActivity.checkSelfPermission(INTERNET) != PERMISSION_GRANTED) mainActivity.requestPermissions(
			arrayOf(INTERNET),
			0
		)
	}
}