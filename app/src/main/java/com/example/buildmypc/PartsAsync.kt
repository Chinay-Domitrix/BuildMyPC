@file:Suppress("DEPRECATION")

package com.example.buildmypc

import android.Manifest.permission.INTERNET
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.AsyncTask
import com.example.buildmypc.MainActivity.parts
import com.example.buildmypc.R.string.firebase_key
import org.json.JSONException
import org.json.JSONObject
import java.net.URL

class PartsAsync(private val mainActivity: MainActivity) : AsyncTask<Void, Void, String>() {
	override fun onPreExecute() {
		super.onPreExecute()
		if (mainActivity.checkSelfPermission(INTERNET) != PERMISSION_GRANTED) mainActivity.requestPermissions(
			arrayOf(INTERNET),
			0
		)
	}

	override fun doInBackground(vararg strings: Void) =
		URL(
			"https://firebasestorage.googleapis.com/v0/b/buildmypc-ac8c3.appspot.com/o/part_data.json?alt=media&token=" + mainActivity.getString(
				firebase_key
			)
		).readText()

	override fun onPostExecute(s: String) {
		super.onPostExecute(s)
		try {
			parts.set(JSONObject(s))
		} catch (e: JSONException) {
			e.printStackTrace()
		}
	}
}