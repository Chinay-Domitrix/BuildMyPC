package com.example.buildmypc.ui.newsfeed;

import android.os.AsyncTask;
import android.util.Xml;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static android.util.Log.d;
import static com.example.buildmypc.ui.newsfeed.NewsfeedFragment.finalArticleList;

class RSSAsyncTask extends AsyncTask<String, Void, ArrayList<Article>> {
	@Override
	protected ArrayList<Article> doInBackground(@NotNull String... strings) {
		assert strings.length > 0;
		ArrayList<Article> usableArticleList = new ArrayList<>();
		// building a string by downloading off of le interwebz (c&p from WeatherApp)
		StringBuilder builder = new StringBuilder();
		Xml xmlObject = null;
		ArticleXMLParser xmlParser;
		JSONObject webpageObj = null;

		try {
			Scanner reader = new Scanner(new InputStreamReader(new URL(strings[0]).openConnection().getInputStream()));
			while (reader.hasNextLine())
				builder.append(reader.nextLine()); // the total file, once this runs, is converted into a string and is accessible via "builder.toString();"
		} catch (IOException e) {
			d("GAMING", "Stacking! + " + e.toString());
		}

		// parsing the XML result into something workable
		switch (strings[1].toUpperCase()) {
			case "TECHMEME":
				xmlParser = new ArticleXMLParser(builder.toString(), "Techmeme", "item", "title", "description", "pubDate", "link", "N/A");
				try { // try-catch handles both MalformedURLException and ParseException
					usableArticleList.addAll(xmlParser.parseData());
				} catch (ParseException | MalformedURLException e) {
					d("CHIRAGERROR", e.getClass().getCanonicalName(), e);
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
//				try { // try-catch handles both MalformedURLException and ParseException
//					for(Article article : xmlParser.parseData()){
//						usableArticleList.add(article);
//					}
//				} catch (ParseException | MalformedURLException e) {
//					d("CHIRAGERROR", e.getClass().getCanonicalName(), e);
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
//				try { // try-catch handles both MalformedURLException and ParseException
//					for(Article article : xmlParser.parseData()){
//						usableArticleList.add(article);
//					}
//				} catch (ParseException | MalformedURLException e) {
//					d("CHIRAGERROR", e.getClass().getCanonicalName(), e);
//				}
//				break;
			case "THE VERGE":
				xmlParser = new ArticleXMLParser(builder.toString(), "The Vergecast", "entry", "title", "content", "published", "id", "N/A");
				try { // try-catch handles both MalformedURLException and ParseException
					usableArticleList.addAll(xmlParser.parseData());
				} catch (ParseException | MalformedURLException e) {
					d("CHIRAGERROR", e.getClass().getCanonicalName(), e);
				}
				break;
			case "WIRED":
				xmlParser = new ArticleXMLParser(builder.toString(), "WIRED", "item", "title", "description", "pubDate", "link", "media:thumbnail");
				try { /// try-catch handles both MalformedURLException and ParseException
					usableArticleList.addAll(xmlParser.parseData());
				} catch (ParseException | MalformedURLException e) {
					d("CHIRAGERROR", e.getClass().getCanonicalName(), e);
				}
				break;
			case "NYTIMES":
				xmlParser = new ArticleXMLParser(builder.toString(), "NYT Technology", "item", "title", "description", "pubDate", "link", "media:content");
				try { // try-catch handles both MalformedURLException and ParseException
					usableArticleList.addAll(xmlParser.parseData());
				} catch (ParseException | MalformedURLException e) {
					d("CHIRAGERROR", e.getClass().getCanonicalName(), e);
				}
				break;
//			case "ARS_TECHNICHA":
//				break;
			case "ENGADGET":
				xmlParser = new ArticleXMLParser(builder.toString(), "Engadget", "item", "title", "description", "pubDate", "link", "media:content");
				try { // try-catch handles both MalformedURLException and ParseException
					usableArticleList.addAll(xmlParser.parseData());
				} catch (ParseException | MalformedURLException e) {
					d("CHIRAGERROR", e.getClass().getCanonicalName(), e);
				}
				break;
			default:
				d("Article Exception", "Illegal Article", new IllegalStateException("Unexpected value: " + strings[1].toUpperCase()));
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