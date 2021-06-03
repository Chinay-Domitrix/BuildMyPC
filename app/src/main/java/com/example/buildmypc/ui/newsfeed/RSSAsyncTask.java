package com.example.buildmypc.ui.newsfeed;

import android.os.AsyncTask;
import android.util.Xml;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

import static android.util.Log.d;
import static com.example.buildmypc.ui.newsfeed.NewsfeedFragment.finalArticleList;

class RSSAsyncTask extends AsyncTask<String, Void, ArrayList<Article>> {
	@Override
	protected ArrayList<Article> doInBackground(String... strings) {
		assert (strings != null) && (strings.length > 0);
		ArrayList<Article> usableArticleList = new ArrayList<>();
		// building a string by downloading off of le interwebz (c&p from WeatherApp)
		StringBuilder builder = new StringBuilder();
		Xml xmlObject = null;
		ArticleXMLParser xmlParser;
		JSONObject webpageObj = null;
		try {
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(strings[0]).openConnection().getInputStream()));
			while ((line = reader.readLine()) != null)
				builder.append(line); // the total file, once this runs, is converted into a string and is accessible via "builder.toString();"
		} catch (IOException e) {
			d("GAMING", "Stacking! + " + e.toString());
		}
		// parsing the XML result into something workable
		switch (strings[1].toUpperCase()) {
			case "TECHMEME":
				xmlParser = new ArticleXMLParser(builder.toString(), "Techmeme", "item", "title", "description", "pubDate", "link", "N/A");
				try { // try-catch handles both the URLMalformedException and the Parse-based-Exception
					usableArticleList.addAll(xmlParser.parseData());
				} catch (ParseException | MalformedURLException e) {
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
//				} catch (ParseException | MalformedURLException e) {
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
//				} catch (ParseException | MalformedURLException e) {
//					Log.d("CHIRAGERROR", e.toString());
//				}
//				break;
			case "THE VERGE":
				xmlParser = new ArticleXMLParser(builder.toString(), "The Vergecast", "entry", "title", "content", "published", "id", "N/A");
				try { // try-catch handles both the URLMalformedException and the Parse-based-Exception
					usableArticleList.addAll(xmlParser.parseData());
				} catch (ParseException | MalformedURLException e) {
					d("CHIRAGERROR", e.toString());
				}
				break;
			case "WIRED":
				xmlParser = new ArticleXMLParser(builder.toString(), "WIRED", "item", "title", "description", "pubDate", "link", "media:thumbnail");
				try { // try-catch handles both the URLMalformedException and the Parse-based-Exception
					usableArticleList.addAll(xmlParser.parseData());
				} catch (ParseException | MalformedURLException e) {
					d("CHIRAGERROR", e.toString());
				}
				break;
			case "NYTIMES":
				xmlParser = new ArticleXMLParser(builder.toString(), "NYT Technology", "item", "title", "description", "pubDate", "link", "media:content");
				try { // try-catch handles both the URLMalformedException and the Parse-based-Exception
					usableArticleList.addAll(xmlParser.parseData());
				} catch (ParseException | MalformedURLException e) {
					d("CHIRAGERROR", e.toString());
				}
				break;
//			case "ARS_TECHNICHA":
//				break;
			case "ENGADGET":
				xmlParser = new ArticleXMLParser(builder.toString(), "Engadget", "item", "title", "description", "pubDate", "link", "media:content");
				try { // try-catch handles both the URLMalformedException and the Parse-based-Exception
					usableArticleList.addAll(xmlParser.parseData());
				} catch (ParseException | MalformedURLException e) {
					d("CHIRAGERROR", e.toString());
				}
				break;
			default:
				break;
		}
		return usableArticleList;
	}

	@Override
	protected void onPostExecute(ArrayList<Article> articles) {
		super.onPostExecute(articles);
		finalArticleList.get().addAll(articles);
		d("SUCCESSTEXT", "yay");
	}
}
