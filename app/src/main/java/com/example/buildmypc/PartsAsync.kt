@file:Suppress("DEPRECATION")

package com.example.buildmypc

import android.Manifest.permission.INTERNET
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.widget.Toast
import com.example.buildmypc.MainActivity.parts
import com.example.buildmypc.R.string.firebase_key
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.net.URL
import java.util.*

class PartsAsync(private val mainActivity: MainActivity) {
	fun run() {
		CoroutineScope(Dispatchers.IO).launch {
			onPreExecute()
			try {
				val maxDownloadSize = 18533
			} catch (e: Exception) {
				withContext(Dispatchers.Main) {
					Toast.makeText(mainActivity, e.message, Toast.LENGTH_LONG).show()
				}
			}
		}
	}

	fun onPreExecute() {
		if (mainActivity.checkSelfPermission(INTERNET) != PERMISSION_GRANTED) mainActivity.requestPermissions(
			arrayOf(INTERNET),
			0
		)
	}

	fun doInBackground(vararg strings: Void): String {
		val x = Scanner(
			URL(
				"https://firebasestorage.googleapis.com/v0/b/buildmypc-ac8c3.appspot.com/o/part_data.json?alt=media&token=" + mainActivity.getString(
					firebase_key
				)
			).openConnection().getInputStream()
		)
		var y = ""
		while (x.hasNextLine()) y += x.nextLine()
		return y
	}

	fun onPostExecute(s: String) {
		try {
			parts.set(JSONObject(s))
		} catch (e: JSONException) {
			e.printStackTrace()
		}
	}
}