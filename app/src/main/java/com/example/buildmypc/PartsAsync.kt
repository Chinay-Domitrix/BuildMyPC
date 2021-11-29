/*@file:Suppress("DEPRECATION")

package com.example.buildmypc

import android.Manifest.permission.INTERNET
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.example.buildmypc.MainActivity.parts
import com.example.buildmypc.MainActivity.storageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.File

class PartsAsync(private val mainActivity: MainActivity) {
	fun run() {
		CoroutineScope(IO).launch {
			onPreExecute()

			try {
				storageReference.get().child("part_data.json")
					.getFile(File("part_data.json"))
				Log.wtf("WTF","WTAF")
				withContext(Main) { parts.set(JSONObject(File("part_data.json").readText())) }
			} catch (e: Exception) {
				withContext(Main) { makeText(mainActivity, e.message, LENGTH_LONG).show() }
			}
		}
	}

	private fun onPreExecute() {
		if (mainActivity.checkSelfPermission(INTERNET) != PERMISSION_GRANTED) mainActivity.requestPermissions(
			arrayOf(INTERNET),
			0
		)
	}
}*/
