package com.example.buildmypc.ui.newsfeed;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

public class ArticleXMLParser {
	private final String data;
	private final String publisher;
	private final String itemDivisor;
	private final String nameInd; // name indicator
	private final String descInd;
	private final String dateInd;
	private final String urlInd;
	private final String imageInd;

	public ArticleXMLParser(String data, @NotNull String publisher, @NotNull String itemDivisor, @NotNull String nameInd, @NotNull String descInd, @NotNull String dateInd, String urlInd, String imageInd) {
		this.data = data;
		this.publisher = publisher.toUpperCase();
		this.itemDivisor = itemDivisor.toUpperCase();
		this.nameInd = nameInd.toUpperCase();
		this.descInd = descInd.toUpperCase();
		this.dateInd = dateInd.toUpperCase();
		this.urlInd = urlInd;
		this.imageInd = imageInd;
	}

	public ArrayList<Article> parseData() throws ParseException, MalformedURLException {
		ArrayList<Article> articles = new ArrayList<>();
		Article currentArticle = new Article();
		int signifierLen;
		String remainder;
		String[] dataArr = data.split("r'[\\r\\n]+'");
		int dataIndex = 0;
//		boolean contLoop = true;
		while (dataIndex < dataArr.length) {
			String line = dataArr[dataIndex];
			if (line.contains("<" + itemDivisor + ">"))
				articles.add(new Article()); // indicator that divides up different elements
			else if (line.contains("<" + nameInd)) { // name indicator code
				currentArticle = articles.get(articles.size() - 1);
				signifierLen = ("<" + nameInd + ">").length();
				remainder = line.substring(signifierLen, line.length() - signifierLen - 1);
				currentArticle.setHeading(remainder);
			} else if (line.contains("<" + descInd)) { //TODO determine whether this is able to even be shown
				signifierLen = ("<" + descInd + ">").length();
				remainder = line.substring(signifierLen, line.length() - signifierLen - 1);
				currentArticle.setDesc(remainder);
			} else if (line.contains("<" + dateInd)) { // date indicator code
				signifierLen = ("<" + dateInd + ">").length();
				remainder = line.substring(signifierLen, line.length() - signifierLen - 1);
				currentArticle.setDate(remainder); // this line can throw a ParseException iff a SimpleDateFormatter
			} else if (line.contains("<" + urlInd)) { // url indicator
				signifierLen = ("<" + urlInd + ">").length();
				remainder = line.substring(signifierLen, line.length() - signifierLen - 1);
				currentArticle.setOriginURL(/*new URL(*/remainder)/*)*/;
			}
			dataIndex++;
		}
		return articles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ArticleXMLParser that = (ArticleXMLParser) o;

		if (!Objects.equals(data, that.data)) return false;
		if (!Objects.equals(publisher, that.publisher))
			return false;
		if (!Objects.equals(itemDivisor, that.itemDivisor))
			return false;
		if (!Objects.equals(nameInd, that.nameInd)) return false;
		if (!Objects.equals(descInd, that.descInd)) return false;
		if (!Objects.equals(dateInd, that.dateInd)) return false;
		if (!Objects.equals(urlInd, that.urlInd)) return false;
		return Objects.equals(imageInd, that.imageInd);
	}

	@Override
	public int hashCode() {
		int result = data != null ? data.hashCode() : 0;
		result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
		result = 31 * result + (itemDivisor != null ? itemDivisor.hashCode() : 0);
		result = 31 * result + (nameInd != null ? nameInd.hashCode() : 0);
		result = 31 * result + (descInd != null ? descInd.hashCode() : 0);
		result = 31 * result + (dateInd != null ? dateInd.hashCode() : 0);
		result = 31 * result + (urlInd != null ? urlInd.hashCode() : 0);
		result = 31 * result + (imageInd != null ? imageInd.hashCode() : 0);
		return result;
	}
}
//switch(publisher.toUpperCase()){
//		case "TECHMEME":
//		break;
//
//		case "TECHCRUNCH":
//		break;
//
//		case "MIT_TECHNOLOGY":
//		break;
//
//		case "THE_VERGE":
//
//		break;
//
//		case "WIRED":
//		break;
//
//		case "NYTIMES":
//		break;
//
//		case "ARS_TECHNICHA":
//		break;
//
//		case "ENGADGET":
//		break;
//
//default:
//		break;