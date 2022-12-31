package com.example.buildmypc.ui.newsfeed

import android.graphics.drawable.Drawable

class Article(
	var heading: String?,
	var author: String?,
	var desc: String?,
	var originURL: String?,
	var publisher: String?,
	var image: Drawable?,
	var dateStr: String?
) {
	override fun toString() = "$heading, written by $author on $dateStr"

	interface OnDataSendToActivity {
		fun refreshList()
	}
}