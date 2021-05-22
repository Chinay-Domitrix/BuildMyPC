package com.example.buildmypc.ui.newsfeed;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.net.URL;
import java.util.Date;

public class Article {
	private String heading;
	private String desc;
	private URL originURL;
	private String publisher;
	private Drawable image;
	private Date date;

	public Article(){
		this("", "", null, "", null, null);
	}

	public Article(String heading, String desc, URL originURL, String publisher, Drawable image, Date date){
		this.heading = heading;
		this.desc = desc;
		this.originURL = originURL;
		this.publisher = publisher;
		this.image = image;
		this.date = date;
	}

	public String getHeading() { return heading; }
	public void setHeading(String heading) { this.heading = heading; }
	public String getDesc() { return desc; }
	public void setDesc(String desc) { this.desc = desc; }
	public URL getOriginURL() { return originURL; }
	public void setOriginURL(URL originURL) { this.originURL = originURL; }
	public String getPublisher() { return publisher; }
	public void setPublisher(String publisher) { this.publisher = publisher; }
	public Drawable getImage() { return image; }
	public void setImage(Drawable image) { this.image = image; }
	public Date getDate() {return date;}
	public void setDate(Date date) { this.date = date; }
}
