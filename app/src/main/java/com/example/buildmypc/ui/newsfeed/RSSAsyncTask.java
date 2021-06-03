package com.example.buildmypc.ui.newsfeed;

import android.os.AsyncTask;
import android.util.Xml;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static android.util.Log.d;

class RSSAsyncTask extends AsyncTask<String, Void, ArrayList<Article>> {
	private String urls;

	public RSSAsyncTask() {

	}

	@Override
	protected ArrayList<Article> doInBackground(String... strings) {
		assert (strings != null) && (strings.length > 0);
		ArrayList<Article> usableArticleList = new ArrayList<>();
		URL url;
		String publisher = strings[1];
		// building a string by downloading off of le interwebz (c&p from WeatherApp)
		URLConnection connection;
		StringBuilder builder = new StringBuilder();
		Xml xmlObject = null;
		ArticleXMLParser xmlParser;
		JSONObject webpageObj = null;
		try {
			url = new URL(strings[0]);
			connection = url.openConnection();
			InputStream stream = connection.getInputStream();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			while ((line = reader.readLine()) != null)
				builder.append(line); // the total file, once this runs, is converted into a string and is accessible via "builder.toString();"
		} catch (Exception e) {
			d("GAMING", "Stacking! + " + e.toString());
		}
		// parsing the XML result into something workable
		switch (publisher.toUpperCase()) {
			case "TECHMEME":
				xmlParser = new ArticleXMLParser(builder.toString(), "Techmeme", "item", "title", "description", "pubDate", "link", "N/A");
				try { // try-catch handles both the URLMalformedException and the Parse-based-Exception
					usableArticleList.addAll(xmlParser.parseData());
				} catch (Exception e) {
					d("CHIRAGERROR", e.toString());
				}
				break;
//			case "TECHCRUNCH":
//				xmlParser = new ArticleXMLParser(
//						builder.toString(),
//						"TechCrunch",
//						"item",
//						"title",
//						"description",
//						"pubDate",
//						"link",
//						"N/A"
//				);
//				try { // try-catch handles both the URLMalformedException and the Parse-based-Exception
//					for(Article article : xmlParser.parseData()){
//						usableArticleList.add(article);
//					}
//				} catch (Exception e) {
//					Log.d("CHIRAGERROR", e.toString());
//				}
//				break;

//			case "MIT_TECHNOLOGY":
//				xmlParser = new ArticleXMLParser(
//						builder.toString(),
//						"MIT Technology Review",
//						"item",
//						"title",
//						"description",
//						"pubDate",
//						"link",
//						"N/A"
//				);
//				try { // try-catch handles both the URLMalformedException and the Parse-based-Exception
//					for(Article article : xmlParser.parseData()){
//						usableArticleList.add(article);
//					}
//				} catch (Exception e) {
//					Log.d("CHIRAGERROR", e.toString());
//				}
//				break;
			case "THE_VERGE":
				xmlParser = new ArticleXMLParser(builder.toString(), "The Vergecast", "entry", "title", "content", "published", "id", "N/A");
				try { // try-catch handles both the URLMalformedException and the Parse-based-Exception
					usableArticleList.addAll(xmlParser.parseData());
				} catch (Exception e) {
					d("CHIRAGERROR", e.toString());
				}
				break;
			case "WIRED":
				xmlParser = new ArticleXMLParser(builder.toString(), "WIRED", "item", "title", "description", "pubDate", "link", "media:thumbnail");
				try { // try-catch handles both the URLMalformedException and the Parse-based-Exception
					usableArticleList.addAll(xmlParser.parseData());
				} catch (Exception e) {
					d("CHIRAGERROR", e.toString());
				}
				break;
			case "NYTIMES":
				xmlParser = new ArticleXMLParser(builder.toString(), "NYT Technology", "item", "title", "description", "pubDate", "link", "media:content");
				try { // try-catch handles both the URLMalformedException and the Parse-based-Exception
					usableArticleList.addAll(xmlParser.parseData());
				} catch (Exception e) {
					d("CHIRAGERROR", e.toString());
				}
				break;
//			case "ARS_TECHNICHA":
//				break;
			case "ENGADGET":
				xmlParser = new ArticleXMLParser(builder.toString(), "Engadget", "item", "title", "description", "pubDate", "link", "media:content");
				try { // try-catch handles both the URLMalformedException and the Parse-based-Exception
					usableArticleList.addAll(xmlParser.parseData());
				} catch (Exception e) {
					d("CHIRAGERROR", e.toString());
				}
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


