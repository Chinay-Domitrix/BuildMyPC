package com.example.buildmypc.ui.newsfeed;

import java.util.ArrayList;

public class ArticleXMLParser {
	private String data;
	private String publisher;

	private String itemDivisor;
	private String nameInd; // name indicator
	private String descInd;
	private String dateInd;
	private String urlInd;

	public ArticleXMLParser(String data, String publisher, String itemDivisor, String nameInd, String descInd, String dateInd, String urlInd) {
		this.data = data;
		this.publisher = publisher.toUpperCase();
		this.itemDivisor = itemDivisor.toUpperCase();
		this.nameInd = nameInd.toUpperCase();
		this.descInd = descInd.toUpperCase();
		this.dateInd = dateInd.toUpperCase();
		this.urlInd = urlInd;
	}

	public ArrayList<Article> parseData(){
		String[] dataArr = data.split("r'[\\r\\n]+'");
		int index = 0;
		boolean contLoop = true;
		while(contLoop && index < dataArr.length){
			String line = dataArr[index];
			if()
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
