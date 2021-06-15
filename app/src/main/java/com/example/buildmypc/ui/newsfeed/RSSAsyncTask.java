package com.example.buildmypc.ui.newsfeed;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import androidx.fragment.app.FragmentActivity;

import com.example.buildmypc.MainActivity;
import com.example.buildmypc.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static android.util.Log.d;
import static androidx.core.content.res.ResourcesCompat.getDrawable;
import static com.example.buildmypc.R.drawable.nytimes;
import static com.example.buildmypc.R.drawable.wired;
import static com.example.buildmypc.ui.newsfeed.NewsfeedFragment.finalArticleList;

class RSSAsyncTask extends AsyncTask<String, Void, String> {

	public static final int START = 0;
	public static final int TITLE = 1;
	public static final int DESC = 2;
	public static final int LINK = 3;
	public static final int DATE = 4;
	public static final int AUTHOR = 5;
	public static final int END = 6;

	private Drawable[] logos;
	private NewsfeedFragment fragment;
	private String publisher;

	public RSSAsyncTask(Drawable[] logos, NewsfeedFragment fragment) {
		this.logos = logos;
		this.fragment = fragment;
		publisher = "";
	}



	@Override
	protected String doInBackground(@NotNull String... strings) {
		assert strings.length > 0;
		ArrayList<Article> usableArticleList = new ArrayList<>();
		// building a string by downloading off of le interwebz (c&p from WeatherApp)
		StringBuilder builder = new StringBuilder();
		Xml xmlObject = null;
//		ArticleXMLParser xmlParser;
		JSONObject webpageObj = null;

		publisher = strings[1];

		try {
			Scanner reader = new Scanner(new InputStreamReader(new URL(strings[0]).openConnection().getInputStream()));
			while (reader.hasNextLine()) {
				String str = reader.nextLine();
				builder.append(str); // the total file, once this runs, is converted into a string and is accessible via "builder.toString();"
//				Log.d("RSSASYNCTASK", "" + str);
			}
		} catch (IOException e) {
			d("RSSASYNCTASK", "Stacking! + " + e.toString());
		}


		// parsing the XML result into something workable
		ArrayList<Article> tempArticleList = new ArrayList<>();
		switch (strings[1].toUpperCase()) {
			case "THE VERGE":
				try { // try-catch handles both MalformedURLException and ParseException
					tempArticleList.clear();
					XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser xpp = factory.newPullParser();
					xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);

					ArrayList<String> polishedRSS = new ArrayList<String>();
					xpp.setInput(new StringReader(builder.toString())); // copy and paste this a bunch
					int eventType = xpp.getEventType();
					while(eventType != XmlPullParser.END_DOCUMENT){
						if(xpp.getText() != null){
							polishedRSS.add(xpp.getText());
//							Log.d("RSSASYNC_XMLPARSER", "| " + xpp.getText());
						}
						eventType = xpp.next();
					}

					int searchStatus = START;
					String currentLine = "";
					int i = 0;
					Article tempArticle = new Article();
					while(i < polishedRSS.size()) {
//						Log.d("ARTICLE_LOOP", "i: " + i + ", State: " +  searchStatus + "\nContent: " + polishedRSS.get(i));
						currentLine = polishedRSS.get(i);
						//tempArticle.setFormat(); // TODO this
						if (!currentLine.equals(polishedRSS.get(0))) {
							switch (searchStatus) {
								case START: // 0
									tempArticle = new Article(); // out with the old and in with the new!
									tempArticle.setImage(logos[0]);
									String letters = currentLine.substring(0, 2);
									if (letters.equals("Th")) {
										i = 12;
										searchStatus = DATE;
									}
									else if(letters.equals("20")) {
										searchStatus = DATE;
									}
//									else if(letters.equals("ht")){
//										searchStatus = LINK;
//									}
									break;

								case LINK: // 3
									tempArticle.setOriginURL(currentLine);
									i+=2;
									searchStatus = AUTHOR;
									break;

								case AUTHOR: // 5
									tempArticle.setAuthor(currentLine);
									searchStatus = END;
									i--;
									break;

								case DATE: // 4
									tempArticle.setDateStr(currentLine); // either another date or the title comes after this
									char c = polishedRSS.get(i+2).substring(0, 1).toCharArray()[0];
									if(Character.isAlphabetic(c)) { // is a letter
										searchStatus = TITLE;
										i++;
									}
									break;

								case TITLE: // 1
									// is a title
									tempArticle.setHeading(currentLine);
									i++;
									searchStatus = DESC;
									break;

								case DESC: // 2 TODO either remove or fix
									i+=2;
									searchStatus = LINK;
									break;

								case END: // 6
									tempArticleList.add(tempArticle);
									searchStatus = START;
									break;
							}
						}
						if(i + 1 <= polishedRSS.size()) i++;
					}

				} catch (Exception e) {
					d("RSSASYNCTASK", e.getClass().getCanonicalName(), e);
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
			case "WIRED":
				try { // try-catch handles both MalformedURLException and ParseException
					tempArticleList.clear();
					XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser xpp = factory.newPullParser();
					xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);

					ArrayList<String> polishedRSS = new ArrayList<String>();
					xpp.setInput(new StringReader(builder.toString())); // copy and paste this a bunch
					int eventType = xpp.getEventType();
					while(eventType != XmlPullParser.END_DOCUMENT){
						if(xpp.getText() != null){
							polishedRSS.add(xpp.getText());
							Log.d("RSSASYNC_XMLPARSER", "| " + xpp.getText());
						}
						eventType = xpp.next();
					}

					int searchStatus = START;
					String currentLine = "";
					int i = 5;
					Article tempArticle = new Article();
					while(i < polishedRSS.size()) {
						Log.d("ARTICLE_LOOP", "i: " + i + ", State: " +  searchStatus + "\nContent: " + polishedRSS.get(i));
						currentLine = polishedRSS.get(i);
						//tempArticle.setFormat(); // TODO this
						if (!currentLine.equals(polishedRSS.get(0))) {
							switch (searchStatus) {
								case START: // 0
									tempArticle = new Article(); // out with the old and in with the new!
									tempArticle.setImage(logos[1]);
									if(i + 1 < polishedRSS.size() && Character.isDigit(currentLine.charAt(5))){ // date checker
										i--;
										searchStatus = DATE;
									}

									break;

								case DATE: // 4
									tempArticle.setDateStr(currentLine); // either another date or the title comes after this
									if(polishedRSS.get(i+1).equals("Condé Nast")) i+=2; // skips one line after every "Condé Nast" as to jump straight to the next important element
									searchStatus = TITLE; // always comes after first date entry
									break;

								case TITLE: // 1
									tempArticle.setHeading(currentLine);
									searchStatus = LINK;
									break;

								case LINK: // 3
									tempArticle.setOriginURL(currentLine);
									// skip 2 lines here so the iteration brings it to 3
									i+=2;
									searchStatus = DESC;
									break;

								case DESC:
									tempArticle.setDesc(currentLine);
									// skip 3 lines so the iteration brings it to 4
									i+=3;
									searchStatus = AUTHOR;
									break;

								case AUTHOR: // 5
									tempArticle.setAuthor(currentLine);
									searchStatus = END;
									i--;
									break;

								case END: // 6
									tempArticleList.add(tempArticle);
									searchStatus = START;
									break;
							}
						}
						if(i + 1 <= polishedRSS.size()) i++;
					}

				} catch (Exception e) {
					d("RSSASYNCTASK", e.getClass().getCanonicalName(), e);
				}
				break;
			case "NYTIMES":
				try { // try-catch handles both MalformedURLException and ParseException
					tempArticleList.clear();
					XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser xpp = factory.newPullParser();
					xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);

					ArrayList<String> polishedRSS = new ArrayList<String>();
					xpp.setInput(new StringReader(builder.toString())); // copy and paste this a bunch
					int eventType = xpp.getEventType();
					while(eventType != XmlPullParser.END_DOCUMENT){
						if(xpp.getText() != null){
							polishedRSS.add(xpp.getText());
							Log.d("RSSASYNC_XMLPARSER", "| " + xpp.getText());
						}
						eventType = xpp.next();
					}

					/* General strategy:
					 * - search for the first URL entry for a new article
					 * - backtrack to the article title
					 * - skip to the second URL entry
					 * - work from there
					 */


					int searchStatus = START;
					String currentLine = "";
					int i = 27; // start of actual articles
					Article tempArticle = new Article();
					while(i < polishedRSS.size()) {
						Log.d( "ARTICLE_LOOP", "i: " + i + ", State: " +  searchStatus + "\nContent: " + polishedRSS.get(i));
						currentLine = polishedRSS.get(i);
						//tempArticle.setFormat(); // TODO this
						if (!currentLine.equals(polishedRSS.get(0))) {
							switch (searchStatus) {
								case START: // 0
									tempArticle = new Article(); // out with the old and in with the new!
									tempArticle.setImage(logos[2]); // third logo is the NYTimes one

									// located at first URL
									if(currentLine.startsWith("http")) {
										// first backtrack to the name entry
										i-=3;
										searchStatus = TITLE;
									}
									break;

								case TITLE: // 1
									tempArticle.setHeading(currentLine);
									// jump to second instance of the URL
									i+=3;
									searchStatus = LINK;
									break;

								case LINK: // 3
									tempArticle.setOriginURL(currentLine);
									// jump to description
									i+=2;
									searchStatus = DESC;
									break;

								case DESC:
									tempArticle.setDesc(currentLine);
									// jump to authors
									i++;
									searchStatus = AUTHOR;
									break;

								case AUTHOR: // 5
									tempArticle.setAuthor(currentLine);
									// jump to date
									i++;
									searchStatus = DATE;
									break;

								case DATE: // 4
									tempArticle.setDateStr(currentLine); // either another date or the title comes after this
									int nextURLIndex = i;
									while(nextURLIndex < polishedRSS.size() && !polishedRSS.get(nextURLIndex).startsWith("http")){
										nextURLIndex++;
										Log.d("ARTICLETAG", polishedRSS.get(nextURLIndex));
									}
									i = nextURLIndex - 2;
									searchStatus = END;
									break;

								case END: // 6
									tempArticleList.add(tempArticle);
									searchStatus = START;
									break;
							}
						}
						if(i + 1 <= polishedRSS.size()) i++;
					}

				} catch (Exception e) {
					d("RSSASYNCTASK", e.getClass().getCanonicalName(), e);
				}
				break;
//			case "ARS_TECHNICHA":
//				break;
			case "ENGADGET":
//				xmlParser = new ArticleXMLParser(builder.toString(), "Engadget", "item", "title", "description", "pubDate", "link", "media:content");
				try { // try-catch handles both MalformedURLException and ParseException
				} catch (Exception e) {
					d("RSSASYNCTASK", e.getClass().getCanonicalName(), e);
				}
				break;
			default:
				d("RSSASYNCTASK", "Illegal Article ", new IllegalStateException(" Unexpected value: " + strings[1].toUpperCase()));
				break;
		}
		ArrayList<Article> tempList = finalArticleList.get();
		tempList.addAll(tempArticleList);
		finalArticleList.set(tempList);

//		for(Article article : tempArticleList){
//			Log.d("ARTICLELIST", "article: " + article.toString());
//		}

		return "done";
	}

	@Override
	protected void onPostExecute(String str) {
		super.onPostExecute(str);

		// switch ensures successive starts of threads
		switch(publisher.toUpperCase()){
			case "THE VERGE":
				d("RSSASYNCTASK", "THE VERGE FINISHED");
				new RSSAsyncTask(logos, fragment).execute("https://www.wired.com/feed", "WIRED");
				break;

			case "WIRED":
				d("RSSASYNCTASK", "WIRED FINISHED");
				new RSSAsyncTask(logos, fragment).execute("https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml", "NYTimes");
				break;

			case "NYTIMES":
				((Article.OnDataSendToActivity)(fragment)).refreshList();
				d("RSSASYNCTASK", "NYTIMES FINISHED");
				break;

//			case "ENGADGET": // update the main UI
//				((Article.OnDataSendToActivity)(fragment)).refreshList();
//				d("RSSASYNCTASK", "ENGADGET FINISHED");
//				break;

		}
		((Article.OnDataSendToActivity)(fragment)).refreshList();
		d("RSSASYNCTASK", "did a thing");
	}
}