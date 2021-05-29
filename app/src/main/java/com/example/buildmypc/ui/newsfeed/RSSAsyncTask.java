package com.example.buildmypc.ui.newsfeed;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class RSSAsyncTask extends AsyncTask<String, Void, Article> {
	@Override
	protected Article doInBackground(String... urlStrs) {
		// building the string
		URLConnection connection;

		StringBuilder builder = new StringBuilder();
		Xml xmlObject = null;
		JSONObject webpageObj = null;
		try {
			URL url = new URL(urlStrs[0]);
			connection = url.openConnection();
			InputStream stream = connection.getInputStream();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}

		} catch (Exception e) {
			Log.d("GAMING", "Stacking! + " + e.toString());
		}
		return null;
	}

	@Override
	protected void onPostExecute(Article article) {
		super.onPostExecute(article);
	}
}
