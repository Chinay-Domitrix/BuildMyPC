package com.example.buildmypc.ui.newsfeed

import android.content.Context
import com.prof.rssparser.Channel
import com.prof.rssparser.OnTaskCompleted
import com.prof.rssparser.Parser
import com.prof.rssparser.Parser.Builder
import java.nio.charset.Charset.forName

class RSSParser(context: Context) {
	init {
		parser = Builder().context(context).charset(forName("ISO-8859-7"))
			.cacheExpirationMillis(8640000).build()
		parser.onFinish(object : OnTaskCompleted {
//			what to do when the parsing finishes
			override fun onTaskCompleted(channel: Channel) {
//				Use the channel info
			}

//			what to do in case of error
			override fun onError(e: Exception) {
//				Handle the exception
			}
		})
	}

	companion object {
		lateinit var parser: Parser
	}

	fun execute(url: String): Unit {
		viewModelScope.launch {
			try {
				val channel = parser.getChannel(url)
				// Do something with your data
			} catch (e: Exception) {
				e.printStackTrace()
				// Handle the exception
			}
		}
	}
}