package com.example.buildmypc.ui.newsfeed;

import android.graphics.drawable.Drawable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
	private String heading;
	private String author;
	private String desc;
	private String originURL;
	private String publisher;
	private Drawable image;
	private String dateStr;

	public Article(String heading, String author, String desc, String originURL, String publisher, Drawable image, String dateStr) {
		this.heading = heading;
		this.author = author;
		this.desc = desc;
		this.originURL = originURL;
		this.publisher = publisher;
		this.image = image;
		this.dateStr = dateStr;
	}

	public Article() {
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getOriginURL() {
		return originURL;
	}

	public void setOriginURL(String originURL) {
		this.originURL = originURL;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Drawable getImage() {
		return image;
	}

	public void setImage(Drawable image) {
		this.image = image;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String toString(){
		return getHeading() + ", written by " + getAuthor() + " on " + getDateStr();
	}

	public interface OnDataSendToActivity {
		void refreshList();
	}
}