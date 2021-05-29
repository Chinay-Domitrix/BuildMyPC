package com.example.buildmypc.ui.newsfeed;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArticleXMLParser {
	private final String data;
	private final String publisher;

	private final String itemDivisor;
	private final String nameInd; // name indicator
	private final String descInd;
	private final String dateInd;
	private final String urlInd;
	private final String imageInd;

	public ArticleXMLParser(String data, String publisher, String itemDivisor, String nameInd, String descInd, String dateInd, String urlInd, String imageInd) {
		this.data = data;
		this.publisher = publisher.toUpperCase();
		this.itemDivisor = itemDivisor.toUpperCase();
		this.nameInd = nameInd.toUpperCase();
		this.descInd = descInd.toUpperCase();
		this.dateInd = dateInd.toUpperCase();
		this.urlInd = urlInd;
		this.imageInd = imageInd;
	}

	public ArrayList<Article> parseData(){
		ArrayList<Article> articles = new ArrayList<Article>();
		Article currentArticle = new Article();
		String[] dataArr = data.split("r'[\\r\\n]+'");
		int dataIndex = 0;
		boolean contLoop = true;
		while(contLoop && dataIndex < dataArr.length){
			String line = dataArr[dataIndex];
			if(line.contains("<" + itemDivisor)){ // indicator that divides up different elements
				articles.add(new Article());
			}
			else if(line.contains("<" + nameInd)){ // name indicator code
				currentArticle = articles.get(articles.size() - 1);
				currentArticle.setHeading();
			}
			else if(line.contains("<" + descInd)){ //TODO determine whether this is able to even be shown

			}
			else if(line.contains("<" + dateInd)){ // date indicator code

			}
			else if(line.contains("<" + urlInd)){ // url indicator

			}

			dataIndex++;
		}

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
