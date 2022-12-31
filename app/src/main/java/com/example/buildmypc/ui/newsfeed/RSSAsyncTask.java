package com.example.buildmypc.ui.newsfeed;

/*
class RSSAsyncTask extends AsyncTask<String, Void, String> {
	public static final int START = 0, TITLE = 1, DESC = 2, LINK = 3, DATE = 4, AUTHOR = 5, END = 6;
	private final Drawable[] logos;
	private final NewsfeedFragment fragment;
	private String publisher;

	public RSSAsyncTask(Drawable[] logos, NewsfeedFragment fragment) {
		this.logos = logos;
		this.fragment = fragment;
		publisher = "";
	}

	@Override
	protected String doInBackground(@NotNull String... strings) {
		assert strings.length > 0;
		// building a string by downloading off of le interwebz (c&p from WeatherApp)
		StringBuilder builder = new StringBuilder();
		publisher = strings[1];
		try {
			Scanner reader = new Scanner(new InputStreamReader(new URL(strings[0]).openConnection().getInputStream()));
			while (reader.hasNextLine())
				builder.append(reader.nextLine()); // the total file, once this runs, is converted into a string and is accessible via "builder.toString();"
		} catch (IOException e) {
			d("RSSASYNCTASK", "Stacking! + " + e.toString());
		}
		// parsing the XML result into something workable
		ArrayList<Article> tempArticleList = new ArrayList<>();
		switch (strings[1].toUpperCase()) {
			case "THE VERGE":
				try { // try-catch handles both MalformedURLException and ParseException
					XmlPullParserFactory factory = newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser xpp = factory.newPullParser();
					xpp.setFeature(FEATURE_PROCESS_NAMESPACES, false);
					ArrayList<String> polishedRSS = new ArrayList<>();
					xpp.setInput(new StringReader(builder.toString())); // copy and paste this a bunch
					int eventType = xpp.getEventType();
					while (eventType != END_DOCUMENT) {
						if (xpp.getText() != null) polishedRSS.add(xpp.getText());
						eventType = xpp.next();
					}
					int searchStatus = START;
					String currentLine;
					int i = 0;
					Article tempArticle = new Article(null, null, null, null, null, null, null);
					while (i < polishedRSS.size()) {
						currentLine = polishedRSS.get(i);
						//tempArticle.setFormat(); // TODO this
						if (!currentLine.equals(polishedRSS.get(0))) switch (searchStatus) {
							case START -> {
								tempArticle = new Article(null, null, null, null, null, null, null); // out with the old and in with the new!
								tempArticle.setImage(logos[0]);
								String letters = currentLine.substring(0, 2);
								if (letters.equals("Th")) {
									i = 12;
									searchStatus = DATE;
								} else if (letters.equals("20")) searchStatus = DATE;
							}
							case LINK -> {
								tempArticle.setOriginURL(currentLine);
								i += 2;
								searchStatus = AUTHOR;
							}
							case AUTHOR -> {
								tempArticle.setAuthor(currentLine);
								searchStatus = END;
								i--;
							}
							case DATE -> {
								tempArticle.setDateStr(currentLine);
								char c = polishedRSS.get(i + 2).substring(0, 1).toCharArray()[0];
								if (isAlphabetic(c)) {
									searchStatus = TITLE;
									i++;
								}
							}
							case TITLE -> {
								tempArticle.setHeading(currentLine);
								i++;
								searchStatus = DESC;
							}
							case DESC -> {
								i += 2;
								searchStatus = LINK;
							}
							case END -> {
								tempArticleList.add(tempArticle);
								searchStatus = START;
							}
						}
						if ((i + 1) <= polishedRSS.size()) i++;
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
					XmlPullParserFactory factory = newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser xpp = factory.newPullParser();
					xpp.setFeature(FEATURE_PROCESS_NAMESPACES, false);
					ArrayList<String> polishedRSS = new ArrayList<>();
					xpp.setInput(new StringReader(builder.toString())); // copy and paste this a bunch
					int eventType = xpp.getEventType();
					while (eventType != END_DOCUMENT) {
						if (xpp.getText() != null) {
							polishedRSS.add(xpp.getText());
							d("RSSASYNC_XMLPARSER", "| " + xpp.getText());
						}
						eventType = xpp.next();
					}
					int searchStatus = START;
					String currentLine;
					int i = 5;
					Article tempArticle = new Article(null, null, null, null, null, null, null);
					while (i < polishedRSS.size()) {
						d("ARTICLE_LOOP", "i: " + i + ", State: " + searchStatus + "\nContent: " + polishedRSS.get(i));
						currentLine = polishedRSS.get(i);
						//tempArticle.setFormat(); // TODO this
						if (!currentLine.equals(polishedRSS.get(0))) switch (searchStatus) {
							case START -> {
								tempArticle = new Article(null, null, null, null, null, null, null); // out with the old and in with the new!
								tempArticle.setImage(logos[1]);
								if (((i + 1) < polishedRSS.size()) && isDigit(currentLine.charAt(5))) { // date checker
									i--;
									searchStatus = DATE;
								}
							}
							case DATE -> {
								tempArticle.setDateStr(currentLine); // either another date or the title comes after this
								if (polishedRSS.get(i + 1).equals("Condé Nast"))
									i += 2; // skips one line after every "Condé Nast" as to jump straight to the next important element
								searchStatus = TITLE; // always comes after first date entry
							}
							case TITLE -> {
								tempArticle.setHeading(currentLine);
								searchStatus = LINK;
							}
							case LINK -> {
								tempArticle.setOriginURL(currentLine);
								i += 2;
								searchStatus = DESC;
							}
							case DESC -> {
								tempArticle.setDesc(currentLine);
								i += 3;
								searchStatus = AUTHOR;
							}
							case AUTHOR -> {
								tempArticle.setAuthor(currentLine);
								searchStatus = END;
								i--;
							}
							case END -> {
								tempArticleList.add(tempArticle);
								searchStatus = START;
							}
						}
						if (i + 1 <= polishedRSS.size()) i++;
					}

				} catch (Exception e) {
					d("RSSASYNCTASK", e.getClass().getCanonicalName(), e);
				}
				break;
			case "NYTIMES":
				try { // try-catch handles both MalformedURLException and ParseException
					XmlPullParserFactory factory = newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser xpp = factory.newPullParser();
					xpp.setFeature(FEATURE_PROCESS_NAMESPACES, false);
					ArrayList<String> polishedRSS = new ArrayList<>();
					xpp.setInput(new StringReader(builder.toString())); // copy and paste this a bunch
					int eventType = xpp.getEventType();
					while (eventType != END_DOCUMENT) {
						if (xpp.getText() != null) {
							polishedRSS.add(xpp.getText());
							d("RSSASYNC_XMLPARSER", "| " + xpp.getText());
						}
						eventType = xpp.next();
					}
					int searchStatus = START;
					String currentLine;
					int i = 27; // start of actual articles
					Article tempArticle = new Article(null, null, null, null, null, null, null);
					while (i < polishedRSS.size()) {
						d("ARTICLE_LOOP", "i: " + i + ", State: " + searchStatus + "\nContent: " + polishedRSS.get(i));
						currentLine = polishedRSS.get(i);
						//tempArticle.setFormat(); // TODO this
						if (!currentLine.equals(polishedRSS.get(0))) switch (searchStatus) {
							case START -> {
								tempArticle = new Article(null, null, null, null, null, null, null); // out with the old and in with the new!
								tempArticle.setImage(logos[2]); // the third logo is the NYTimes one
								if (currentLine.startsWith("http")) {
									// first backtrack to the name entry
									i -= 3;
									searchStatus = TITLE;
								}
							}
							case TITLE -> {
								tempArticle.setHeading(currentLine);
								i += 3;
								searchStatus = LINK;
							}
							case LINK -> {
								tempArticle.setOriginURL(currentLine);
								i += 2;
								searchStatus = DESC;
							}
							case DESC -> {
								tempArticle.setDesc(currentLine);
								i++;
								searchStatus = AUTHOR;
							}
							case AUTHOR -> {
								tempArticle.setAuthor(currentLine);
								i++;
								searchStatus = DATE;
							}
							case DATE -> {
								tempArticle.setDateStr(currentLine); // either another date or the title comes after this
								int nextURLIndex = i;
								while (nextURLIndex < polishedRSS.size() && !polishedRSS.get(nextURLIndex).startsWith("http")) {
									nextURLIndex++;
									d("ARTICLETAG", polishedRSS.get(nextURLIndex));
								}
								i = nextURLIndex - 2;
								searchStatus = END;
							}
							case END -> {
								tempArticleList.add(tempArticle);
								searchStatus = START;
							}
						}
						if (i + 1 <= polishedRSS.size()) i++;
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
		return "done";
	}

	@Override
	protected void onPostExecute(String str) {
		super.onPostExecute(str);
		switch (publisher.toUpperCase()) {
			case "THE VERGE" -> {
				d("RSSASYNCTASK", "THE VERGE FINISHED");
				new RSSAsyncTask(logos, fragment).execute("https://www.wired.com/feed", "WIRED");
			}
			case "WIRED" -> {
				d("RSSASYNCTASK", "WIRED FINISHED");
				new RSSAsyncTask(logos, fragment).execute("https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml", "NYTimes");
			}
			case "NYTIMES" -> {
				((OnDataSendToActivity) fragment).refreshList();
				d("RSSASYNCTASK", "NYTIMES FINISHED");
			}
		}
		((OnDataSendToActivity) fragment).refreshList();
		d("RSSASYNCTASK", "did a thing");
	}
}*/
