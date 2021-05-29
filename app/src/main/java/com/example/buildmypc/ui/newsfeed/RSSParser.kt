package com.example.buildmypc.ui.newsfeed

import android.content.Context
import com.prof.rssparser.Channel
import com.prof.rssparser.OnTaskCompleted
import com.prof.rssparser.Parser
import java.nio.charset.Charset

class RSSParser(context: Context) {
	init {
		parser = Parser.Builder()
			.context(context)
			.charset(Charset.forName("ISO-8859-7"))
			.cacheExpirationMillis(24L * 60L * 60L * 100L)
			.build()
		parser.onFinish(object : OnTaskCompleted {
			//what to do when the parsing finishes
			override fun onTaskCompleted(channel: Channel) {
				// Use the channel info
			}

			//what to do in case of error
			override fun onError(e: Exception) {
				// Handle the exception
			}
		})
	}

	companion object {
		lateinit var parser: Parser
	}

	fun execute(url: String): Unit = parser.execute(url)
}