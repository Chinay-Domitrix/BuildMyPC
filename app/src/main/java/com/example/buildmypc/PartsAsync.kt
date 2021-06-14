package com.example.buildmypc

import android.Manifest.permission.INTERNET
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.AsyncTask
import com.example.buildmypc.MainActivity.parts
import com.example.buildmypc.R.string.firebase_key
import org.json.JSONException
import org.json.JSONObject
import java.net.URL

class PartsAsync(private val context: Context) : AsyncTask<Void, Void, String>() {
	override fun onPreExecute() {
		super.onPreExecute()
		if (context.checkSelfPermission(INTERNET) != PERMISSION_GRANTED) (context as Activity).requestPermissions(
			arrayOf(INTERNET),
			0
		)
	}

	override fun doInBackground(vararg strings: Void) =
		URL(
			"https://firebasestorage.googleapis.com/v0/b/buildmypc-ac8c3.appspot.com/o/part_data.json?alt=media&token=" + context.getString(
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