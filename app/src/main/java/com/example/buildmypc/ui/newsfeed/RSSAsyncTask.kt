package com.example.buildmypc.ui.newsfeed

import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.util.Log.d
import com.example.buildmypc.ui.newsfeed.Article.OnDataSendToActivity
import com.example.buildmypc.ui.newsfeed.NewsfeedFragment.Companion.finalArticleList
import org.xmlpull.v1.XmlPullParser.END_DOCUMENT
import org.xmlpull.v1.XmlPullParser.FEATURE_PROCESS_NAMESPACES
import org.xmlpull.v1.XmlPullParserFactory.newInstance
import java.io.IOException
import java.io.InputStreamReader
import java.io.StringReader
import java.lang.Character.isAlphabetic
import java.lang.Character.isDigit
import java.net.URL
import java.util.*
import java.util.Locale.getDefault

class RSSAsyncTask(
	private var logos: Array<Drawable>,
	private var fragment: NewsfeedFragment,
	private var publisher: String = ""
) : AsyncTask<String, Void, String>() {
	override fun doInBackground(vararg strings: String): String {
		assert(strings.isNotEmpty())
		// building a string by downloading off of le interwebz (c&p from WeatherApp)
		val builder = StringBuilder()
		publisher = strings[1]
		try {
			val reader =
				Scanner(InputStreamReader(URL(strings[0]).openConnection().getInputStream()))
			while (reader.hasNextLine()) builder.append(reader.nextLine()) // the total file, once this runs, is converted into a string and is accessible via "builder.toString();"
		} catch (e: IOException) {
			d("RSSASYNCTASK", "Stacking! + $e")
		}
		// parsing the XML result into something workable
		val tempArticleList = ArrayList<Article>()
		when (strings[1].uppercase(getDefault())) {
			"THE VERGE" -> {
				try { // try-catch handles both MalformedURLException and ParseException
					val factory = newInstance()
					factory.isNamespaceAware = true
					val xpp = factory.newPullParser()
					xpp.setFeature(FEATURE_PROCESS_NAMESPACES, false)
					val polishedRSS = ArrayList<String>()
					xpp.setInput(StringReader(builder.toString())) // copy and paste this a bunch

					var eventType = xpp.eventType
					while (eventType != END_DOCUMENT) {
						if (xpp.text != null) polishedRSS.add(xpp.text)
						eventType = xpp.next()
					}
					var searchStatus: Int = START
					var currentLine: String
					var i = 0
					var tempArticle = Article(null, null, null, null, null, null, null)
					while (i < polishedRSS.size) {
						currentLine = polishedRSS[i]
						//tempArticle.setFormat(); // TODO this
						if (currentLine != polishedRSS[0]) when (searchStatus) {
							START -> {
								tempArticle = Article(
									null,
									null,
									null,
									null,
									null,
									null,
									null
								) // out with the old and in with the new!
								tempArticle.image = logos[0]
								val letters = currentLine.substring(0, 2)
								if (letters == "Th") {
									i = 12
									searchStatus = DATE
								} else if (letters == "20") searchStatus = DATE
							}
							LINK -> {
								tempArticle.originURL = currentLine
								i += 2
								searchStatus = AUTHOR
							}
							AUTHOR -> {
								tempArticle.author = currentLine
								searchStatus = END
								i--
							}
							DATE -> {
								tempArticle.dateStr = currentLine
								if (isAlphabetic(
										polishedRSS[i + 2].substring(0, 1).toCharArray()[0].code
									)
								) {
									searchStatus = TITLE
									i++
								}
							}
							TITLE -> {
								tempArticle.heading = currentLine
								i++
								searchStatus = DESC
							}
							DESC -> {
								i += 2
								searchStatus = LINK
							}
							END -> {
								tempArticleList.add(tempArticle)
								searchStatus = START
							}
						}
						if (i + 1 <= polishedRSS.size) i++
					}
				} catch (e: Exception) {
					d("RSSASYNCTASK", e.javaClass.canonicalName, e)
				}
			}
			"WIRED" -> {
				try {
					val factory = newInstance()
					factory.isNamespaceAware = true
					val xpp = factory.newPullParser()
					xpp.setFeature(FEATURE_PROCESS_NAMESPACES, false)
					val polishedRSS: ArrayList<String> = ArrayList()
					xpp.setInput(StringReader(builder.toString())) // copy and paste this a bunch
					var eventType = xpp.eventType
					while (eventType != END_DOCUMENT) {
						if (xpp.text != null) {
							polishedRSS.add(xpp.text)
							d("RSSASYNC_XMLPARSER", "| " + xpp.text)
						}
						eventType = xpp.next()
					}
					var searchStatus = START
					var currentLine: String
					var i = 5
					var tempArticle = Article(null, null, null, null, null, null, null)
					while (i < polishedRSS.size) {
						d(
							"ARTICLE_LOOP",
							"i: $i, State: $searchStatus\nContent: ${polishedRSS[i]}"
						)
						currentLine = polishedRSS[i]
						//tempArticle.setFormat(); // TODO this
						if (currentLine != polishedRSS[0]) when (searchStatus) {
							START -> {
								tempArticle = Article(
									null,
									null,
									null,
									null,
									null,
									null,
									null
								) // out with the old and in with the new!
								tempArticle.image = logos[1]
								if (i + 1 < polishedRSS.size && isDigit(currentLine[5])) { // date checker
									i--
									searchStatus = DATE
								}
							}
							DATE -> {
								tempArticle.dateStr =
									currentLine // either another date or the title comes after this
								if (polishedRSS[i + 1] == "Condé Nast") i += 2 // skips one line after every "Condé Nast" as to jump straight to the next important element
								searchStatus = TITLE // always comes after first date entry
							}
							TITLE -> {
								tempArticle.heading = currentLine
								searchStatus = LINK
							}
							LINK -> {
								tempArticle.originURL = currentLine
								i += 2
								searchStatus = DESC
							}
							DESC -> {
								tempArticle.desc = currentLine
								i += 3
								searchStatus = AUTHOR
							}
							AUTHOR -> {
								tempArticle.author = currentLine
								searchStatus = END
								i--
							}
							END -> {
								tempArticleList.add(tempArticle)
								searchStatus = START
							}
						}
						if (i + 1 <= polishedRSS.size) i++
					}
				} catch (e: Exception) {
					d("RSSASYNCTASK", e.javaClass.canonicalName, e)
				}
			}
			"NYTIMES" -> try {
				val factory = newInstance()
				factory.isNamespaceAware = true
				val xpp = factory.newPullParser()
				xpp.setFeature(FEATURE_PROCESS_NAMESPACES, false)
				val polishedRSS = ArrayList<String>()
				xpp.setInput(StringReader(builder.toString())) // copy and paste this a bunch
				var eventType = xpp.eventType
				while (eventType != END_DOCUMENT) {
					if (xpp.text != null) {
						polishedRSS.add(xpp.text)
						d("RSSASYNC_XMLPARSER", "| " + xpp.text)
					}
					eventType = xpp.next()
				}
				var searchStatus = START
				var currentLine: String
				var i = 5
				var tempArticle = Article(null, null, null, null, null, null, null)
				while (i < polishedRSS.size) {
					d(
						"ARTICLE_LOOP",
						" i: $i, State: $searchStatus\nContent: ${polishedRSS[i]}".trimIndent()
					)
					currentLine = polishedRSS[i]
					//tempArticle.setFormat(); // TODO this
					if (currentLine != polishedRSS[0]) when (searchStatus) {
						START -> {
							tempArticle = Article(
								null,
								null,
								null,
								null,
								null,
								null,
								null
							) // out with the old and in with the new!
							tempArticle.image = logos[2] // the third logo is the NYTimes one
							if (currentLine.startsWith("http")) {
								// first backtrack to the name entry
								i -= 3
								searchStatus = TITLE
							}
						}
						TITLE -> {
							tempArticle.heading = currentLine
							i += 3
							searchStatus = LINK
						}
						LINK -> {
							tempArticle.originURL = currentLine
							i += 2
							searchStatus = DESC
						}
						DESC -> {
							tempArticle.desc = currentLine
							i++
							searchStatus = AUTHOR
						}
						AUTHOR -> {
							tempArticle.author = currentLine
							i++
							searchStatus = DATE
						}
						DATE -> {
							tempArticle.dateStr =
								currentLine // either another date or the title comes after this
							var nextURLIndex = i
							while (nextURLIndex < polishedRSS.size && !polishedRSS[nextURLIndex].startsWith(
									"http"
								)
							) {
								nextURLIndex++
								d("ARTICLETAG", polishedRSS[nextURLIndex])
							}
							i = nextURLIndex - 2
							searchStatus = END
						}
						END -> {
							tempArticleList.add(tempArticle)
							searchStatus = START
						}
					}
					if (i + 1 <= polishedRSS.size) i++
				}
			} catch (e: Exception) {
				d("RSSASYNCTASK", e.javaClass.canonicalName, e)
			}
			else -> d(
				"RSSASYNCTASK",
				"Illegal Article ",
				IllegalStateException("Unexpected value: " + strings[1].uppercase(getDefault()))
			)
		}
		val tempList = finalArticleList.get()
		tempList.addAll(tempArticleList)
		finalArticleList.set(tempList)
		return "done"
	}

	override fun onPostExecute(result: String) {
		super.onPostExecute(result)
		when (publisher.uppercase(getDefault())) {
			"THE VERGE" -> {
				d("RSSASYNCTASK", "THE VERGE FINISHED")
				RSSAsyncTask(logos, fragment).execute("https://www.wired.com/feed", "WIRED")
			}
			"WIRED" -> {
				d("RSSASYNCTASK", "WIRED FINISHED")
				RSSAsyncTask(
					logos,
					fragment
				).execute("https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml", "NYTimes")
			}
			"NYTIMES" -> {
				(fragment as OnDataSendToActivity).refreshList()
				d("RSSASYNCTASK", "NYTIMES FINISHED")
			}
		}
		(fragment as OnDataSendToActivity).refreshList()
		d("RSSASYNCTASK", "did a thing")
	}

	companion object {
		const val START = 0
		const val TITLE = 1
		const val DESC = 2
		const val LINK = 3
		const val DATE = 4
		const val AUTHOR = 5
		const val END = 6
	}
}