package com.ex.ex1.selenium;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class instagram_search_list {
	
		private static final Logger logger = LoggerFactory.getLogger(instagram_search_list.class);
	
		public void  crawl(WebDriver driver, String search) throws InterruptedException, IOException, ParseException {
		
		try {
			try {	
		Document doc = Jsoup.connect("https:/www.instagram.com/web/search/topsearch/")
							.header("origin", "https://www.instagram.com")
							.header("referer", "https://www.instagram.com/")
							.header("accept-encoding", "gzip, deflate, br")
							.header("x-requested-with", "XMLHttpRequest")
							.header("context", "blended")
							.header("query", search)
							.header("include_reel", "true")
							.ignoreContentType(true)
							.get();
		ArrayList<String> result = new ArrayList<>();
		JSONObject jsonObject = new JSONObject();
		JSONParser parser = new JSONParser();
		jsonObject = (JSONObject)parser.parse(doc.text());
		
		System.out.println("doc : " + jsonObject);
		
		/*JSONArray items = (JSONArray) ((JSONArray) jsonObject.get("items")).get(0);
		for(int i=0; i < items.size(); i++) {
		  String item = (String) (((JSONArray) items.get(i)).get(0));
		  result.add(item);
		}


		// 얻어낸 추천 검색어 목록.
		// 테스트 프로젝트의 자바 버전이 낮아 for문을 사용했다.
		for(String item : result) {
		  System.out.println(item);
		}*/
		
		
	
    	System.out.println("======================================================");
                
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }

        } catch (NullPointerException e) {
        	e.printStackTrace();
        } finally {
			driver.close();
		}
		
	}
	
	
}
