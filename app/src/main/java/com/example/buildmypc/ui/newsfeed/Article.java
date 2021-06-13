package com.example.buildmypc.ui.newsfeed;

import android.graphics.drawable.Drawable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
	private String heading;
	private String desc;
	private String originURL;
	private String publisher;
	private Drawable image;
	private Date date;
	private SimpleDateFormat format;

	public Article() {
		this("", "", null, "", null, null, null);
	}

	public Article(String heading, String desc, String originURL, String publisher, Drawable image, Date date, SimpleDateFormat format) {
		this.heading = heading;
		this.desc = desc;
		this.originURL = originURL;
		this.publisher = publisher;
		this.image = image;
		this.date = date;
		this.format = format;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
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

	public Date getDate() {
		return date;
	}

	public void setDate(String dateStr) throws ParseException {
		assert format != null;
		date = format.parse(dateStr);
	}

	public SimpleDateFormat getFormat() {
		return format;
	}

	public void setFormat(SimpleDateFormat format) {
		this.format = format;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Article article = (Article) o;

		if (getHeading() != null ? !getHeading().equals(article.getHeading()) : article.getHeading() != null)
			return false;
		if (getDesc() != null ? !getDesc().equals(article.getDesc()) : article.getDesc() != null)
			return false;
		if (getOriginURL() != null ? !getOriginURL().equals(article.getOriginURL()) : article.getOriginURL() != null)
			return false;
		if (getPublisher() != null ? !getPublisher().equals(article.getPublisher()) : article.getPublisher() != null)
			return false;
		if (getImage() != null ? !getImage().equals(article.getImage()) : article.getImage() != null)
			return false;
		if (getDate() != null ? !getDate().equals(article.getDate()) : article.getDate() != null)
			return false;
		return getFormat() != null ? getFormat().equals(article.getFormat()) : article.getFormat() == null;
	}

	@Override
	public int hashCode() {
		int result = getHeading() != null ? getHeading().hashCode() : 0;
		result = 31 * result + (getDesc() != null ? getDesc().hashCode() : 0);
		result = 31 * result + (getOriginURL() != null ? getOriginURL().hashCode() : 0);
		result = 31 * result + (getPublisher() != null ? getPublisher().hashCode() : 0);
		result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
		result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
		result = 31 * result + (getFormat() != null ? getFormat().hashCode() : 0);
		return result;
	}
}