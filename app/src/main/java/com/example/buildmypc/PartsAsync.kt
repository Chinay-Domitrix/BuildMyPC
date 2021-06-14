package com.example.buildmypc

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.util.Log.*
import com.example.buildmypc.MainActivity.parts
import com.example.buildmypc.R.string.firebase_key
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import java.util.*

class PartsAsync(private val context: Context) : AsyncTask<Void, Void, String>() {
	override fun doInBackground(vararg strings: Void): String {
		val x = StringBuilder()
		try {
			val scanner = Scanner(
				URL(
					"https://firebasestorage.googleapis.com/v0/b/buildmypc-ac8c3.appspot.com/o/part_data.json?alt=media&token=" + context.getString(
						firebase_key
					)
				).openConnection().getInputStream()
			)
			while (scanner.hasNextLine()) x.append(scanner.nextLine())
		} catch (e: IOException) {
			d("Parts Exception", e.javaClass.simpleName, e)
		}
		return x.toString()
	}

	override fun onPostExecute(s: String) {
		super.onPostExecute(s)
		try {
			parts.set(JSONObject(s))
		} catch (e: JSONException) {
			e.printStackTrace()
		}
	}
}