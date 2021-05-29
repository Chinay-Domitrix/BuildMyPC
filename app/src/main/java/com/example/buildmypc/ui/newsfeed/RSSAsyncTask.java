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
import java.util.ArrayList;

public class RSSAsyncTask extends AsyncTask<String, Void, ArrayList<Article>> {
	@Override
	protected ArrayList<Article> doInBackground(String... strings) {
		assert strings != null && strings.length > 0;
		URL url;
		String publisher = strings[1];
		// building a string by downloading off of le interwebz (c&p from WeatherApp)
		URLConnection connection;
		StringBuilder builder = new StringBuilder();
		Xml xmlObject = null;
		JSONObject webpageObj = null;
		try {
			url = new URL(strings[0]);
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

		// parsing the XML result into something workable
		switch (publisher.toUpperCase()) {
			case "TECHMEME":
				break;

			case "TECHCRUNCH":
				break;

			case "MIT_TECHNOLOGY":
				break;

			case "THE_VERGE":

				break;

			case "WIRED":
				break;

			case "NYTIMES":
				break;

			case "ARS_TECHNICHA":
				break;

			case "ENGADGET":
				break;

			default:
				break;
		}

		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<Article> articles) {
		super.onPostExecute(articles);
	}
}


